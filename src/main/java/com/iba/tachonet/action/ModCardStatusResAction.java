package com.iba.tachonet.action;

import com.iba.tachonet.ExecutionContext;
import com.iba.tachonet.bean.ModCardDetailsTCN2MSResType;
import com.iba.tachonet.bean.StatusCodeEnumType;
import com.iba.tachonet.bean.TCN2MSModCardStatusResType;

/**
 * @author Vladimir Radchuk
 * 
 */
public class ModCardStatusResAction extends TCN2MSAbstractAction {

    private final TCN2MSModCardStatusResType tcn2msModCardStatusResType;

    /**
     * Default constructor
     * 
     * @param tcn2msModCardStatusResType
     */
    public ModCardStatusResAction(
            TCN2MSModCardStatusResType tcn2msModCardStatusResType) {
        super();
        this.tcn2msModCardStatusResType = tcn2msModCardStatusResType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.Action#execute(com.iba.tachonet.ExecutionContext)
     */
    public void execute(ExecutionContext context) throws Exception {
        String refId = tcn2msModCardStatusResType.getHeader().getMSRefId();
        StatusCodeEnumType statusCode = tcn2msModCardStatusResType.getHeader()
                .getStatusCode();
        String statusMessage = tcn2msModCardStatusResType.getHeader()
                .getStatusMessage();
        if (statusCode != StatusCodeEnumType.OK
                && tcn2msModCardStatusResType.getBody().getCardDetails().size() == 0)
            context.getLotusDAO().updateWithTCNReceipt(refId, statusCode,
                    statusMessage);
        else
            for (ModCardDetailsTCN2MSResType item : tcn2msModCardStatusResType
                    .getBody().getCardDetails()) {
                try {
                    context.getLotusDAO().updateWithModCardStatusResponse(
                            refId, statusCode, statusMessage, item);
                } catch (Exception e) {
                    log.error(this.getClass().getName(), e);
                }
            }
    }
}
