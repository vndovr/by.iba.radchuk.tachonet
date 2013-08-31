package com.iba.tachonet.lotus;

import lotus.domino.Document;

/**
 * @author Vladimir Radchuk
 * 
 */
class LogXmlMessageAction extends LotusAction<Object> {

    private final String xml;
    private final String consumer;

    /**
     * Default constructor
     * 
     * @param xml
     */
    public LogXmlMessageAction(String xml, String consumer) {
        super();
        this.xml = xml;
        this.consumer = consumer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.iba.tachonet.lotus.LotusAction#execute()
     */
    @Override
    public Object execute() throws Exception {
        log.info("LogXmlMessageAction.execute()");
        Document doc = getLogDb().createDocument();
        doc.appendItemValue("form", "MessageXML");
        doc.appendItemValue("xml", xml);
        doc.appendItemValue("consumer", consumer);
        doc.save();
        return null;
    }
}
