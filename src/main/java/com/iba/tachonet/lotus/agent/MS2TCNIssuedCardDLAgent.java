package com.iba.tachonet.lotus.agent;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.xml.bind.JAXBException;

import lotus.domino.Document;

import org.apache.http.client.ClientProtocolException;

import com.iba.tachonet.TachoNetCaller;
import com.iba.tachonet.bean.IssuedCardMS2TCNReqType;
import com.iba.tachonet.bean.MS2TCNIssuedCardDLReqType;

/**
 * @author Vladimir Radchuk
 * 
 */
class MS2TCNIssuedCardDLAgent extends DocumentProcessorAgent {
    private static final String MS2TCNIssuedCardDLView = "view";

    /**
     * Default constructor
     */
    public MS2TCNIssuedCardDLAgent() {
        super(MS2TCNIssuedCardDLView);
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
        int code = sendMS2TCNIssuedCardDLReqType(document.getUniversalID(),
                "drivingLicenseIssuingNation", "drivingLicenseNumber",
                "issuedCardNumber", "issuingMemberStateCode");

        if (code == HttpURLConnection.HTTP_ACCEPTED) {
            // mark document status as (Request sent)
        }
        throw new Exception("Processing of document ["
                + document.getUniversalID() + "] failed with HTTP staus code ["
                + code + "]");

    }

    /**
     * @param msRefID
     * @param drivingLicenseIssuingNation
     * @param drivingLicenseNumber
     * @param issuedCardNumber
     * @param issuingMemberStateCode
     * @return
     * @throws ClientProtocolException
     * @throws JAXBException
     * @throws IOException
     */
    public int sendMS2TCNIssuedCardDLReqType(String msRefID,
            String drivingLicenseIssuingNation, String drivingLicenseNumber,
            String issuedCardNumber, String issuingMemberStateCode)
            throws ClientProtocolException, JAXBException, IOException {
        MS2TCNIssuedCardDLReqType ms2TCNICDLR = objectFactory
                .createMS2TCNIssuedCardDLReqType();
        ms2TCNICDLR.setHeader(getHeader(msRefID, false));
        ms2TCNICDLR
                .setBody(objectFactory.createMS2TCNIssuedCardDLReqBodyType());
        IssuedCardMS2TCNReqType ic = objectFactory
                .createIssuedCardMS2TCNReqType();
        ms2TCNICDLR.getBody().getIssuedCard().add(ic);

        if (drivingLicenseIssuingNation != null)
            ic.setDrivingLicenseIssuingNation(drivingLicenseIssuingNation);
        if (drivingLicenseNumber != null)
            ic.setDrivingLicenseNumber(drivingLicenseNumber);
        if (issuedCardNumber != null)
            ic.setIssuedCardNumber(issuedCardNumber);
        if (issuingMemberStateCode != null)
            ic.setIssuingMemberStateCode(issuingMemberStateCode);

        return TachoNetCaller.call(objectFactory
                .createMS2TCNIssuedCardDLReq(ms2TCNICDLR));
    }

}
