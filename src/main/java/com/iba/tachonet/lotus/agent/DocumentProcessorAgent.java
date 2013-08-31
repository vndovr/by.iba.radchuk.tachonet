package com.iba.tachonet.lotus.agent;

import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import lotus.domino.Document;
import lotus.domino.View;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iba.tachonet.bean.HeaderMS2TCNReqType;
import com.iba.tachonet.bean.ObjectFactory;
import com.iba.tachonet.lotus.LotusAction;
import com.iba.tachonet.utils.Resources;

/**
 * @author Vladimir Radchuk
 */
abstract class DocumentProcessorAgent extends LotusAction<AgentExecutionResult> {
    protected static final Log log = LogFactory
            .getLog(DocumentProcessorAgent.class);

    protected static final ObjectFactory objectFactory = new ObjectFactory();
    protected static final DatatypeFactory datatypeFactory = getDatatypeFactory();

    private final String view;

    /**
     * Default constructor
     */
    public DocumentProcessorAgent(String view) {
        super();
        this.view = view;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.lotus.LotusAction#execute()
     */
    @Override
    public AgentExecutionResult execute() throws Exception {
        log.info(this.getClass().getName() + ".execute()");
        AgentExecutionResult result = new AgentExecutionResult();
        View view = getRequestsDb().getView(this.view);
        Document document = view.getFirstDocument();
        while (document != null) {
            log.info("Processing document [" + document.getUniversalID() + "]");
            try {
                process(document);
                result.addSuccessful(document.getUniversalID());
            } catch (Exception e) {
                log.info("Processing failed", e);
                result.addFailed(document.getUniversalID());
            }
            document = view.getNextDocument(document);
        }
        return result;
    }

    /**
     * Generates the header for request
     * 
     * @return
     */
    protected HeaderMS2TCNReqType getHeader(String msRefID, boolean isBatch) {
        HeaderMS2TCNReqType header = objectFactory.createHeaderMS2TCNReqType();
        header.setMSRefId(msRefID);
        header.setFrom(Resources.getTCNRequestHeaderSender());
        header.setSentAt(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        header.setTimeoutValue(isBatch ? Resources
                .getTCNRequestHeaderBatchTimeout() : Resources
                .getTCNRequestHeaderOnlineTimeout());
        header.setTo(Resources.getTCNRequestHeaderReceiver());
        header.setVersion(Resources.getTCNRequestHeaderVersion());

        if (Resources.isTCNSystemTestModeEnabled())
            header.setTestId(Resources.getTCNRequestTestId());

        return header;
    }

    /**
     * @param document
     * @throws Exception
     */
    protected abstract void process(Document document) throws Exception;

    /**
     * @return
     */
    private static DatatypeFactory getDatatypeFactory() {
        try {
            return DatatypeFactory.newInstance();
        } catch (DatatypeConfigurationException e) {
            log.error("getDatatypeFactory()", e);
            return null;
        }
    }
}
