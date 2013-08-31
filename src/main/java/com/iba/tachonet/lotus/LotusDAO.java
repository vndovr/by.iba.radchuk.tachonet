package com.iba.tachonet.lotus;

import javax.xml.datatype.DatatypeFactory;

import com.iba.tachonet.bean.IssuedCardMS2TCNResType;
import com.iba.tachonet.bean.IssuedCardTCN2MSResType;
import com.iba.tachonet.bean.IssuedCardType;
import com.iba.tachonet.bean.ModCardDetailsMS2TCNResType;
import com.iba.tachonet.bean.ModCardDetailsTCN2MSReqType;
import com.iba.tachonet.bean.ModCardDetailsTCN2MSResType;
import com.iba.tachonet.bean.ObjectFactory;
import com.iba.tachonet.bean.SearchCriteriaMS2TCNResType;
import com.iba.tachonet.bean.SearchCriteriaTCN2MSResType;
import com.iba.tachonet.bean.SearchCriteriaType;
import com.iba.tachonet.bean.SearchedDriverMS2TCNResType;
import com.iba.tachonet.bean.SearchedDriverTCN2MSReqType;
import com.iba.tachonet.bean.SearchedDriverTCN2MSResType;
import com.iba.tachonet.bean.StatusCodeEnumType;

/**
 * @author Vladimir Radchuk
 * 
 */
public class LotusDAO extends LotusActionExecutor {

    /**
     * Default constructor
     */
    public LotusDAO() {
        super();
    }

    /**
     * @param refId
     * @param statusCode
     * @param statusMessage
     * @param item
     * @throws Exception
     */
    public void updateWithCheckCardStatusResponse(String refId,
            StatusCodeEnumType statusCode, String statusMessage,
            SearchCriteriaTCN2MSResType item) throws Exception {
        executeLotusAction(new UpdateWithCheckCardStatusResponseAction(refId,
                statusCode, statusMessage, item));
    }

    /**
     * @param refId
     * @param statusCode
     * @param statusMessage
     * @param item
     * @throws Exception
     */
    public void updateWithCheckIssuedCardResponse(String refId,
            StatusCodeEnumType statusCode, String statusMessage,
            SearchedDriverTCN2MSResType item) throws Exception {
        executeLotusAction(new UpdateWithCheckIssuedCardResponseAction(refId,
                statusCode, statusMessage, item));

    }

    /**
     * @param refId
     * @param statusCode
     * @param statusMessage
     * @param item
     * @throws Exception
     */
    public void updateWithIssuedCardResponse(String refId,
            StatusCodeEnumType statusCode, String statusMessage,
            IssuedCardTCN2MSResType item) throws Exception {
        executeLotusAction(new UpdateWithIssuedCardResponseAction(refId,
                statusCode, statusMessage, item));
    }

    /**
     * @param refId
     * @param statusCode
     * @param statusMessage
     * @param item
     * @throws Exception
     */
    public void updateWithModCardStatusResponse(String refId,
            StatusCodeEnumType statusCode, String statusMessage,
            ModCardDetailsTCN2MSResType item) throws Exception {
        executeLotusAction(new UpdateWithModCardStatusResponseAction(refId,
                statusCode, statusMessage, item));
    }

    /**
     * @param objectFactory
     * @param item
     * @return
     * @throws Exception
     */
    public ModCardDetailsMS2TCNResType getModCardDetailsMS2TCNRespose(
            ObjectFactory objectFactory, ModCardDetailsTCN2MSReqType item)
            throws Exception {
        return executeLotusAction(new GetModCardDetailsMS2TCNResposeAction(
                objectFactory, item));
    }

    /**
     * @param objectFactory
     * @param item
     * @return
     * @throws Exception
     */
    public IssuedCardMS2TCNResType getIssuedCardMS2TCNResponse(
            ObjectFactory objectFactory, IssuedCardType item) throws Exception {
        return executeLotusAction(new GetIssuedCardMS2TCNResponseAction(
                objectFactory, item));
    }

    /**
     * @param objectFactory
     * @param datatypeFactory
     * @param item
     * @return
     * @throws Exception
     */
    public SearchedDriverMS2TCNResType getSearchedDriverMS2TCNResponse(
            ObjectFactory objectFactory, DatatypeFactory datatypeFactory,
            SearchedDriverTCN2MSReqType item) throws Exception {
        return executeLotusAction(new GetSearchedDriverMS2TCNResponseAction(
                objectFactory, datatypeFactory, item));
    }

    /**
     * @param objectFactory
     * @param item
     * @return
     * @throws Exception
     */
    public SearchCriteriaMS2TCNResType getSearchCriteriaMS2TCNResponse(
            ObjectFactory objectFactory, DatatypeFactory datatypeFactory,
            SearchCriteriaType item) throws Exception {
        return executeLotusAction(new GetSearchCriteriaMS2TCNResponseAction(
                objectFactory, datatypeFactory, item));
    }

    /**
     * @param refId
     * @param statusCode
     * @param statusMessage
     * @throws Exception
     */
    public void updateWithTCNReceipt(String refId,
            StatusCodeEnumType statusCode, String statusMessage)
            throws Exception {
        executeLotusAction(new UpdateWithTCNReceiptAction(refId, statusCode,
                statusMessage));
    }

    /**
     * Persists the XML message for later processing
     * 
     * @param xml
     */
    public void persistForBatchProcessing(String xml) throws Exception {
        executeLotusAction(new PersistForBatchProcessingAction(xml));
    }

    /**
     * Logs the XML message to lotus notes database
     * 
     * @param xml
     * @param consumer
     * @throws Exception
     */
    public void logXmlMessage(String xml, String consumer) throws Exception {
        executeLotusAction(new LogXmlMessageAction(xml, consumer));
    }
}
