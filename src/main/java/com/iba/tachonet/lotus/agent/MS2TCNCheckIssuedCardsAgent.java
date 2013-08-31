package com.iba.tachonet.lotus.agent;

import java.io.IOException;
import java.net.HttpURLConnection;

import javax.xml.bind.JAXBException;

import lotus.domino.Document;

import org.apache.http.client.ClientProtocolException;

import com.iba.tachonet.TachoNetCaller;
import com.iba.tachonet.bean.MS2TCNCheckIssuedCardsReqType;
import com.iba.tachonet.bean.SearchedDriverMS2TCNReqType;

/**
 * @author Vladimir Radchuk
 * 
 */
class MS2TCNCheckIssuedCardsAgent extends DocumentProcessorAgent {
    private static final String MS2TCNCheckIssuedCardsView = "view";

    /**
     * Default constructor
     */
    public MS2TCNCheckIssuedCardsAgent() {
        super(MS2TCNCheckIssuedCardsView);
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
        int code = sendMS2TCNCheckIssuedCardsReq(document.getUniversalID(),
                "birthDate", "drivingLicenseIssuingNation",
                "drivingLicenseNumber", "firstName", "issuingMemberStateCode",
                "placeOfBirth", "surname");
        if (code == HttpURLConnection.HTTP_ACCEPTED) {
            // mark document status as (Request sent)
        }
        throw new Exception("Processing of document ["
                + document.getUniversalID() + "] failed with HTTP staus code ["
                + code + "]");
    }

    /**
     * @param msRefID
     * @param birthDate
     * @param drivingLicenseIssuingNation
     * @param drivingLicenseNumber
     * @param firstName
     * @param issuingMemberStateCode
     * @param placeOfBirth
     * @param surname
     * @return
     * @throws JAXBException
     * @throws ClientProtocolException
     * @throws IOException
     */
    public int sendMS2TCNCheckIssuedCardsReq(String msRefID, String birthDate,
            String drivingLicenseIssuingNation, String drivingLicenseNumber,
            String firstName, String issuingMemberStateCode,
            String placeOfBirth, String surname) throws JAXBException,
            ClientProtocolException, IOException {
        MS2TCNCheckIssuedCardsReqType ms2tcnCICR = objectFactory
                .createMS2TCNCheckIssuedCardsReqType();
        ms2tcnCICR.setHeader(getHeader(msRefID, false));
        ms2tcnCICR.setBody(objectFactory
                .createMS2TCNCheckIssuedCardsReqBodyType());
        SearchedDriverMS2TCNReqType sd = objectFactory
                .createSearchedDriverMS2TCNReqType();
        ms2tcnCICR.getBody().getSearchedDriver().add(sd);

        if (birthDate != null)
            sd.setBirthDate(birthDate);
        if (drivingLicenseIssuingNation != null)
            sd.setDrivingLicenseIssuingNation(drivingLicenseIssuingNation);
        if (drivingLicenseNumber != null)
            sd.setDrivingLicenseNumber(drivingLicenseNumber);
        if (firstName != null)
            sd.setFirstName(firstName);
        if (issuingMemberStateCode != null)
            sd.setIssuingMemberStateCode(issuingMemberStateCode);
        if (placeOfBirth != null)
            sd.setPlaceOfBirth(placeOfBirth);
        if (surname != null)
            sd.setSurname(surname);

        return TachoNetCaller.call(objectFactory
                .createMS2TCNCheckIssuedCardsReq(ms2tcnCICR));
    }

}
