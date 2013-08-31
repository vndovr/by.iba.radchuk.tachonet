package com.iba.tachonet.lotus;

import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.View;

import com.iba.tachonet.bean.ICDLMS2TCNStatusCodeEnumType;
import com.iba.tachonet.bean.IssuedCardMS2TCNResType;
import com.iba.tachonet.bean.IssuedCardType;
import com.iba.tachonet.bean.ObjectFactory;

/**
 * @author Vladimir Radchuk
 * 
 */
class GetIssuedCardMS2TCNResponseAction extends
        LotusAction<IssuedCardMS2TCNResType> {

    private static final String IssuedCardDLView = "l_TCN_IssuedCard";

    private final ObjectFactory objectFactory;
    private final IssuedCardType item;

    public GetIssuedCardMS2TCNResponseAction(ObjectFactory objectFactory,
            IssuedCardType item) {
        super();
        this.objectFactory = objectFactory;
        this.item = item;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.lotus.LotusAction#execute()
     */
    @Override
    public IssuedCardMS2TCNResType execute() throws Exception {
        log.info("GetIssuedCardMS2TCNResponseAction.execute()");
        IssuedCardMS2TCNResType result = objectFactory
                .createIssuedCardMS2TCNResType();

        result.setDrivingLicenseNumber(item.getDrivingLicenseNumber());
        result.setIssuedCardNumber(item.getIssuedCardNumber());
        result.setIssuingMemberStateCode(item.getIssuingMemberStateCode());

        try {
            performSearch(result);
        } catch (NotesException e) {
            log.error("performSearch()", e);
            result
                    .setIssuedCardDLStatusCode(ICDLMS2TCNStatusCodeEnumType.SERVER_ERROR);
            result.setIssuedCardDLStatusMessage(e.getMessage());
        }
        return result;
    }

    /**
     * Pefrorms the search in lotus notes database Possible it will be - sendng
     * Ok and creating card if not created.
     * 
     * @param result
     * @throws NotesException
     */
    private void performSearch(IssuedCardMS2TCNResType result)
            throws NotesException {
        View view = getCardsDb().getView(IssuedCardDLView);
        Document document = view.getDocumentByKey(buildKey(item
                .getIssuedCardNumber(), item.getIssuingMemberStateCode(), item
                .getDrivingLicenseNumber()), true);

        if (document == null) {
            result
                    .setIssuedCardDLStatusCode(ICDLMS2TCNStatusCodeEnumType.DRIVING_LICENSE_NUMBER_NOT_FOUND);
            result
                    .setIssuedCardDLStatusMessage("Record not found in the database.");
        } else {
            if (document.getItemValueString("Status").equals("HandedOver")) {
                result
                        .setIssuedCardDLStatusCode(ICDLMS2TCNStatusCodeEnumType.OK);
                result.setIssuedCardDLStatusMessage("");
            } else {
                result
                        .setIssuedCardDLStatusCode(ICDLMS2TCNStatusCodeEnumType.DRIVING_LICENSE_NUMBER_INVALID);
                result.setIssuedCardDLStatusMessage("Card has status: "
                        + document.getItemValueString("Status"));
            }
        }
    }
}
