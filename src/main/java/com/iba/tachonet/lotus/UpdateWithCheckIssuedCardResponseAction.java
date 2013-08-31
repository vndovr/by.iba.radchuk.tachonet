package com.iba.tachonet.lotus;

import lotus.domino.Document;

import com.iba.tachonet.bean.CICTCN2MSSearchStatusCodeEnumType;
import com.iba.tachonet.bean.DriverDetailsCICType;
import com.iba.tachonet.bean.MSContactInfoType;
import com.iba.tachonet.bean.MSStatusCodeEnumType;
import com.iba.tachonet.bean.MemberStateType;
import com.iba.tachonet.bean.SearchedDriverTCN2MSResType;
import com.iba.tachonet.bean.StatusCodeEnumType;

/**
 * @author Vladimir Radchuk
 * 
 */
class UpdateWithCheckIssuedCardResponseAction extends LotusAction<Object> {
    private final String refId;
    private final StatusCodeEnumType statusCode;
    private final String statusMessage;
    private final SearchedDriverTCN2MSResType item;

    /**
     * Default constructor
     * 
     * @param item
     * @param statusMessage
     * @param statusCode
     * @param refId
     * 
     */
    public UpdateWithCheckIssuedCardResponseAction(String refId,
            StatusCodeEnumType statusCode, String statusMessage,
            SearchedDriverTCN2MSResType item) {
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
        log.info("UpdateWithCheckIssuedCardResponseAction.execute()");

        Document document = getRequestsDb().getDocumentByUNID(refId);
        document.replaceItemValue("statusCode", statusCode);
        document.replaceItemValue("statusMessage", statusMessage);

        if (statusCode == StatusCodeEnumType.OK) {

            CICTCN2MSSearchStatusCodeEnumType searchStatusCode = item
                    .getSearchStatusCode();

            String searchStatusMessage = item.getSearchStatusMessage();

            document.replaceItemValue("searchStatusCode", searchStatusCode);
            document.replaceItemValue("searchStatusMessage",
                    searchStatusMessage);

            if ((searchStatusCode == CICTCN2MSSearchStatusCodeEnumType.NOT_YET_CONNECTED
                    || searchStatusCode == CICTCN2MSSearchStatusCodeEnumType.TIMEOUT
                    || searchStatusCode == CICTCN2MSSearchStatusCodeEnumType.NOT_AVAILABLE || searchStatusCode == CICTCN2MSSearchStatusCodeEnumType.SERVER_ERROR)
                    && (item.getMemberState().size() != 0)) {

                for (MemberStateType memberState : item.getMemberState()) {

                    MSStatusCodeEnumType msStatusCode = memberState
                            .getMSStatusCode();
                    String msStatusMessage = memberState.getMSStatusMessage();

                    if (msStatusCode == MSStatusCodeEnumType.NOT_AVAILABLE
                            || msStatusCode == MSStatusCodeEnumType.NOT_YET_CONNECTED
                            || msStatusCode == MSStatusCodeEnumType.TIMEOUT
                            || msStatusCode == MSStatusCodeEnumType.SERVER_ERROR) {

                        MSContactInfoType msc = memberState.getMSContactInfo();
                    } else {
                        String memberStateCode = memberState
                                .getMemberStateCode();

                        for (DriverDetailsCICType ddc : memberState
                                .getDriverDetails()) {
                            ddc.getBirthDate();
                            ddc.getFirstName();
                            ddc.getSurname();
                            ddc.getPlaceOfBirth();

                            ddc.getDrivingLicenseDetails().getDLIssueDate();
                            ddc.getDrivingLicenseDetails().getDLIssuingNation();
                            ddc.getDrivingLicenseDetails().getDLNumber();
                            ddc.getDrivingLicenseDetails().getDLStatus();

                            ddc.getCardDetails().getAdditionalCardStatus();
                            ddc.getCardDetails()
                                    .getAdditionalStatusModifiedAt();
                            ddc.getCardDetails().getCardNumber();
                            ddc.getCardDetails().getCardStatus();
                            ddc.getCardDetails().getCIA();
                            ddc.getCardDetails().getExpiryDate();
                            ddc.getCardDetails().getStartOfValidityDate();
                            ddc.getCardDetails().getStatusModifiedAt();
                        }
                    }
                }
            }
        }

        document.save(true, false);

        return null;
    }
}
