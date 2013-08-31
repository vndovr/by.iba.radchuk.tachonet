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
import com.iba.tachonet.bean.TCNReceiptType;
import com.iba.tachonet.message.XMLSchemaValidator;
import com.iba.tachonet.utils.MessageUtils;
import com.iba.tachonet.utils.Resources;

/**
 * @author Vladimir Radchuk
 * 
 */
public final class OnlineRequestsGenerator {

    private static Log log = LogFactory.getLog(OnlineRequestsGenerator.class);

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

            // Special TCNReceipt message
            JAXBElement<? extends IncomingMessage> tcnReceipt = getTCNReceiptType();

            // Validate all generated messages
            validate(checkCardStatusReqMessage);
            validate(checkIssuedCardsReqMessage);
            validate(issuedCardDLReqMessage);
            validate(modCardStatusReqMessage);
            validate(checkCardStatusResMessage);
            validate(checkIssuedCardsResMessage);
            validate(issuedCardDLResMessage);
            validate(modCardStatusResMessage);
            validate(tcnReceipt);

            // Calls the local service
            // call(checkCardStatusReqMessage);
            // call(checkIssuedCardsReqMessage);
            // call(issuedCardDLReqMessage);
            // call(modCardStatusReqMessage);
            // call(checkCardStatusResMessage);
            // call(checkIssuedCardsResMessage);
            // call(issuedCardDLResMessage);
            // call(modCardStatusResMessage);
            // call(tcnReceipt);

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
    private OnlineRequestsGenerator() throws DatatypeConfigurationException {
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
        SearchCriteriaType item = objectFactory.createSearchCriteriaType();
        request.getBody().getSearchCriteria().add(item);
        item.setCardNumber("1234567891");
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
        SearchedDriverTCN2MSReqType item = objectFactory
                .createSearchedDriverTCN2MSReqType();
        request.getBody().getSearchedDriver().add(item);
        item.setBirthDate("1976-01-25");
        item.setDrivingLicenseIssuingNation("BY");
        item.setDrivingLicenseNumber("KN0324");
        item.setFirstName("ULADZIMIR");
        item.setFirstNameSearchKey("UL");
        item.setPlaceOfBirth("ROVNO");
        item.setSurname("RADCHUK");
        item.setSurnameSearchKey("RADCHUK");

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
        IssuedCardType item = objectFactory.createIssuedCardType();
        request.getBody().getIssuedCard().add(item);

        item.setDrivingLicenseNumber("");
        item.setIssuedCardNumber("");
        item.setIssuingMemberStateCode("");

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
        ModCardDetailsTCN2MSReqType item = objectFactory
                .createModCardDetailsTCN2MSReqType();
        request.getBody().getCardDetails().add(item);

        DeclaredByMSType declaredBy = objectFactory.createDeclaredByMSType();
        declaredBy.setEMail("some@tachonet.com");
        declaredBy.setFax("+37529173333");
        declaredBy.setFirstName("Firstname");
        declaredBy.setSurname("Surname");
        declaredBy.setMemberStateCode("UK");
        declaredBy.setOrigin("ORIGIN");
        declaredBy.setPhone("+37529173333");

        item.setCardNumber("12345668");
        item.setDeclaredBy(declaredBy);
        item.setNewCardStatus(NewCardStatusCodeEnumType.CONFISCATED);
        item.setReason("This is the reason");
        item.setReasonCode(MCSReasonCodeEnumType.NONE);
        item.setStatusModifiedAt(datatypeFactory
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
        SearchCriteriaTCN2MSResType item = objectFactory
                .createSearchCriteriaTCN2MSResType();
        response.getBody().getSearchCriteria().add(item);

        CardDetailsCCSType cardDetails = objectFactory
                .createCardDetailsCCSType();
        cardDetails.setAdditionalCardStatus(CardStatusCodeEnumType.CONFISCATED);
        cardDetails.setAdditionalStatusModifiedAt(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        cardDetails.setCardStatus(CardStatusCodeEnumType.APPROVED);
        cardDetails.setCIA("CIA");
        cardDetails.setExpiryDate(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        cardDetails.setStartOfValidityDate(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        cardDetails.setStatusModifiedAt(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));

        DriverDetailsCCSType driverDetails = objectFactory
                .createDriverDetailsCCSType();

        driverDetails.setBirthDate("1998-01-01");
        driverDetails.setFirstName("ULADZIMIR");
        driverDetails.setPlaceOfBirth("MINSK");
        driverDetails.setSurname("RADCHUK");

        DrivingLicenseDetailsType licenseDetails = objectFactory
                .createDrivingLicenseDetailsType();
        licenseDetails.setDLIssueDate(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));
        licenseDetails.setDLIssuingNation("BELARUS");
        licenseDetails.setDLNumber("342");
        licenseDetails.setDLStatus(DLStatusCodeEnumType.VALID);

        driverDetails.setDrivingLicenseDetails(licenseDetails);

        cardDetails.setDriverDetails(driverDetails);

        item.setCardDetails(cardDetails);
        item.setCardNumber("12345668");
        item.setIssuingMemberStateCode("BY");
        item.setSearchStatusCode(CCSTCN2MSSearchStatusCodeEnumType.FOUND);
        item.setSearchStatusMessage("Found fucking card");
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
        SearchedDriverTCN2MSResType item = objectFactory
                .createSearchedDriverTCN2MSResType();
        response.getBody().getSearchedDriver().add(item);

        MemberStateType state = objectFactory.createMemberStateType();
        state.setMemberStateCode("BY");
        state.setMSStatusCode(MSStatusCodeEnumType.FOUND);
        state.setMSStatusMessage("OK");

        item.getMemberState().add(state);

        item.setBirthDate("1967-02-28");
        item.setDrivingLicenseIssuingNation("BY");
        item.setDrivingLicenseNumber("34234232");
        item.setFirstName("ULADZIMIR");
        item.setIssuingMemberStateCode("BY");
        item.setPlaceOfBirth("MINSK");
        item.setSearchStatusCode(CICTCN2MSSearchStatusCodeEnumType.FOUND);
        item.setSearchStatusMessage("OK");
        item.setSurname("RADCHUK");

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
        IssuedCardTCN2MSResType item = objectFactory
                .createIssuedCardTCN2MSResType();
        response.getBody().getIssuedCard().add(item);

        item.setDrivingLicenseIssuingNation("BY");
        item.setDrivingLicenseNumber("234234");
        item.setIssuedCardDLStatusCode(ICDLTCN2MSStatusCodeEnumType.OK);
        item.setIssuedCardDLStatusMessage("OK");
        item.setIssuedCardNumber("12343");
        item.setIssuingMemberStateCode("BY");

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
        ModCardDetailsTCN2MSResType item = objectFactory
                .createModCardDetailsTCN2MSResType();
        response.getBody().getCardDetails().add(item);

        item.setCardNumber("324234324");

        DeclaredByMSType declaredBy = objectFactory.createDeclaredByMSType();
        declaredBy.setEMail("some@tachonet.com");
        declaredBy.setFax("+37529173333");
        declaredBy.setFirstName("Firstname");
        declaredBy.setSurname("Surname");
        declaredBy.setMemberStateCode("UK");
        declaredBy.setOrigin("ORIGIN");
        declaredBy.setPhone("+37529173333");

        item.setDeclaredBy(declaredBy);
        item.setIssuingMemberStateCode("BY");
        item.setModStatusCode(MCSTCN2MSStatusCodeEnumType.OK);
        item.setModStatusMessage("OK");
        item.setNewCardStatus(NewCardStatusCodeEnumType.LOST);
        item.setReason("Reason goes there");
        item.setReasonCode(MCSReasonCodeEnumType.NONE);
        item.setStatusModifiedAt(datatypeFactory
                .newXMLGregorianCalendar((GregorianCalendar) GregorianCalendar
                        .getInstance()));

        return objectFactory.createTCN2MSModCardStatusRes(response);
    }

    /**
     * Tests handling of TCN2MSModCardStatusResType
     * 
     * @throws JAXBException
     * @throws IOException
     * @throws ClientProtocolException
     */
    static JAXBElement<? extends IncomingMessage> getTCNReceiptType()
            throws JAXBException, ClientProtocolException, IOException {
        TCNReceiptType receipt = objectFactory.createTCNReceiptType();
        receipt.setHeader(getResHeader());

        receipt.getHeader().setStatusCode(StatusCodeEnumType.SERVER_ERROR);
        receipt.getHeader().setStatusMessage("Internal server error");

        return objectFactory.createTCNReceipt(receipt);
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
        header.setTimeoutValue(Resources.getTCNRequestHeaderBatchTimeout());
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
