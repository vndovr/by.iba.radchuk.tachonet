package com.iba.tachonet.lotus;

import javax.xml.datatype.DatatypeFactory;

import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.View;

import com.iba.tachonet.bean.CICMS2TCNSearchStatusCodeEnumType;
import com.iba.tachonet.bean.CardDetailsCICType;
import com.iba.tachonet.bean.CardStatusCodeEnumType;
import com.iba.tachonet.bean.DLStatusCodeEnumType;
import com.iba.tachonet.bean.DriverDetailsCICType;
import com.iba.tachonet.bean.DrivingLicenseDetailsType;
import com.iba.tachonet.bean.ObjectFactory;
import com.iba.tachonet.bean.SearchedDriverMS2TCNResType;
import com.iba.tachonet.bean.SearchedDriverTCN2MSReqType;

/**
 * @author Vladimir Radchuk
 * 
 */
class GetSearchedDriverMS2TCNResponseAction extends
        LotusAction<SearchedDriverMS2TCNResType> {

    private static final String SearchedDriverMS2TCNView = "Hernya_kakayato";

    private final ObjectFactory objectFactory;
    private final DatatypeFactory datatypeFactory;
    private final SearchedDriverTCN2MSReqType item;

    /**
     * Default constructor
     * 
     * @param item
     * @param objectFactory
     */
    public GetSearchedDriverMS2TCNResponseAction(ObjectFactory objectFactory,
            DatatypeFactory datatypeFactory, SearchedDriverTCN2MSReqType item) {
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
    public SearchedDriverMS2TCNResType execute() throws Exception {
        log.info("GetSearchedDriverMS2TCNResponseAction.execute()");

        SearchedDriverMS2TCNResType result = objectFactory
                .createSearchedDriverMS2TCNResType();

        result.setBirthDate(item.getBirthDate());
        result.setDrivingLicenseIssuingNation(item
                .getDrivingLicenseIssuingNation());
        result.setDrivingLicenseNumber(item.getDrivingLicenseNumber());
        result.setFirstName(item.getFirstName());
        result.setFirstNameSearchKey(item.getFirstNameSearchKey());
        result.setPlaceOfBirth(item.getPlaceOfBirth());
        result.setSurname(item.getSurname());
        result.setSurnameSearchKey(item.getSurnameSearchKey());

        try {
            performSearch(result);

        } catch (NotesException e) {
            log.error("performSearch()", e);
            result
                    .setSearchStatusCode(CICMS2TCNSearchStatusCodeEnumType.SERVER_ERROR);
            result.setSearchStatusMessage(e.getMessage());
        }

        return result;
    }

    /**
     * Performs the search of the record in lotus notes database
     * 
     * @param result
     * @throws NotesException
     */
    private void performSearch(SearchedDriverMS2TCNResType result)
            throws NotesException {
        View view = getCardsDb().getView(SearchedDriverMS2TCNView);

        Document document = view.getDocumentByKey(buildKey(item.getBirthDate(),
                item.getDrivingLicenseIssuingNation(), item
                        .getDrivingLicenseNumber(), item.getFirstName(), item
                        .getFirstNameSearchKey(), item.getPlaceOfBirth(), item
                        .getSurname(), item.getSurnameSearchKey()), true);

        if (document == null) {
            result
                    .setSearchStatusCode(CICMS2TCNSearchStatusCodeEnumType.NOT_FOUND);
            result
                    .setSearchStatusMessage("Record not found in Belarusian lotus notes database.");
        } else {
            DriverDetailsCICType ddc = objectFactory
                    .createDriverDetailsCICType();
            CardDetailsCICType cdc = objectFactory.createCardDetailsCICType();
            DrivingLicenseDetailsType dld = objectFactory
                    .createDrivingLicenseDetailsType();
            ddc.setCardDetails(cdc);
            ddc.setDrivingLicenseDetails(dld);
            result.getDriverDetails().add(ddc);

            ddc.setBirthDate("");
            ddc.setFirstName("");
            ddc.setPlaceOfBirth("");
            ddc.setSurname("");

            cdc.setAdditionalCardStatus(CardStatusCodeEnumType.APPLICATION);
            cdc.setAdditionalStatusModifiedAt(null);
            cdc.setCardNumber("");
            cdc.setCardStatus(CardStatusCodeEnumType.APPLICATION);
            cdc.setCIA("");
            cdc.setExpiryDate(null);
            cdc.setStartOfValidityDate(null);
            cdc.setStatusModifiedAt(null);

            // sample how to set date
            dld.setDLIssueDate(datatypeFactory
                    .newXMLGregorianCalendar("2005-08-15T16:13:03+0000"));

            dld.setDLIssuingNation("");
            dld.setDLNumber("");
            dld.setDLStatus(DLStatusCodeEnumType.INVALID);

            result.setSearchStatusCode(CICMS2TCNSearchStatusCodeEnumType.FOUND);
            result.setSearchStatusMessage("Yes, I did again");
        }
    }
}
