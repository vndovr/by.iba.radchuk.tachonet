package com.iba.tachonet.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iba.tachonet.IncomingMessage;
import com.iba.tachonet.MessageProcessingException;
import com.iba.tachonet.lotus.LotusDAO;
import com.iba.tachonet.message.BatchDetectorVisitor;
import com.iba.tachonet.message.ThreadPool;
import com.iba.tachonet.message.XMLSchemaValidator;
import com.iba.tachonet.utils.MessageUtils;
import com.iba.tachonet.utils.Resources;

/**
 * Servlet implementation class MessageHandler
 */
public class MessageHandler extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final Log log = LogFactory.getLog(MessageHandler.class);

    /**
     * Default constructor
     */
    public MessageHandler() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        log.info("GET called...");
        resp
                .getWriter()
                .println(
                        "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">"
                                + "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"ru\" lang=\"ru\">"
                                + "<head>"
                                + "</head>"
                                + "<body>"
                                + "<h1>TachoNET in Belarus</h1>"
                                + "<h2>Follow the appropriate procedure to use this application</h2>"
                                + "<small>Copyright (c) 2010 IBA Ltd.</small>"
                                + "</body>" + "</html>");
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
     * , javax.servlet.http.HttpServletResponse)
     */
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String xml = IOUtils.toString(request.getInputStream(), "utf-8");

        if (Resources.isSystemLogIncomingMessagesEnabled())
            ThreadPool
                    .logXmlMessage(xml, Resources.getTCNRequestHeaderSender());

        try {
            processXmlRequest(response, xml);
            response.setStatus(HttpServletResponse.SC_ACCEPTED);
        } catch (Exception e) {
            log.error(this.getClass().getName() + ".doPost()", e);
            response
                    .sendError(
                            e instanceof MessageProcessingException ? HttpServletResponse.SC_BAD_REQUEST
                                    : HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                            e.getMessage());
        }
    }

    /**
     * Process the XML request data
     * 
     * @param response
     * @param xml
     * @throws JAXBException
     * @throws MessageProcessingException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private void processXmlRequest(HttpServletResponse response, String xml)
            throws JAXBException, MessageProcessingException, IOException {
        // validates XML against schema
        String validationError = new XMLSchemaValidator().validate(xml);

        if (validationError != null)
            throw new MessageProcessingException("Incorrect XML data: "
                    + validationError);

        // deserialize object from XML
        Object o = MessageUtils.unmarshall(xml);

        if (o == null || !(o instanceof JAXBElement<?>))
            throw new MessageProcessingException(
                    "Message is empty or has an incorrect format");

        if (!(((JAXBElement<?>) o).getValue() instanceof IncomingMessage))
            throw new MessageProcessingException("Message type is incorrect");

        IncomingMessage message = ((JAXBElement<? extends IncomingMessage>) o)
                .getValue();

        BatchDetectorVisitor batchDetectorVisitor = new BatchDetectorVisitor();

        message.accept(batchDetectorVisitor);

        log.info(batchDetectorVisitor.isBatch() ? "This is <<batch>> request"
                : "This is <<online>> request");

        if (!(batchDetectorVisitor.isBatch()
                && Resources.isSystemBatchProcessingEnabled() && persistForBatchProcessing(xml)))
            ThreadPool.processIncomingMessage(message);
    }

    /**
     * Persist message for batch processing.
     * 
     * @param im
     * @return true if successfull
     */
    private boolean persistForBatchProcessing(String xml) {
        try {
            new LotusDAO().persistForBatchProcessing(xml);
            return true;
        } catch (Exception e) {
            log.error("MessageHandler.persistForBatchProcessing()", e);
            return false;
        }
    }
}
