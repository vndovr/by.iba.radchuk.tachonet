package com.iba.tachonet.lotus;

import lotus.domino.Document;

import com.iba.tachonet.bean.StatusCodeEnumType;

/**
 * @author Vladimir Radchuk
 * 
 */
class UpdateWithTCNReceiptAction extends LotusAction<Object> {

    private final String refId;
    private final StatusCodeEnumType statusCode;
    private final String statusMessage;

    /**
     * Default constructor
     * 
     * @param statusMessage
     * @param statusCode
     * @param refId
     * 
     */
    public UpdateWithTCNReceiptAction(String refId,
            StatusCodeEnumType statusCode, String statusMessage) {
        super();
        this.refId = refId;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.lotus.LotusAction#execute()
     */
    @Override
    public Object execute() throws Exception {
        log.info("UpdateWithTCNReceiptAction.execute()");
        Document document = getRequestsDb().getDocumentByUNID(refId);
        document.replaceItemValue("statusCode", statusCode);
        document.replaceItemValue("statusMessage", statusMessage);
        document.save(true, false);
        return null;
    }
}
