package com.iba.tachonet.action;

import com.iba.tachonet.ExecutionContext;
import com.iba.tachonet.bean.MS2TCNCheckCardStatusResType;
import com.iba.tachonet.bean.SearchCriteriaMS2TCNResType;
import com.iba.tachonet.bean.SearchCriteriaType;
import com.iba.tachonet.bean.TCN2MSCheckCardStatusReqType;

/**
 * @author Vladimir Radchuk
 * 
 */
public class CheckCardStatusReqAction extends TCN2MSAbstractAction {
    private final TCN2MSCheckCardStatusReqType tcn2msCheckCardStatusReqType;

    /**
     * Default constructor
     * 
     * @param tcn2msCheckCardStatusReqType
     */
    public CheckCardStatusReqAction(
            TCN2MSCheckCardStatusReqType tcn2msCheckCardStatusReqType) {
        super();
        this.tcn2msCheckCardStatusReqType = tcn2msCheckCardStatusReqType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.Action#execute(com.iba.tachonet.ExecutionContext)
     */
    public void execute(ExecutionContext context) throws Exception {
        MS2TCNCheckCardStatusResType ms2tcnCCSR = getObjectFactory()
                .createMS2TCNCheckCardStatusResType();
        ms2tcnCCSR.setHeader(getResponseHeader(tcn2msCheckCardStatusReqType
                .getHeader()));

        ms2tcnCCSR.setBody(getObjectFactory()
                .createMS2TCNCheckCardStatusResBodyType());

        try {
            for (SearchCriteriaType item : tcn2msCheckCardStatusReqType
                    .getBody().getSearchCriteria()) {
                SearchCriteriaMS2TCNResType result = context.getLotusDAO()
                        .getSearchCriteriaMS2TCNResponse(getObjectFactory(),
                                getDatatypeFactory(), item);
                if (result != null)
                    ms2tcnCCSR.getBody().getSearchCriteria().add(result);
                else
                    log.warn(".getSearchCriteriaResponse() returned <<null>>");
            }
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            reply(getObjectFactory().createTCNReceipt(
                    getISEMessage(
                            getResponseHeader(tcn2msCheckCardStatusReqType
                                    .getHeader()), e.getMessage())));
            return;
        }
        reply(getObjectFactory().createMS2TCNCheckCardStatusRes(ms2tcnCCSR));
    }
}
