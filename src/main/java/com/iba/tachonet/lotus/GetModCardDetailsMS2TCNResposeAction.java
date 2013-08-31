package com.iba.tachonet.lotus;

import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.View;

import com.iba.tachonet.bean.MCSMS2TCNStatusCodeEnumType;
import com.iba.tachonet.bean.ModCardDetailsMS2TCNResType;
import com.iba.tachonet.bean.ModCardDetailsTCN2MSReqType;
import com.iba.tachonet.bean.ObjectFactory;

/**
 * @author Vladimir Radchuk
 * 
 */
class GetModCardDetailsMS2TCNResposeAction extends
        LotusAction<ModCardDetailsMS2TCNResType> {
    private static final String ModCardDetailsView = "Some view";
    private final ObjectFactory objectFactory;
    private final ModCardDetailsTCN2MSReqType item;

    /**
     * Default constructor
     * 
     * @param item
     * @param objectFactory
     */
    public GetModCardDetailsMS2TCNResposeAction(ObjectFactory objectFactory,
            ModCardDetailsTCN2MSReqType item) {
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
    public ModCardDetailsMS2TCNResType execute() throws Exception {
        ModCardDetailsMS2TCNResType result = objectFactory
                .createModCardDetailsMS2TCNResType();
        log.info("GetModCardDetailsMS2TCNResposeAction.execute()");

        result.setCardNumber(item.getCardNumber());
        result.setDeclaredBy(item.getDeclaredBy());
        result.setNewCardStatus(item.getNewCardStatus());
        result.setReason(item.getReason());
        result.setReasonCode(item.getReasonCode());
        result.setStatusModifiedAt(item.getStatusModifiedAt());

        try {
            performSearch(result);
        } catch (NotesException e) {
            log.error("performSearch()", e);
            result.setModStatusCode(MCSMS2TCNStatusCodeEnumType.SERVER_ERROR);
            result.setModStatusMessage(e.getMessage());
        }

        return result;
    }

    /**
     * Performs the search in lotus notes database
     * 
     * @param result
     * @throws NotesException
     */
    private void performSearch(ModCardDetailsMS2TCNResType result)
            throws NotesException {
        View view = getCardsDb().getView(ModCardDetailsView);
        Document document = view.getDocumentByKey(item.getCardNumber(), true);
        if (document == null) {

            result
                    .setModStatusCode(MCSMS2TCNStatusCodeEnumType.CARD_NUMBER_NOT_FOUND);
            result
                    .setModStatusMessage("Unfortunately I did not find the record");
        } else {
            result.setModStatusCode(MCSMS2TCNStatusCodeEnumType.OK);
        }
    }
}
