package com.iba.tachonet.action;

import com.iba.tachonet.ExecutionContext;
import com.iba.tachonet.bean.SearchedDriverTCN2MSResType;
import com.iba.tachonet.bean.StatusCodeEnumType;
import com.iba.tachonet.bean.TCN2MSCheckIssuedCardsResType;

/**
 * @author Vladimir Radchuk
 * 
 */
public class CheckIssuedCardsResAction extends TCN2MSAbstractAction {
    private final TCN2MSCheckIssuedCardsResType tcn2msCheckIssuedCardsResType;

    /**
     * Default constructor
     * 
     * @param tcn2msCheckIssuedCardsResType
     * 
     */
    public CheckIssuedCardsResAction(
            TCN2MSCheckIssuedCardsResType tcn2msCheckIssuedCardsResType) {
        super();
        this.tcn2msCheckIssuedCardsResType = tcn2msCheckIssuedCardsResType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.Action#execute(com.iba.tachonet.ExecutionContext)
     */
    public void execute(ExecutionContext context) throws Exception {
        String refId = tcn2msCheckIssuedCardsResType.getHeader().getMSRefId();
        StatusCodeEnumType statusCode = tcn2msCheckIssuedCardsResType
                .getHeader().getStatusCode();
        String statusMessage = tcn2msCheckIssuedCardsResType.getHeader()
                .getStatusMessage();
        if (statusCode != StatusCodeEnumType.OK
                && tcn2msCheckIssuedCardsResType.getBody().getSearchedDriver()
                        .size() == 0)
            context.getLotusDAO().updateWithTCNReceipt(refId, statusCode,
                    statusMessage);
        else
            for (SearchedDriverTCN2MSResType item : tcn2msCheckIssuedCardsResType
                    .getBody().getSearchedDriver()) {
                try {
                    context.getLotusDAO().updateWithCheckIssuedCardResponse(
                            refId, statusCode, statusMessage, item);
                } catch (Exception e) {
                    log.error(this.getClass().getName(), e);
                }
            }
    }
}
