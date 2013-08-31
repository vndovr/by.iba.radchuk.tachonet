package com.iba.tachonet.lotus;

import lotus.domino.Document;

import com.iba.tachonet.bean.ICDLTCN2MSStatusCodeEnumType;
import com.iba.tachonet.bean.IssuedCardTCN2MSResType;
import com.iba.tachonet.bean.MSContactInfoType;
import com.iba.tachonet.bean.StatusCodeEnumType;

/**
 * @author Vladimir Radchuk
 * 
 */
class UpdateWithIssuedCardResponseAction extends LotusAction<Object> {
    private final String refId;
    private final StatusCodeEnumType statusCode;
    private final String statusMessage;
    private final IssuedCardTCN2MSResType item;

    /**
     * Default constructor
     * 
     * @param item
     * @param statusMessage
     * @param statusCode
     * @param refId
     * 
     */
    public UpdateWithIssuedCardResponseAction(String refId,
            StatusCodeEnumType statusCode, String statusMessage,
            IssuedCardTCN2MSResType item) {
        this.refId = refId;
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.item = item;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.lotus.LotusAction#execute()
     */
    @Override
    public Object execute() throws Exception {
        log.info("UpdateWithIssuedCardResponseAction.execute()");

        Document document = getRequestsDb().getDocumentByUNID(refId);
        document.replaceItemValue("statusCode", statusCode);
        document.replaceItemValue("statusMessage", statusMessage);

        if (statusCode == StatusCodeEnumType.OK) {
            ICDLTCN2MSStatusCodeEnumType issuedCardStatusCode = item
                    .getIssuedCardDLStatusCode();

            String issuedCardStatusMessage = item
                    .getIssuedCardDLStatusMessage();

            document.replaceItemValue("issuedCardStatusCode",
                    issuedCardStatusCode);
            document.replaceItemValue("issuedCardStatusMessage",
                    issuedCardStatusMessage);

            if (issuedCardStatusCode == ICDLTCN2MSStatusCodeEnumType.NOT_AVAILABLE
                    || issuedCardStatusCode == ICDLTCN2MSStatusCodeEnumType.NOT_YET_CONNECTED) {
                MSContactInfoType msci = item.getMSContactInfo();

                document
                        .replaceItemValue("msContactInfoEmail", msci.getEMail());
                document.replaceItemValue("msContactInfoFax", msci.getFax());
                document
                        .replaceItemValue("msContactInfoPhone", msci.getPhone());
            }
            item.getIssuedCardDLStatusMessage();
        }

        document.save(true, false);
        return null;
    }
}
