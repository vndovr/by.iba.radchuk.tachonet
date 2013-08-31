package com.iba.tachonet.lotus;

import lotus.domino.Document;

import com.iba.tachonet.bean.MCSTCN2MSStatusCodeEnumType;
import com.iba.tachonet.bean.MSContactInfoType;
import com.iba.tachonet.bean.ModCardDetailsTCN2MSResType;
import com.iba.tachonet.bean.StatusCodeEnumType;

/**
 * @author Vladimir Radchuk
 * 
 */
class UpdateWithModCardStatusResponseAction extends LotusAction<Object> {
    private final String refId;
    private final StatusCodeEnumType statusCode;
    private final String statusMessage;
    private final ModCardDetailsTCN2MSResType item;

    /**
     * Default constructor
     * 
     * @param item
     * @param statusMessage
     * @param statusCode
     * @param refId
     */
    public UpdateWithModCardStatusResponseAction(String refId,
            StatusCodeEnumType statusCode, String statusMessage,
            ModCardDetailsTCN2MSResType item) {
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
        log.info("UpdateWithModCardStatusResponseAction.execute()");

        Document document = getRequestsDb().getDocumentByUNID(refId);
        document.replaceItemValue("statusCode", statusCode);
        document.replaceItemValue("statusMessage", statusMessage);

        if (statusCode == StatusCodeEnumType.OK) {
            MCSTCN2MSStatusCodeEnumType modStatusCode = item.getModStatusCode();
            String modStatusMessage = item.getModStatusMessage();

            document.replaceItemValue("modStatusCode", modStatusCode);
            document.replaceItemValue("modStatusMessage", modStatusMessage);

            if (modStatusCode == MCSTCN2MSStatusCodeEnumType.NOT_AVAILABLE
                    || modStatusCode == MCSTCN2MSStatusCodeEnumType.NOT_YET_CONNECTED
                    || modStatusCode == MCSTCN2MSStatusCodeEnumType.TIMEOUT
                    || modStatusCode == MCSTCN2MSStatusCodeEnumType.SERVER_ERROR) {

                MSContactInfoType msc = item.getMSContactInfo();
                document.replaceItemValue("msContactInfoEmail", msc.getEMail());
                document.replaceItemValue("msContactInfoFax", msc.getFax());
                document.replaceItemValue("msContactInfoPhone", msc.getPhone());
            }
        }
        return null;
    }

}
