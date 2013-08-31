package com.iba.tachonet.action;

import com.iba.tachonet.ExecutionContext;
import com.iba.tachonet.bean.IssuedCardTCN2MSResType;
import com.iba.tachonet.bean.StatusCodeEnumType;
import com.iba.tachonet.bean.TCN2MSIssuedCardDLResType;

/**
 * @author Vladimir Radchuk
 * 
 */
public class IssuedCardDLResAction extends TCN2MSAbstractAction {

    private final TCN2MSIssuedCardDLResType tcn2msIssuedCardDLResType;

    /**
     * Default constructor
     * 
     * @param tcn2msIssuedCardDLResType
     */
    public IssuedCardDLResAction(
            TCN2MSIssuedCardDLResType tcn2msIssuedCardDLResType) {
        super();
        this.tcn2msIssuedCardDLResType = tcn2msIssuedCardDLResType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.Action#execute(com.iba.tachonet.ExecutionContext)
     */
    public void execute(ExecutionContext context) throws Exception {
        String refId = tcn2msIssuedCardDLResType.getHeader().getMSRefId();
        StatusCodeEnumType statusCode = tcn2msIssuedCardDLResType.getHeader()
                .getStatusCode();
        String statusMessage = tcn2msIssuedCardDLResType.getHeader()
                .getStatusMessage();
        if (statusCode != StatusCodeEnumType.OK
                && tcn2msIssuedCardDLResType.getBody().getIssuedCard().size() == 0)
            context.getLotusDAO().updateWithTCNReceipt(refId, statusCode,
                    statusMessage);
        else
            for (IssuedCardTCN2MSResType item : tcn2msIssuedCardDLResType
                    .getBody().getIssuedCard()) {
                try {
                    context.getLotusDAO().updateWithIssuedCardResponse(refId,
                            statusCode, statusMessage, item);
                } catch (Exception e) {
                    log.error(this.getClass().getName(), e);
                }
            }
    }

}
