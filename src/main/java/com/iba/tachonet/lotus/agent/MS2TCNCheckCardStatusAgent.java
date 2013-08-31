package com.iba.tachonet.lotus.agent;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.xml.bind.JAXBException;

import lotus.domino.Document;

import org.apache.http.client.ClientProtocolException;

import com.iba.tachonet.TachoNetCaller;
import com.iba.tachonet.bean.MS2TCNCheckCardStatusReqType;
import com.iba.tachonet.bean.SearchCriteriaMS2TCNReqType;

/**
 * @author Vladimir Radchuk
 * 
 */
class MS2TCNCheckCardStatusAgent extends DocumentProcessorAgent {
    private static final String MS2TCNCheckCardStatusView = "";

    /**
     * Default constructor
     */
    public MS2TCNCheckCardStatusAgent() {
        super(MS2TCNCheckCardStatusView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iba.tachonet.lotus.agent.DocumentProcessorAgent#process(lotus.domino
     * .Document)
     */
    @Override
    protected void process(Document document) throws Exception {
        int code = sendMS2TCNCheckCardStatusReq(document.getUniversalID(),
                "cardNumber", "issuingMemberStateCode");
        if (code == HttpURLConnection.HTTP_ACCEPTED) {
            // mark document status as (Request sent)
        }
        throw new Exception("Processing of document ["
                + document.getUniversalID() + "] failed with HTTP staus code ["
                + code + "]");

    }

    /**
     * @param msRefID
     * @param cardNumber
     * @param issuingMemberStateCode
     * @return
     * @throws ClientProtocolException
     * @throws JAXBException
     * @throws IOException
     */
    public int sendMS2TCNCheckCardStatusReq(String msRefID, String cardNumber,
            String issuingMemberStateCode) throws ClientProtocolException,
            JAXBException, IOException {

        MS2TCNCheckCardStatusReqType ms2tcnCCSR = objectFactory
                .createMS2TCNCheckCardStatusReqType();
        ms2tcnCCSR.setHeader(getHeader(msRefID, false));
        ms2tcnCCSR.setBody(objectFactory
                .createMS2TCNCheckCardStatusReqBodyType());

        SearchCriteriaMS2TCNReqType criteria = objectFactory
                .createSearchCriteriaMS2TCNReqType();
        ms2tcnCCSR.getBody().getSearchCriteria().add(criteria);

        if (cardNumber != null)
            criteria.setCardNumber(cardNumber);
        if (issuingMemberStateCode != null)
            criteria.setIssuingMemberStateCode(issuingMemberStateCode);

        return TachoNetCaller.call(objectFactory
                .createMS2TCNCheckCardStatusReq(ms2tcnCCSR));
    }

}
