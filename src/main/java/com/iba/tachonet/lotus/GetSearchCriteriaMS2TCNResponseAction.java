package com.iba.tachonet.lotus;

import javax.xml.datatype.DatatypeFactory;

import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.View;

import com.iba.tachonet.bean.CCSMS2TCNSearchStatusCodeEnumType;
import com.iba.tachonet.bean.CardDetailsCCSType;
import com.iba.tachonet.bean.CardStatusCodeEnumType;
import com.iba.tachonet.bean.DriverDetailsCCSType;
import com.iba.tachonet.bean.DrivingLicenseDetailsType;
import com.iba.tachonet.bean.ObjectFactory;
import com.iba.tachonet.bean.SearchCriteriaMS2TCNResType;
import com.iba.tachonet.bean.SearchCriteriaType;
import com.iba.tachonet.bean.WorkshopDetailsType;

/**
 * @author Vladimir Radchuk
 * 
 */
class GetSearchCriteriaMS2TCNResponseAction extends
        LotusAction<SearchCriteriaMS2TCNResType> {
    private static final String SearchedCriteriaView = "l_TCN_Cards";

    private final ObjectFactory objectFactory;
    private final DatatypeFactory datatypeFactory;
    private final SearchCriteriaType item;

    /**
     * @param item
     * @param objectFactory
     * 
     */
    public GetSearchCriteriaMS2TCNResponseAction(ObjectFactory objectFactory,
            DatatypeFactory datatypeFactory, SearchCriteriaType item) {
        super();
        this.objectFactory = objectFactory;
        this.datatypeFactory = datatypeFactory;
        this.item = item;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.lotus.LotusAction#execute()
     */
    @Override
    public SearchCriteriaMS2TCNResType execute() throws Exception {
        SearchCriteriaMS2TCNResType result = objectFactory
                .createSearchCriteriaMS2TCNResType();
        log.info("GetSearchCriteriaMS2TCNResponseAction.execute()");

        result.setCardNumber(item.getCardNumber());

        try {
            performSearch(result);
        } catch (NotesException e) {
            log.error("performSearch()", e);
            result
                    .setSearchStatusCode(CCSMS2TCNSearchStatusCodeEnumType.SERVER_ERROR);
            result.setSearchStatusMessage(e.getMessage());
        }

        return result;
    }

    /**
     * Performs the search in lotus notes database
     * 
     * @param result
     * @throws NotesException
     */
    private void performSearch(SearchCriteriaMS2TCNResType result)
            throws NotesException {
        View view = getCardsDb().getView(SearchedCriteriaView);
        Document document = view.getDocumentByKey(item.getCardNumber(), true);

        if (document == null) {
            result
                    .setSearchStatusCode(CCSMS2TCNSearchStatusCodeEnumType.NOT_FOUND);
            result.setSearchStatusMessage("");
        } else {
            result.setSearchStatusCode(CCSMS2TCNSearchStatusCodeEnumType.FOUND);
            result.setSearchStatusMessage("");

            CardDetailsCCSType cdc = objectFactory.createCardDetailsCCSType();
            result.setCardDetails(cdc);

            cdc.setCardStatus(CardStatusCodeEnumType.fromValue(document
                    .getItemValueString("Status")));
            // cdc.setAdditionalCardStatus(CardStatusCodeEnumType.fromValue(""));
            // not used
            cdc.setCIA(document.getItemValueString("CardIssuingAuthorityName"));

            cdc.setStartOfValidityDate(datatypeFactory
                    .newXMLGregorianCalendar(getGregorianCalendar(document,
                            "CardValidityBegin")));
            cdc.setExpiryDate(datatypeFactory
                    .newXMLGregorianCalendar(getGregorianCalendar(document,
                            "CardExpiryDate")));
            cdc.setStatusModifiedAt(datatypeFactory
                    .newXMLGregorianCalendar(getGregorianCalendar(document,
                            "LastApproveDate")));
            /*
             * cdc.setAdditionalStatusModifiedAt(datatypeFactory.newXMLGregorianCalendar
             * (getGregorianCalendar(document, "")));
             */

            if (document.getItemValueString("CardType").equals("Driver")) {
                DriverDetailsCCSType ddc = objectFactory
                        .createDriverDetailsCCSType();
                cdc.setDriverDetails(ddc);

                ddc
                        .setSurname(document
                                .getItemValueString("CardHolderSurname"));
                ddc.setFirstName(document
                        .getItemValueString("CardHolderFirstName"));
                ddc.setBirthDate(getFormattedDate(document,
                        "CardHolderBirthDate"));
                // ddc.setPlaceOfBirth(""); // not used

                DrivingLicenseDetailsType dlc = objectFactory
                        .createDrivingLicenseDetailsType();
                ddc.setDrivingLicenseDetails(dlc);

                dlc.setDLNumber(document
                        .getItemValueString("DrivingLicenceNumber"));
                dlc.setDLIssuingNation(document
                        .getItemValueString("DrivingLicenceIssuingNation"));

                // if the Member State is not able to check the DL, then it
                // should not send these attributes
                // dlc.setDLStatus(DLStatusCodeEnumType.VALID);
                // dlc.setDLIssueDate(datatypeFactory.newXMLGregorianCalendar(""));
            } else if (document.getItemValueString("CardType").equals(
                    "Workshop")) {
                WorkshopDetailsType wdc = objectFactory
                        .createWorkshopDetailsType();
                cdc.setWorkshopDetails(wdc);
                wdc.setWorkshopName(document.getItemValueString("CompanyName"));
                wdc.setWorkshopAddress(document
                        .getItemValueString("CompanyAddress"));
                wdc
                        .setSurname(document
                                .getItemValueString("CardHolderSurname"));
                wdc.setFirstName(document
                        .getItemValueString("CardHolderFirstName"));
                wdc.setBirthDate(getFormattedDate(document,
                        "CardHolderBirthDate"));
            }
        }
    }
}
