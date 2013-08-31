package com.iba.tachonet.lotus;

import lotus.domino.Document;

/**
 * @author Vladimir Radchuk
 * 
 */
class PersistForBatchProcessingAction extends LotusAction<Object> {

    private final String xml;

    /**
     * Default constructor
     * 
     * @param xml
     */
    public PersistForBatchProcessingAction(String xml) {
        super();
        this.xml = xml;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.lotus.LotusAction#execute()
     */
    @Override
    public Object execute() throws Exception {
        log.info("PersistForBatchProcessingAction.execute()");
        Document doc = getLogDb().createDocument();
        doc.appendItemValue("form", "BatchProcessingXML");
        doc.appendItemValue("xml", xml);
        doc.save();
        return null;
    }

}
