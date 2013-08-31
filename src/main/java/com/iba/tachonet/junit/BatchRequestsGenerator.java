package com.iba.tachonet.junit;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import com.iba.tachonet.IncomingMessage;
import com.iba.tachonet.bean.CCSTCN2MSSearchStatusCodeEnumType;
import com.iba.tachonet.bean.CICTCN2MSSearchStatusCodeEnumType;
import com.iba.tachonet.bean.CardDetailsCCSType;
import com.iba.tachonet.bean.CardStatusCodeEnumType;
import com.iba.tachonet.bean.DLStatusCodeEnumType;
import com.iba.tachonet.bean.DeclaredByMSType;
import com.iba.tachonet.bean.DriverDetailsCCSType;
import com.iba.tachonet.bean.DrivingLicenseDetailsType;
import com.iba.tachonet.bean.HeaderResType;
import com.iba.tachonet.bean.HeaderTCN2MSReqType;
import com.iba.tachonet.bean.ICDLTCN2MSStatusCodeEnumType;
import com.iba.tachonet.bean.IssuedCardTCN2MSResType;
import com.iba.tachonet.bean.IssuedCardType;
import com.iba.tachonet.bean.MCSReasonCodeEnumType;
import com.iba.tachonet.bean.MCSTCN2MSStatusCodeEnumType;
import com.iba.tachonet.bean.MSStatusCodeEnumType;
import com.iba.tachonet.bean.MemberStateType;
import com.iba.tachonet.bean.ModCardDetailsTCN2MSReqType;
import com.iba.tachonet.bean.ModCardDetailsTCN2MSResType;
import com.iba.tachonet.bean.NewCardStatusCodeEnumType;
import com.iba.tachonet.bean.ObjectFactory;
import com.iba.tachonet.bean.SearchCriteriaTCN2MSResType;
import com.iba.tachonet.bean.SearchCriteriaType;
import com.iba.tachonet.bean.SearchedDriverTCN2MSReqType;
import com.iba.tachonet.bean.SearchedDriverTCN2MSResType;
import com.iba.tachonet.bean.StatusCodeEnumType;
import com.iba.tachonet.bean.TCN2MSCheckCardStatusReqType;
import com.iba.tachonet.bean.TCN2MSCheckCardStatusResType;
import com.iba.tachonet.bean.TCN2MSCheckIssuedCardsReqType;
import com.iba.tachonet.bean.TCN2MSCheckIssuedCardsResType;
import com.iba.tachonet.bean.TCN2MSIssuedCardDLReqType;
import com.iba.tachonet.bean.TCN2MSIssuedCardDLResType;
import com.iba.tachonet.bean.TCN2MSModCardStatusReqType;
import com.iba.tachonet.bean.TCN2MSModCardStatusResType;
import com.iba.tachonet.message.XMLSchemaValidator;
import com.iba.tachonet.utils.MessageUtils;
import com.iba.tachonet.utils.Resources;

/**
 * @author Vladimir Radchuk
 * 
 */
public final class BatchRequestsGenerator {

    private static Log log = LogFactory.getLog(BatchRequestsGenerator.class);

    private static ObjectFactory objectFactory;
    private static DatatypeFactory datatypeFactory;

    static {
        try {
            datatypeFactory = DatatypeFactory.newInstance();
            objectFactory = new ObjectFactory();
        } catch (DatatypeConfigurationException e) {
            log.error("Exception in initialization: ", e);
        }
    }

    public static void main(String[] args) {
        try {
            log.info("Started...");

            // All requests from TachoNET
            JAXBElement<? extends IncomingMessage> checkCardStatusReqMessage = getTCN2MSCheckCardStatusReqType();
            JAXBElement<? extends IncomingMessage> checkIssuedCardsReqMessage = getTCN2MSCheckIssuedCardsReqType();
            JAXBElement<? extends IncomingMessage> issuedCardDLReqMessage = getTCN2MSIssuedCardDLReqType();
            JAXBElement<? extends IncomingMessage> modCardStatusReqMessage = getTCN2MSModCardStatusReqType();

            // All responses from TachoNET
            JAXBElement<? extends IncomingMessage> checkCardStatusResMessage = getTCN2MSCheckCardStatusResType();
            JAXBElement<? extends IncomingMessage> checkIssuedCardsResMessage = getTCN2MSCheckIssuedCardsResType();
            JAXBElement<? extends IncomingMessage> issuedCardDLResMessage = getTCN2MSIssuedCardDLResType();
            JAXBElement<? extends IncomingMessage> modCardStatusResMessage = getTCN2MSModCardStatusResType();

            // Validate all generated messages
            validate(checkCardStatusReqMessage);
            validate(checkIssuedCardsReqMessage);
            validate(issuedCardDLReqMessage);
            validate(modCardStatusReqMessage);
            validate(checkCardStatusResMessage);
            validate(checkIssuedCardsResMessage);
            validate(issuedCardDLResMessage);
            validate(modCardStatusResMessage);

            // Calls the local service
            // call(checkCardStatusReqMessage);
            // call(checkIssuedCardsReqMessage);
            // call(issuedCardDLReqMessage);
            // call(modCardStatusReqMessage);
            // call(checkCardStatusResMessage);
            // call(checkIssuedCardsResMessage);
            // call(issuedCardDLResMessage);
            // call(modCardStatusResMessage);

        } catch (Exception e) {
            log.error("main()", e);
        } finally {
            log.info("Completed...");
        }
    }

