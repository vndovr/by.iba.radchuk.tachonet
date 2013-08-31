package com.iba.tachonet.action;

import com.iba.tachonet.ExecutionContext;
import com.iba.tachonet.bean.IssuedCardMS2TCNResType;
import com.iba.tachonet.bean.IssuedCardType;
import com.iba.tachonet.bean.MS2TCNIssuedCardDLResType;
import com.iba.tachonet.bean.TCN2MSIssuedCardDLReqType;

/**
 * @author Vladimir Radchuk
 * 
 */
public class IssuedCardDLReqAction extends TCN2MSAbstractAction {
    private final TCN2MSIssuedCardDLReqType tcn2msIssuedCardDLReqType;

    /**
     * Default constructor
     * 
     * @param tcn2msIssuedCardDLReqType
     */
    public IssuedCardDLReqAction(
            TCN2MSIssuedCardDLReqType tcn2msIssuedCardDLReqType) {
        super();
        this.tcn2msIssuedCardDLReqType = tcn2msIssuedCardDLReqType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.Action#execute(com.iba.tachonet.ExecutionContext)
     */
    public void execute(ExecutionContext context) throws Exception {
        MS2TCNIssuedCardDLResType ms2tcnICDLR = getObjectFactory()
                .createMS2TCNIssuedCardDLResType();
        ms2tcnICDLR.setHeader(getResponseHeader(tcn2msIssuedCardDLReqType
                .getHeader()));
        ms2tcnICDLR.setBody(getObjectFactory()
                .createMS2TCNIssuedCardDLResBodyType());
        try {
            for (IssuedCardType item : tcn2msIssuedCardDLReqType.getBody()
                    .getIssuedCard()) {

                IssuedCardMS2TCNResType result = context.getLotusDAO()
                        .getIssuedCardMS2TCNResponse(getObjectFactory(), item);
                if (result != null)
                    ms2tcnICDLR.getBody().getIssuedCard().add(result);
                else
                    log.warn("getIssuedCardMS2TCNResponse() returned <<null>>");
            }
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            reply(getObjectFactory().createTCNReceipt(
                    getISEMessage(getResponseHeader(tcn2msIssuedCardDLReqType
                            .getHeader()), e.getMessage())));
            return;
        }
        reply(getObjectFactory().createMS2TCNIssuedCardDLRes(ms2tcnICDLR));
    }
}
