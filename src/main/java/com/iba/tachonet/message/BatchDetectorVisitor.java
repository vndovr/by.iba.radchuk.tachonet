package com.iba.tachonet.message;

import com.iba.tachonet.IncomingMessageVisitor;
import com.iba.tachonet.MessageProcessingException;
import com.iba.tachonet.bean.TCN2MSCheckCardStatusReqType;
import com.iba.tachonet.bean.TCN2MSCheckCardStatusResType;
import com.iba.tachonet.bean.TCN2MSCheckIssuedCardsReqType;
import com.iba.tachonet.bean.TCN2MSCheckIssuedCardsResType;
import com.iba.tachonet.bean.TCN2MSIssuedCardDLReqType;
import com.iba.tachonet.bean.TCN2MSIssuedCardDLResType;
import com.iba.tachonet.bean.TCN2MSModCardStatusReqType;
import com.iba.tachonet.bean.TCN2MSModCardStatusResType;
import com.iba.tachonet.bean.TCNReceiptType;

/**
 * @author Vladimir Radchuk
 * 
 */
public class BatchDetectorVisitor implements IncomingMessageVisitor {

    private boolean batch = false;

    /**
     * Default constructor
     */
    public BatchDetectorVisitor() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iba.tachonet.IncomingMessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSModCardStatusResType)
     */
    public void accept(TCN2MSModCardStatusResType tcn2msModCardStatusResType)
            throws MessageProcessingException {
        batch = tcn2msModCardStatusResType.getBody().getCardDetails().size() > 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iba.tachonet.IncomingMessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSModCardStatusReqType)
     */
    public void accept(TCN2MSModCardStatusReqType tcn2msModCardStatusReqType)
            throws MessageProcessingException {
        batch = tcn2msModCardStatusReqType.getBody().getCardDetails().size() > 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iba.tachonet.IncomingMessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSIssuedCardDLResType)
     */
    public void accept(TCN2MSIssuedCardDLResType tcn2msIssuedCardDLResType)
            throws MessageProcessingException {
        batch = tcn2msIssuedCardDLResType.getBody().getIssuedCard().size() > 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iba.tachonet.IncomingMessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSIssuedCardDLReqType)
     */
    public void accept(TCN2MSIssuedCardDLReqType tcn2msIssuedCardDLReqType)
            throws MessageProcessingException {
        batch = tcn2msIssuedCardDLReqType.getBody().getIssuedCard().size() > 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iba.tachonet.IncomingMessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSCheckIssuedCardsResType)
     */
    public void accept(
            TCN2MSCheckIssuedCardsResType tcn2msCheckIssuedCardsResType)
            throws MessageProcessingException {
        batch = tcn2msCheckIssuedCardsResType.getBody().getSearchedDriver()
                .size() > 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iba.tachonet.IncomingMessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSCheckIssuedCardsReqType)
     */
    public void accept(
            TCN2MSCheckIssuedCardsReqType tcn2msCheckIssuedCardsReqType)
            throws MessageProcessingException {
        batch = tcn2msCheckIssuedCardsReqType.getBody().getSearchedDriver()
                .size() > 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iba.tachonet.IncomingMessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSCheckCardStatusResType)
     */
    public void accept(TCN2MSCheckCardStatusResType tcn2msCheckCardStatusResType)
            throws MessageProcessingException {
        batch = tcn2msCheckCardStatusResType.getBody().getSearchCriteria()
                .size() > 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iba.tachonet.IncomingMessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSCheckCardStatusReqType)
     */
    public void accept(TCN2MSCheckCardStatusReqType tcn2msCheckCardStatusReqType)
            throws MessageProcessingException {
        batch = tcn2msCheckCardStatusReqType.getBody().getSearchCriteria()
                .size() > 1;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iba.tachonet.IncomingMessageVisitor#accept(com.iba.tachonet.bean.
     * TCNReceiptType)
     */
    public void accept(TCNReceiptType tcnReceiptType)
            throws MessageProcessingException {
        batch = false;
    }

    /**
     * @return the batch
     */
    public boolean isBatch() {
        return batch;
    }
}
