package com.iba.tachonet.action;

import com.iba.tachonet.ExecutionContext;
import com.iba.tachonet.bean.MS2TCNModCardStatusResType;
import com.iba.tachonet.bean.ModCardDetailsMS2TCNResType;
import com.iba.tachonet.bean.ModCardDetailsTCN2MSReqType;
import com.iba.tachonet.bean.TCN2MSModCardStatusReqType;

/**
 * @author Vladimir Radchuk
 * 
 */
public class ModCardStatusReqAction extends TCN2MSAbstractAction {

    private final TCN2MSModCardStatusReqType tcn2msModCardStatusReqType;

    /**
     * Default constructor
     * 
     * @param tcn2msModCardStatusReqType
     */
    public ModCardStatusReqAction(
            TCN2MSModCardStatusReqType tcn2msModCardStatusReqType) {
        super();
        this.tcn2msModCardStatusReqType = tcn2msModCardStatusReqType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.Action#execute(com.iba.tachonet.ExecutionContext)
     */
    public void execute(ExecutionContext context) throws Exception {
        MS2TCNModCardStatusResType ms2tcnMCSR = getObjectFactory()
                .createMS2TCNModCardStatusResType();
        ms2tcnMCSR.setHeader(getResponseHeader(tcn2msModCardStatusReqType
                .getHeader()));
        ms2tcnMCSR.setBody(getObjectFactory()
                .createMS2TCNModCardStatusResBodyType());

        try {
            for (ModCardDetailsTCN2MSReqType item : tcn2msModCardStatusReqType
                    .getBody().getCardDetails()) {

                ModCardDetailsMS2TCNResType result = context.getLotusDAO()
                        .getModCardDetailsMS2TCNRespose(getObjectFactory(),
                                item);
                if (result != null)
                    ms2tcnMCSR.getBody().getCardDetails().add(result);
                else
                    log
                            .warn("getModCardDetailsMS2TCNRespose() returned <<null>>");
            }
        } catch (Exception e) {
            log.error(this.getClass().getName(), e);
            reply(getObjectFactory().createTCNReceipt(
                    getISEMessage(getResponseHeader(tcn2msModCardStatusReqType
                            .getHeader()), e.getMessage())));
            return;
        }
        reply(getObjectFactory().createMS2TCNModCardStatusRes(ms2tcnMCSR));
    }

}