    /**
     * Default constructor
     * 
     * @throws DatatypeConfigurationException
     */
    private BatchRequestsGenerator() throws DatatypeConfigurationException {
        super();
    }

    /**
     * Tests handling of TCN2MSCheckCardStatusReqType
     * 
     * @throws JAXBException
     * @throws IOException
     * @throws ClientProtocolException
     */
    static JAXBElement<? extends IncomingMessage> getTCN2MSCheckCardStatusReqType()
            throws JAXBException, ClientProtocolException, IOException {
        TCN2MSCheckCardStatusReqType request = objectFactory
                .createTCN2MSCheckCardStatusReqType();
        request.setHeader(getReqHeader());
        request.setBody(objectFactory.createTCN2MSCheckCardStatusReqBodyType());

        SearchCriteriaType item1 = objectFactory.createSearchCriteriaType();
        SearchCriteriaType item2 = objectFactory.createSearchCriteriaType();

        request.getBody().getSearchCriteria().add(item1);
        request.getBody().getSearchCriteria().add(item2);

        item1.setCardNumber("1234567891");
        item2.setCardNumber("3453534532");

        return objectFactory.createTCN2MSCheckCardStatusReq(request);
    }

    /**
     * Tests handling of TCN2MSCheckIssuedCardsReqType
     * 
     * @throws JAXBException
     * @throws IOException
     * @throws ClientProtocolException
     */
    static JAXBElement<? extends IncomingMessage> getTCN2MSCheckIssuedCardsReqType()
            throws JAXBException, ClientProtocolException, IOException {
        TCN2MSCheckIssuedCardsReqType request = objectFactory
                .createTCN2MSCheckIssuedCardsReqType();
        request.setHeader(getReqHeader());
        request
                .setBody(objectFactory
                        .createTCN2MSCheckIssuedCardsReqBodyType());

        SearchedDriverTCN2MSReqType item1 = objectFactory
                .createSearchedDriverTCN2MSReqType();
        SearchedDriverTCN2MSReqType item2 = objectFactory
                .createSearchedDriverTCN2MSReqType();

        request.getBody().getSearchedDriver().add(item1);
        request.getBody().getSearchedDriver().add(item2);

        item1.setBirthDate("1976-01-25");
        item1.setDrivingLicenseIssuingNation("BY");
        item1.setDrivingLicenseNumber("KN0324");
        item1.setFirstName("ULADZIMIR");
        item1.setFirstNameSearchKey("UL");
        item1.setPlaceOfBirth("ROVNO");
        item1.setSurname("RADCHUK");
        item1.setSurnameSearchKey("RADCHUK");

        item2.setBirthDate("1976-01-25");
        item2.setDrivingLicenseIssuingNation("BY");
        item2.setDrivingLicenseNumber("KN0324");
        item2.setFirstName("ULADZIMIR");
        item2.setFirstNameSearchKey("UL");
        item2.setPlaceOfBirth("ROVNO");
        item2.setSurname("RADCHUK");
        item2.setSurnameSearchKey("RADCHUK");

        return objectFactory.createTCN2MSCheckIssuedCardsReq(request);
    }

