package com.iba.tachonet.message;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.iba.tachonet.IncomingMessage;

/**
 * @author Vladimir Radchuk
 * 
 */
public class RequestProcessorThread implements Runnable {
    private static final Log log = LogFactory.getLog(RequestProcessorThread.class);
    
    private final IncomingMessage message;

    /**
     * Default constructor
     * 
     * @param message
     * 
     */
    public RequestProcessorThread(IncomingMessage message) {
        super();
        this.message = message;
    }

    /*
     * (non-Javadoc)
     * 
     * @see java.lang.Runnable#run()
     */
    public void run() {
        try {
            message.accept(new RequestProcessorVisitor());
        } catch (Exception e) {
            log.error("RequestProcessorThread.run()", e);
        }
    }

}
