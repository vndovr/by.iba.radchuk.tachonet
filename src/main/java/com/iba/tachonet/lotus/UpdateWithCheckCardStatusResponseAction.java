package com.iba.tachonet.lotus;

import lotus.domino.Document;

import com.iba.tachonet.bean.CCSTCN2MSSearchStatusCodeEnumType;
import com.iba.tachonet.bean.MSContactInfoType;
import com.iba.tachonet.bean.SearchCriteriaTCN2MSResType;
import com.iba.tachonet.bean.StatusCodeEnumType;

/**
 * @author Vladimir Radchuk
 */
class UpdateWithCheckCardStatusResponseAction extends LotusAction<Object> {
    private final String refId;
    private final StatusCodeEnumType statusCode;
    private final String statusMessage;
    private final SearchCriteriaTCN2MSResType item;

    /**
     * Default constructor
     * 
     * @param item
     * @param statusMessage
     * @param statusCode
     * @param refId
     */
    public UpdateWithCheckCardStatusResponseAction(String refId,
            StatusCodeEnumType statusCode, String statusMessage,
            SearchCriteriaTCN2MSResType item) {
        super();
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
        log.info("UpdateWithCheckCardStatusResponseAction.execute()");

        Document document = getRequestsDb().getDocumentByUNID(refId);
        document.replaceItemValue("statusCode", statusCode);
        document.replaceItemValue("statusMessage", statusMessage);

        if (statusCode == StatusCodeEnumType.OK) {
            CCSTCN2MSSearchStatusCodeEnumType searchStatusCode = item
                    .getSearchStatusCode();
            String searchStatusMessage = item.getSearchStatusMessage();

            document.replaceItemValue("searchStatusCode", searchStatusCode);
            document.replaceItemValue("searchStatusMessage",
                    searchStatusMessage);

            if (searchStatusCode == CCSTCN2MSSearchStatusCodeEnumType.NOT_AVAILABLE
                    || searchStatusCode == CCSTCN2MSSearchStatusCodeEnumType.NOT_YET_CONNECTED) {
                MSContactInfoType msci = item.getMSContactInfo();
                document
                        .replaceItemValue("msContactInfoEmail", msci.getEMail());
                document.replaceItemValue("msContactInfoFax", msci.getFax());
                document
                        .replaceItemValue("msContactInfoPhone", msci.getPhone());
            }
        }
        document.save(true, false);
        return null;
    }

}