    /**
     * Tests handling of TCN2MSIssuedCardDLReqType
     * 
     * @throws JAXBException
     * @throws IOException
     * @throws ClientProtocolException
     */
    static JAXBElement<? extends IncomingMessage> getTCN2MSIssuedCardDLReqType()
            throws JAXBException, ClientProtocolException, IOException {
        TCN2MSIssuedCardDLReqType request = objectFactory
                .createTCN2MSIssuedCardDLReqType();
        request.setHeader(getReqHeader());
        request.setBody(objectFactory.createTCN2MSIssuedCardDLReqBodyType());

        IssuedCardType item1 = objectFactory.createIssuedCardType();
        IssuedCardType item2 = objectFactory.createIssuedCardType();

        request.getBody().getIssuedCard().add(item1);
        request.getBody().getIssuedCard().add(item2);

        item1.setDrivingLicenseNumber("SDF");
        item1.setIssuedCardNumber("23423423");
        item1.setIssuingMemberStateCode("BY");

        item2.setDrivingLicenseNumber("LKU");
        item2.setIssuedCardNumber("342342234");
        item2.setIssuingMemberStateCode("UK");

        return objectFactory.createTCN2MSIssuedCardDLReq(request);
    }

    /**
     * Tests handling of TCN2MSModCardStatusReqType
     * 
     * @throws JAXBException
     * @throws IOException
     * @throws ClientProtocolException
     */
    static JAXBElement<? extends IncomingMessage> getTCN2MSModCardStatusReqType()
            throws JAXBException, ClientProtocolException, IOException {
        TCN2MSModCardStatusReqType request = objectFactory
                .createTCN2MSModCardStatusReqType();
        request.setHeader(getReqHeader());
        request.setBody(objectFactory.createTCN2MSModCardStatusReqBodyType());

        ModCardDetailsTCN2MSReqType item1 = objectFactory
                .createModCardDetailsTCN2MSReqType();
        ModCardDetailsTCN2MSReqType item2 = objectFactory
                .createModCardDetailsTCN2MSReqType();

        request.getBody().getCardDetails().add(item1);
        request.getBody().getCardDetails().add(item2);

        DeclaredByMSType declaredBy1 = objectFactory.createDeclaredByMSType();
        declaredBy1.setEMail("some@tachonet.com");
        declaredBy1.setFax("+37529173333");
        declaredBy1.setFirstName("Firstname");
        declaredBy1.setSurname("Surname");
        declaredBy1.setMemberStateCode("UK");
        declaredBy1.setOrigin("ORIGIN");
        declaredBy1.setPhone("+37529173333");

        DeclaredByMSType declaredBy2 = objectFactory.createDeclaredByMSType();
        declaredBy2.setEMail("some@tachonet.com");
        declaredBy2.setFax("+37529173333");
        declaredBy2.setFirstName("Firstname");
        declaredBy2.setSurname("Surname");
        declaredBy2.setMemberStateCode("UK");
        declaredBy2.setOrigin("ORIGIN");
        declaredBy2.setPhone("+37529173333");

        item1.setCardNumber("12345668");
        item1.setDeclaredBy(declaredBy1);
        item1.setNewCardStatus(NewCardStatusCodeEnumType.CONFISCATED);
        item1.setReason("This is the reason");
        item1.setReasonCode(MCSReasonCodeEnumType.NONE);
        item1.setStatusModifiedAt(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));

