package com.iba.tachonet.message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iba.tachonet.Action;
import com.iba.tachonet.Executor;
import com.iba.tachonet.IncomingMessageVisitor;
import com.iba.tachonet.MessageProcessingException;
import com.iba.tachonet.action.CheckCardStatusReqAction;
import com.iba.tachonet.action.CheckCardStatusResAction;
import com.iba.tachonet.action.CheckIssuedCardsReqAction;
import com.iba.tachonet.action.CheckIssuedCardsResAction;
import com.iba.tachonet.action.IssuedCardDLReqAction;
import com.iba.tachonet.action.IssuedCardDLResAction;
import com.iba.tachonet.action.ModCardStatusReqAction;
import com.iba.tachonet.action.ModCardStatusResAction;
import com.iba.tachonet.action.TCNReceiptAction;
import com.iba.tachonet.bean.TCN2MSCheckCardStatusReqType;
import com.iba.tachonet.bean.TCN2MSCheckCardStatusResType;
import com.iba.tachonet.bean.TCN2MSCheckIssuedCardsReqType;
import com.iba.tachonet.bean.TCN2MSCheckIssuedCardsResType;
import com.iba.tachonet.bean.TCN2MSIssuedCardDLReqType;
import com.iba.tachonet.bean.TCN2MSIssuedCardDLResType;
import com.iba.tachonet.bean.TCN2MSModCardStatusReqType;
import com.iba.tachonet.bean.TCN2MSModCardStatusResType;
import com.iba.tachonet.bean.TCNReceiptType;
import com.iba.tachonet.utils.Timer;

/**
 * @author Vladimir Radchuk
 * 
 */
public class RequestProcessorVisitor implements IncomingMessageVisitor {

    private static Log log = LogFactory.getLog(RequestProcessorVisitor.class);

    /**
     * Default constructor
     */
    public RequestProcessorVisitor() {
        super();
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.iba.tachonet.MessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSModCardStatusResType)
     */
    public void accept(TCN2MSModCardStatusResType tcn2msModCardStatusResType)
            throws MessageProcessingException {
        Action action = new ModCardStatusResAction(tcn2msModCardStatusResType);
        execute(action);
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.iba.tachonet.MessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSModCardStatusReqType)
     */
    public void accept(TCN2MSModCardStatusReqType tcn2msModCardStatusReqType)
            throws MessageProcessingException {
        Action action = new ModCardStatusReqAction(tcn2msModCardStatusReqType);
        execute(action);
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.iba.tachonet.MessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSIssuedCardDLResType)
     */
    public void accept(TCN2MSIssuedCardDLResType tcn2msIssuedCardDLResType)
            throws MessageProcessingException {
        Action action = new IssuedCardDLResAction(tcn2msIssuedCardDLResType);
        execute(action);
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.iba.tachonet.MessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSIssuedCardDLReqType)
     */
    public void accept(TCN2MSIssuedCardDLReqType tcn2msIssuedCardDLReqType)
            throws MessageProcessingException {
        Action action = new IssuedCardDLReqAction(tcn2msIssuedCardDLReqType);
        execute(action);
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.iba.tachonet.MessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSCheckIssuedCardsResType)
     */
    public void accept(
            TCN2MSCheckIssuedCardsResType tcn2msCheckIssuedCardsResType)
            throws MessageProcessingException {
        Action action = new CheckIssuedCardsResAction(
                tcn2msCheckIssuedCardsResType);
        execute(action);
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.iba.tachonet.MessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSCheckIssuedCardsReqType)
     */
    public void accept(
            TCN2MSCheckIssuedCardsReqType tcn2msCheckIssuedCardsReqType)
            throws MessageProcessingException {
        Action action = new CheckIssuedCardsReqAction(
                tcn2msCheckIssuedCardsReqType);
        execute(action);
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.iba.tachonet.MessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSCheckCardStatusResType)
     */
    public void accept(TCN2MSCheckCardStatusResType tcn2msCheckCardStatusResType)
            throws MessageProcessingException {
        Action action = new CheckCardStatusResAction(
                tcn2msCheckCardStatusResType);
        execute(action);
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.iba.tachonet.MessageVisitor#accept(com.iba.tachonet.bean.
     * TCN2MSCheckCardStatusReqType)
     */
    public void accept(TCN2MSCheckCardStatusReqType tcn2msCheckCardStatusReqType)
            throws MessageProcessingException {
        Action action = new CheckCardStatusReqAction(
                tcn2msCheckCardStatusReqType);
        execute(action);
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
        Action action = new TCNReceiptAction(tcnReceiptType);
        execute(action);
    }

    /**
     * @param action
     */
    private void execute(Action action) throws MessageProcessingException {
        Timer timer = new Timer(this.getClass().getName() + ".execute("
                + action.getClass().getName() + ")");
        try {
            timer.mark("Start execution");
            Executor.execute(action);
        } catch (Exception e) {
            throw new MessageProcessingException(e);
        } finally {
            timer.mark("Finish execution");
            log.info(timer);
        }
    }
}
