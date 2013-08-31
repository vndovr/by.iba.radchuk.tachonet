package com.iba.tachonet.action;

import com.iba.tachonet.ExecutionContext;
import com.iba.tachonet.bean.MS2TCNCheckIssuedCardsResType;
import com.iba.tachonet.bean.SearchedDriverMS2TCNResType;
import com.iba.tachonet.bean.SearchedDriverTCN2MSReqType;
import com.iba.tachonet.bean.TCN2MSCheckIssuedCardsReqType;

/**
 * @author Vladimir Radchuk
 * 
 */
public class CheckIssuedCardsReqAction extends TCN2MSAbstractAction {
    private final TCN2MSCheckIssuedCardsReqType tcn2msCheckIssuedCardsReqType;

    /**
     * Default constructor
     * 
     * @param tcn2msCheckIssuedCardsReqType
     * 
     */
    public CheckIssuedCardsReqAction(
            TCN2MSCheckIssuedCardsReqType tcn2msCheckIssuedCardsReqType) {
        super();
        this.tcn2msCheckIssuedCardsReqType = tcn2msCheckIssuedCardsReqType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.Action#execute(com.iba.tachonet.ExecutionContext)
     */
    public void execute(ExecutionContext context) throws Exception {
        MS2TCNCheckIssuedCardsResType ms2tcnCICR = getObjectFactory()
                .createMS2TCNCheckIssuedCardsResType();
        ms2tcnCICR.setHeader(getResponseHeader(tcn2msCheckIssuedCardsReqType
                .getHeader()));
        ms2tcnCICR.setBody(getObjectFactory()
                .createMS2TCNCheckIssuedCardsResBodyType());

        try {
            for (SearchedDriverTCN2MSReqType item : tcn2msCheckIssuedCardsReqType
                    .getBody().getSearchedDriver()) {
                SearchedDriverMS2TCNResType result = context.getLotusDAO()
                        .getSearchedDriverMS2TCNResponse(getObjectFactory(),
                                getDatatypeFactory(), item);
                if (result != null)
                    ms2tcnCICR.getBody().getSearchedDriver().add(result);
                else
                    log
                            .warn("getSearchedDriverMS2TCNResponse() returned <<null>>");
            }
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            reply(getObjectFactory().createTCNReceipt(
                    getISEMessage(
                            getResponseHeader(tcn2msCheckIssuedCardsReqType
                                    .getHeader()), e.getMessage())));
            return;
        }
        reply(getObjectFactory().createMS2TCNCheckIssuedCardsRes(ms2tcnCICR));
    }
}