        item2.setCardNumber("12345668");
        item2.setDeclaredBy(declaredBy2);
        item2.setNewCardStatus(NewCardStatusCodeEnumType.CONFISCATED);
        item2.setReason("This is the reason");
        item2.setReasonCode(MCSReasonCodeEnumType.NONE);
        item2.setStatusModifiedAt(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));

        return objectFactory.createTCN2MSModCardStatusReq(request);
    }

    /**
     * Tests handling of TCN2MSCheckCardStatusResType
     * 
     * @throws JAXBException
     * @throws IOException
     * @throws ClientProtocolException
     */
    static JAXBElement<? extends IncomingMessage> getTCN2MSCheckCardStatusResType()
            throws JAXBException, ClientProtocolException, IOException {
        TCN2MSCheckCardStatusResType response = objectFactory
                .createTCN2MSCheckCardStatusResType();
        response.setHeader(getResHeader());
        response
                .setBody(objectFactory.createTCN2MSCheckCardStatusResBodyType());

        SearchCriteriaTCN2MSResType item1 = objectFactory
                .createSearchCriteriaTCN2MSResType();
        SearchCriteriaTCN2MSResType item2 = objectFactory
                .createSearchCriteriaTCN2MSResType();

        response.getBody().getSearchCriteria().add(item1);
        response.getBody().getSearchCriteria().add(item2);

        CardDetailsCCSType cardDetails1 = objectFactory
                .createCardDetailsCCSType();
        CardDetailsCCSType cardDetails2 = objectFactory
                .createCardDetailsCCSType();

        cardDetails1
                .setAdditionalCardStatus(CardStatusCodeEnumType.CONFISCATED);
        cardDetails1.setAdditionalStatusModifiedAt(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        cardDetails1.setCardStatus(CardStatusCodeEnumType.APPROVED);
        cardDetails1.setCIA("CIA");
        cardDetails1.setExpiryDate(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        cardDetails1.setStartOfValidityDate(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        cardDetails1.setStatusModifiedAt(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));

        cardDetails2
                .setAdditionalCardStatus(CardStatusCodeEnumType.CONFISCATED);
        cardDetails2.setAdditionalStatusModifiedAt(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        cardDetails2.setCardStatus(CardStatusCodeEnumType.APPROVED);
        cardDetails2.setCIA("CIA");
        cardDetails2.setExpiryDate(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        cardDetails2.setStartOfValidityDate(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        cardDetails2.setStatusModifiedAt(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));

        DriverDetailsCCSType driverDetails1 = objectFactory
                .createDriverDetailsCCSType();

        DriverDetailsCCSType driverDetails2 = objectFactory
                .createDriverDetailsCCSType();

        driverDetails1.setBirthDate("1998-01-01");
        driverDetails1.setFirstName("ULADZIMIR");
        driverDetails1.setPlaceOfBirth("MINSK");
        driverDetails1.setSurname("RADCHUK");

        driverDetails2.setBirthDate("1998-01-01");
        driverDetails2.setFirstName("ULADZIMIR");
        driverDetails2.setPlaceOfBirth("MINSK");
        driverDetails2.setSurname("RADCHUK");

        DrivingLicenseDetailsType licenseDetails1 = objectFactory
                .createDrivingLicenseDetailsType();
        DrivingLicenseDetailsType licenseDetails2 = objectFactory
                .createDrivingLicenseDetailsType();

        licenseDetails1.setDLIssueDate(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        licenseDetails1.setDLIssuingNation("BELARUS");
        licenseDetails1.setDLNumber("342");
        licenseDetails1.setDLStatus(DLStatusCodeEnumType.VALID);

        licenseDetails2.setDLIssueDate(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        licenseDetails2.setDLIssuingNation("BELARUS");
        licenseDetails2.setDLNumber("342");
        licenseDetails2.setDLStatus(DLStatusCodeEnumType.VALID);

        driverDetails1.setDrivingLicenseDetails(licenseDetails1);
        driverDetails2.setDrivingLicenseDetails(licenseDetails2);

        cardDetails1.setDriverDetails(driverDetails1);
        cardDetails2.setDriverDetails(driverDetails2);

        item1.setCardDetails(cardDetails1);
        item1.setCardNumber("12345668");
        item1.setIssuingMemberStateCode("BY");
        item1.setSearchStatusCode(CCSTCN2MSSearchStatusCodeEnumType.FOUND);
        item1.setSearchStatusMessage("Found fucking card");

        item2.setCardDetails(cardDetails2);
        item2.setCardNumber("12345668");
        item2.setIssuingMemberStateCode("BY");
        item2.setSearchStatusCode(CCSTCN2MSSearchStatusCodeEnumType.FOUND);
        item2.setSearchStatusMessage("Found fucking card");

        return objectFactory.createTCN2MSCheckCardStatusRes(response);
    }

    /**
     * Tests handling of TCN2MSCheckIssuedCardsResType
     * 
     * @throws JAXBException
     * @throws IOException
     * @throws ClientProtocolException
     */
    static JAXBElement<? extends IncomingMessage> getTCN2MSCheckIssuedCardsResType()
            throws JAXBException, ClientProtocolException, IOException {
        TCN2MSCheckIssuedCardsResType response = objectFactory
                .createTCN2MSCheckIssuedCardsResType();
        response.setHeader(getResHeader());
        response.setBody(objectFactory
                .createTCN2MSCheckIssuedCardsResBodyType());

        SearchedDriverTCN2MSResType item1 = objectFactory
                .createSearchedDriverTCN2MSResType();
        SearchedDriverTCN2MSResType item2 = objectFactory
                .createSearchedDriverTCN2MSResType();

        response.getBody().getSearchedDriver().add(item1);
        response.getBody().getSearchedDriver().add(item2);

        MemberStateType state1 = objectFactory.createMemberStateType();
        state1.setMemberStateCode("BY");
        state1.setMSStatusCode(MSStatusCodeEnumType.FOUND);
        state1.setMSStatusMessage("OK");

        MemberStateType state2 = objectFactory.createMemberStateType();
        state2.setMemberStateCode("BY");
        state2.setMSStatusCode(MSStatusCodeEnumType.FOUND);
        state2.setMSStatusMessage("OK");

        item1.getMemberState().add(state1);

        item1.setBirthDate("1967-02-28");
        item1.setDrivingLicenseIssuingNation("BY");
        item1.setDrivingLicenseNumber("34234232");
        item1.setFirstName("ULADZIMIR");
        item1.setIssuingMemberStateCode("BY");
        item1.setPlaceOfBirth("MINSK");
        item1.setSearchStatusCode(CICTCN2MSSearchStatusCodeEnumType.FOUND);
        item1.setSearchStatusMessage("OK");
        item1.setSurname("RADCHUK");

        item2.getMemberState().add(state2);

        item2.setBirthDate("1967-02-28");
        item2.setDrivingLicenseIssuingNation("BY");
        item2.setDrivingLicenseNumber("34234232");
        item2.setFirstName("ULADZIMIR");
        item2.setIssuingMemberStateCode("BY");
        item2.setPlaceOfBirth("MINSK");
        item2.setSearchStatusCode(CICTCN2MSSearchStatusCodeEnumType.FOUND);
        item2.setSearchStatusMessage("OK");
        item2.setSurname("RADCHUK");

        return objectFactory.createTCN2MSCheckIssuedCardsRes(response);
    }

    /**
     * Tests handling of TCN2MSIssuedCardDLResType
     * 
     * @throws JAXBException
     * @throws IOException
     * @throws ClientProtocolException
     */
    static JAXBElement<? extends IncomingMessage> getTCN2MSIssuedCardDLResType()
            throws JAXBException, ClientProtocolException, IOException {
        TCN2MSIssuedCardDLResType response = objectFactory
                .createTCN2MSIssuedCardDLResType();
        response.setHeader(getResHeader());
        response.setBody(objectFactory.createTCN2MSIssuedCardDLResBodyType());

        IssuedCardTCN2MSResType item1 = objectFactory
                .createIssuedCardTCN2MSResType();
        IssuedCardTCN2MSResType item2 = objectFactory
                .createIssuedCardTCN2MSResType();

        response.getBody().getIssuedCard().add(item1);
        response.getBody().getIssuedCard().add(item2);

        item1.setDrivingLicenseIssuingNation("BY");
        item1.setDrivingLicenseNumber("234234");
        item1.setIssuedCardDLStatusCode(ICDLTCN2MSStatusCodeEnumType.OK);
        item1.setIssuedCardDLStatusMessage("OK");
        item1.setIssuedCardNumber("12343");
        item1.setIssuingMemberStateCode("BY");

        item2.setDrivingLicenseIssuingNation("BY");
        item2.setDrivingLicenseNumber("234234");
        item2.setIssuedCardDLStatusCode(ICDLTCN2MSStatusCodeEnumType.OK);
        item2.setIssuedCardDLStatusMessage("OK");
        item2.setIssuedCardNumber("12343");
        item2.setIssuingMemberStateCode("BY");

        return objectFactory.createTCN2MSIssuedCardDLRes(response);
    }

    /**
     * Tests handling of TCN2MSModCardStatusResType
     * 
     * @throws JAXBException
     * @throws IOException
     * @throws ClientProtocolException
     */
    static JAXBElement<? extends IncomingMessage> getTCN2MSModCardStatusResType()
            throws JAXBException, ClientProtocolException, IOException {
        TCN2MSModCardStatusResType response = objectFactory
                .createTCN2MSModCardStatusResType();
        response.setHeader(getResHeader());
        response.setBody(objectFactory.createTCN2MSModCardStatusResBodyType());

        ModCardDetailsTCN2MSResType item1 = objectFactory
                .createModCardDetailsTCN2MSResType();
        ModCardDetailsTCN2MSResType item2 = objectFactory
                .createModCardDetailsTCN2MSResType();

        response.getBody().getCardDetails().add(item1);
        response.getBody().getCardDetails().add(item2);

        DeclaredByMSType declaredBy1 = objectFactory.createDeclaredByMSType();
        declaredBy1.setEMail("some@tachonet.com");
        declaredBy1.setFax("+37529173333");
        declaredBy1.setFirstName("Firstname");
        declaredBy1.setSurname("Surname");
        declaredBy1.setMemberStateCode("UK");
        declaredBy1.setOrigin("ORIGIN");
        declaredBy1.setPhone("+37529173333");

        DeclaredByMSType declaredBy2 = objectFactory.createDeclaredByMSType();
        declaredBy2.setEMail("some@tachonet.com");
        declaredBy2.setFax("+37529173333");
        declaredBy2.setFirstName("Firstname");
        declaredBy2.setSurname("Surname");
        declaredBy2.setMemberStateCode("UK");
        declaredBy2.setOrigin("ORIGIN");
        declaredBy2.setPhone("+37529173333");

        item1.setCardNumber("324234324");
        item1.setDeclaredBy(declaredBy1);
        item1.setIssuingMemberStateCode("BY");
        item1.setModStatusCode(MCSTCN2MSStatusCodeEnumType.OK);
        item1.setModStatusMessage("OK");
        item1.setNewCardStatus(NewCardStatusCodeEnumType.LOST);
        item1.setReason("Reason goes there");
        item1.setReasonCode(MCSReasonCodeEnumType.NONE);
        item1.setStatusModifiedAt(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));

        item2.setCardNumber("324234324");
        item2.setDeclaredBy(declaredBy2);
        item2.setIssuingMemberStateCode("BY");
        item2.setModStatusCode(MCSTCN2MSStatusCodeEnumType.OK);
        item2.setModStatusMessage("OK");
        item2.setNewCardStatus(NewCardStatusCodeEnumType.LOST);
        item2.setReason("Reason goes there");
        item2.setReasonCode(MCSReasonCodeEnumType.NONE);
        item2.setStatusModifiedAt(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));

        return objectFactory.createTCN2MSModCardStatusRes(response);
    }

    /**
     * Generates the header for request
     * 
     * @return
     */
    static HeaderTCN2MSReqType getReqHeader() {

        HeaderTCN2MSReqType header = objectFactory.createHeaderTCN2MSReqType();
        header.setFrom(Resources.getTCNRequestHeaderReceiver());
        header.setTCNRefId("TCNRefId");
        header.setSentAt(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        header.setTimeoutValue(Resources.getTCNRequestHeaderOnlineTimeout());
        header.setTo(Resources.getTCNRequestHeaderSender());
        header.setVersion(Resources.getTCNRequestHeaderVersion());
        if (Resources.isTCNSystemTestModeEnabled())
            header.setTestId(Resources.getTCNRequestTestId());
        return header;
    }

    /**
     * Generates the header for request
     * 
     * @return
     */
    static HeaderResType getResHeader() {

        HeaderResType header = objectFactory.createHeaderResType();
        header.setFrom(Resources.getTCNRequestHeaderReceiver());
        header.setMSRefId("msRefId");
        header.setTCNRefId("TCNRefId");
        header.setSentAt(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        header.setStatusCode(StatusCodeEnumType.OK);
        header.setStatusMessage("OK");
        header.setTo(Resources.getTCNRequestHeaderSender());
        header.setVersion(Resources.getTCNRequestHeaderVersion());
        if (Resources.isTCNSystemTestModeEnabled())
            header.setTestId(Resources.getTCNRequestTestId());
        return header;
    }

    /**
     * @param message
     * @return
     * @throws JAXBException
     * @throws UnsupportedEncodingException
     */
    static void validate(JAXBElement<? extends IncomingMessage> message)
            throws UnsupportedEncodingException, JAXBException {
        String xml = MessageUtils.marshall(message);
        String validationError = new XMLSchemaValidator().validate(xml);
        if (validationError != null)
            log.info("Message type [" + message.getValue().getClass().getName()
                    + "] validation failed with error: " + validationError);
        else
            log.info("Message type [" + message.getValue().getClass().getName()
                    + "] validated successfully.");
    }

    /**
     * Sends the message to TachoNet
     * 
     * @return The Response code
     * @param message
     * @throws JAXBException
     * @throws IOException
     * @throws ClientProtocolException
     */
    static int call(JAXBElement<? extends IncomingMessage> message)
            throws JAXBException, ClientProtocolException, IOException {
        String xml = MessageUtils.marshall(message);

        log.info("XML to send: \n" + xml);

        HttpPost post = new HttpPost(Resources.getMemberMessageHandlerAddress());

        StringEntity se = new StringEntity(xml, "utf-8");
        post.setEntity(se);
        HttpResponse result = new DefaultHttpClient().execute(post);

        return result.getStatusLine().getStatusCode();
    }
}
