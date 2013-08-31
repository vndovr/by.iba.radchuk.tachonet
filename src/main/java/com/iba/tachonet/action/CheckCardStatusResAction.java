package com.iba.tachonet.action;

import com.iba.tachonet.ExecutionContext;
import com.iba.tachonet.bean.SearchCriteriaTCN2MSResType;
import com.iba.tachonet.bean.StatusCodeEnumType;
import com.iba.tachonet.bean.TCN2MSCheckCardStatusResType;

/**
 * @author Vladimir Radchuk
 * 
 */
public class CheckCardStatusResAction extends TCN2MSAbstractAction {
    private final TCN2MSCheckCardStatusResType tcn2msCheckCardStatusResType;

    /**
     * Default constructor
     * 
     * @param tcn2msCheckCardStatusResType
     */
    public CheckCardStatusResAction(
            TCN2MSCheckCardStatusResType tcn2msCheckCardStatusResType) {
        super();
        this.tcn2msCheckCardStatusResType = tcn2msCheckCardStatusResType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.Action#execute(com.iba.tachonet.ExecutionContext)
     */
    public void execute(ExecutionContext context) throws Exception {
        String refId = tcn2msCheckCardStatusResType.getHeader().getMSRefId();
        StatusCodeEnumType statusCode = tcn2msCheckCardStatusResType
                .getHeader().getStatusCode();
        String statusMessage = tcn2msCheckCardStatusResType.getHeader()
                .getStatusMessage();
        if (statusCode != StatusCodeEnumType.OK
                && tcn2msCheckCardStatusResType.getBody().getSearchCriteria()
                        .size() == 0)
            context.getLotusDAO().updateWithTCNReceipt(refId, statusCode,
                    statusMessage);
        else
            for (SearchCriteriaTCN2MSResType item : tcn2msCheckCardStatusResType
                    .getBody().getSearchCriteria()) {
                try {
                    context.getLotusDAO().updateWithCheckCardStatusResponse(
                            refId, statusCode, statusMessage, item);
                } catch (Exception e) {
                    log.error(this.getClass().getName(), e);
                }
            }
    }

}
