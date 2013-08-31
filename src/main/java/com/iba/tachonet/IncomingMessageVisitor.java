package com.iba.tachonet;

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
public interface IncomingMessageVisitor {

    void accept(TCN2MSModCardStatusResType tcn2msModCardStatusResType)
            throws MessageProcessingException;

    void accept(TCN2MSModCardStatusReqType tcn2msModCardStatusReqType)
            throws MessageProcessingException;

    void accept(TCN2MSIssuedCardDLResType tcn2msIssuedCardDLResType)
            throws MessageProcessingException;

    void accept(TCN2MSIssuedCardDLReqType tcn2msIssuedCardDLReqType)
            throws MessageProcessingException;

    void accept(TCN2MSCheckIssuedCardsResType tcn2msCheckIssuedCardsResType)
            throws MessageProcessingException;

    void accept(TCN2MSCheckIssuedCardsReqType tcn2msCheckIssuedCardsReqType)
            throws MessageProcessingException;

    void accept(TCN2MSCheckCardStatusResType tcn2msCheckCardStatusResType)
            throws MessageProcessingException;

    void accept(TCN2MSCheckCardStatusReqType tcn2msCheckCardStatusReqType)
            throws MessageProcessingException;

    void accept(TCNReceiptType tcnReceiptType)
            throws MessageProcessingException;

}
