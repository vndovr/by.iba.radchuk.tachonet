package com.iba.tachonet.action;

import com.iba.tachonet.ExecutionContext;
import com.iba.tachonet.bean.StatusCodeEnumType;
import com.iba.tachonet.bean.TCNReceiptType;

/**
 * @author Vladimir Radchuk
 * 
 */
public class TCNReceiptAction extends TCN2MSAbstractAction {

    private final TCNReceiptType tcnReceiptType;

    /**
     * Default constructor
     * 
     * @param tcnReceiptType
     * 
     */
    public TCNReceiptAction(TCNReceiptType tcnReceiptType) {
        super();
        this.tcnReceiptType = tcnReceiptType;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.Action#execute(com.iba.tachonet.ExecutionContext)
     */
    public void execute(ExecutionContext context) throws Exception {
        String refId = tcnReceiptType.getHeader().getMSRefId();
        StatusCodeEnumType statusCode = tcnReceiptType.getHeader()
                .getStatusCode();
        String statusMessage = tcnReceiptType.getHeader().getStatusMessage();
        context.getLotusDAO().updateWithTCNReceipt(refId, statusCode,
                statusMessage);
    }
}
