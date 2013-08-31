package com.iba.tachonet.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.client.ClientProtocolException;

import com.iba.tachonet.Action;
import com.iba.tachonet.OutgoingMessage;
import com.iba.tachonet.TachoNetCaller;
import com.iba.tachonet.bean.HeaderResType;
import com.iba.tachonet.bean.HeaderTCN2MSReqType;
import com.iba.tachonet.bean.ObjectFactory;
import com.iba.tachonet.bean.StatusCodeEnumType;
import com.iba.tachonet.bean.TCNReceiptType;
import com.iba.tachonet.utils.Resources;

/**
 * @author Vladimir Radchuk
 * 
 */
public abstract class TCN2MSAbstractAction implements Action {

    protected static Log log = LogFactory.getLog(TCN2MSAbstractAction.class);

    private ObjectFactory objectFactory = null;
    private DatatypeFactory datatypeFactory = null;

    /**
     * Default constructor
     */
    public TCN2MSAbstractAction() {
        super();
    }

    /**
     * Returns the ObjectFactory instanse
     * 
     * @return
     */
    protected ObjectFactory getObjectFactory() {
        if (objectFactory == null)
            objectFactory = new ObjectFactory();
        return objectFactory;
    }

    /**
     * Returns the DatatypeFactory instanse
     * 
     * @return
     * @throws DatatypeConfigurationException
     */
    protected DatatypeFactory getDatatypeFactory()
            throws DatatypeConfigurationException {
        if (datatypeFactory == null)
            datatypeFactory = DatatypeFactory.newInstance();
        return datatypeFactory;
    }

    /**
     * Constructs the response header from the request header
     * 
     * @param header
     * @return
     * @throws DatatypeConfigurationException
     */
    protected HeaderResType getResponseHeader(HeaderTCN2MSReqType header)
            throws DatatypeConfigurationException {
        HeaderResType result = getObjectFactory().createHeaderResType();
        result.setFrom(Resources.getTCNRequestHeaderSender());
        result.setSentAt(getDatatypeFactory().newXMLGregorianCalendar(
                (GregorianCalendar) GregorianCalendar.getInstance()));
        result.setStatusCode(StatusCodeEnumType.OK);
        result.setStatusMessage("Ok");
        result.setTCNRefId(header.getTCNRefId());
        result.setTo(Resources.getTCNRequestHeaderReceiver());
        result.setVersion(Resources.getTCNRequestHeaderVersion());
        if (Resources.isTCNSystemTestModeEnabled())
            result.setTestId(Resources.getTCNRequestTestId());
        return result;
    }

    /**
     * Sends the reply back to tachonet system
     * 
     * @param message
     * @throws ClientProtocolException
     * @throws UnsupportedEncodingException
     * @throws IOException
     * @throws JAXBException
     */
    protected void reply(JAXBElement<? extends OutgoingMessage> message)
            throws Exception {
        TachoNetCaller.call(message);
    }

    /**
     * Generates TCNReceipt message for internal server error
     * 
     * @param responseHeader
     * @param message
     */
    protected TCNReceiptType getISEMessage(HeaderResType responseHeader,
            String message) {
        TCNReceiptType ise = getObjectFactory().createTCNReceiptType();
        ise.setHeader(responseHeader);
        ise.getHeader().setStatusCode(StatusCodeEnumType.SERVER_ERROR);
        ise.getHeader().setStatusMessage(message);
        return ise;
    }

}
