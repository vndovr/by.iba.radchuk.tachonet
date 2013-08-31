package com.iba.tachonet.message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iba.tachonet.lotus.LotusDAO;
import com.iba.tachonet.utils.Resources;

/**
 * @author Vladimir Radchuk
 * 
 */
class MessageLoggerThread implements Runnable {
    private static final Log log = LogFactory.getLog(MessageLoggerThread.class);

    private final String xml;
    private final String consumer;

    /**
     * Default constructor
     * 
     * @param xml
     * @param consumer
     */
    public MessageLoggerThread(String xml, String consumer) {
        super();
        this.xml = xml;
        this.consumer = consumer;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
        if (!(Resources.isSystemLogToNotesEnabled() && logXmlToNotes(xml)))
            log.info("XML [" + consumer + "]: \n" + xml);
    }

    /**
     * Logs xml message to Lotus notes database
     * 
     * @param xml
     * @return
     */
    private boolean logXmlToNotes(String xml) {
        try {
            new LotusDAO().logXmlMessage(xml, consumer);
            return true;
        } catch (Exception e) {
            log.error("MessageLoggerThread.run()", e);
            return false;
        }
    }

}
