package com.iba.tachonet.lotus.agent;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.JAXBException;

import lotus.domino.Document;

import org.apache.http.client.ClientProtocolException;

import com.iba.tachonet.TachoNetCaller;
import com.iba.tachonet.bean.DeclaredByType;
import com.iba.tachonet.bean.MCSReasonCodeEnumType;
import com.iba.tachonet.bean.MS2TCNModCardStatusReqType;
import com.iba.tachonet.bean.ModCardDetailsMS2TCNReqType;
import com.iba.tachonet.bean.NewCardStatusCodeEnumType;

/**
 * @author Vladimir Radchuk
 * 
 */
class MS2TCNModCardStatusAgent extends DocumentProcessorAgent {
    private static final String MS2TCNModCardStatusView = "view";

    /**
     * Default constructor
     */
    public MS2TCNModCardStatusAgent() {
        super(MS2TCNModCardStatusView);
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
        int code = sendMS2TCNModCardStatusReq(document.getUniversalID(),
                "cardNumber", "issuingMemberStateCode",
                NewCardStatusCodeEnumType.CONFISCATED, "reason",
                MCSReasonCodeEnumType.NONE, new Date(), "declaredByEmail",
                "declaredByFax", "declaredByFirstName", "declaredByOrigin",
                "declaredByPhone", "declaredBySurname");
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
     * @param newCardStatus
     * @param reason
     * @param reasonCode
     * @param statusModifiedAt
     * @param declaredByEmail
     * @param declaredByFax
     * @param declaredByFirstName
     * @param declaredByOrigin
     * @param declaredByPhone
     * @param declaredBySurname
     * @return
     * @throws ClientProtocolException
     * @throws JAXBException
     * @throws IOException
     */
    public int sendMS2TCNModCardStatusReq(String msRefID, String cardNumber,
            String issuingMemberStateCode,
            NewCardStatusCodeEnumType newCardStatus, String reason,
            MCSReasonCodeEnumType reasonCode, Date statusModifiedAt,
            String declaredByEmail, String declaredByFax,
            String declaredByFirstName, String declaredByOrigin,
            String declaredByPhone, String declaredBySurname)
            throws ClientProtocolException, JAXBException, IOException {
        MS2TCNModCardStatusReqType ms2tcnMCSR = objectFactory
                .createMS2TCNModCardStatusReqType();
        ms2tcnMCSR.setHeader(getHeader(msRefID, false));
        ms2tcnMCSR
                .setBody(objectFactory.createMS2TCNModCardStatusReqBodyType());
        ModCardDetailsMS2TCNReqType mcd = objectFactory
                .createModCardDetailsMS2TCNReqType();
        ms2tcnMCSR.getBody().getCardDetails().add(mcd);
        DeclaredByType dbt = objectFactory.createDeclaredByType();

        if (declaredByEmail != null || declaredByFax != null
                || declaredByFirstName != null || declaredByOrigin != null
                || declaredByPhone != null || declaredBySurname != null)
            mcd.setDeclaredBy(dbt);

        if (cardNumber != null)
            mcd.setCardNumber(cardNumber);
        if (issuingMemberStateCode != null)
            mcd.setIssuingMemberStateCode(issuingMemberStateCode);
        if (newCardStatus != null)
            mcd.setNewCardStatus(newCardStatus);
        if (reason != null)
            mcd.setReason(reason);
        if (reasonCode != null)
            mcd.setReasonCode(reasonCode);
        if (statusModifiedAt != null) {
            GregorianCalendar gc = (GregorianCalendar) GregorianCalendar
                    .getInstance();
            gc.setTime(statusModifiedAt);
            mcd
                    .setStatusModifiedAt(datatypeFactory
                            .newXMLGregorianCalendar(gc));
        }

        if (declaredByEmail != null)
            dbt.setEMail(declaredByEmail);
        if (declaredByFax != null)
            dbt.setFax(declaredByFax);
        if (declaredByFirstName != null)
            dbt.setFirstName(declaredByFirstName);
        if (declaredByOrigin != null)
            dbt.setOrigin(declaredByOrigin);
        if (declaredByPhone != null)
            dbt.setPhone(declaredByPhone);
        if (declaredBySurname != null)
            dbt.setSurname(declaredBySurname);

        return TachoNetCaller.call(objectFactory
                .createMS2TCNModCardStatusReq(ms2tcnMCSR));
    }
}
