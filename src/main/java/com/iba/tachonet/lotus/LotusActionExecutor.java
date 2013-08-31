package com.iba.tachonet.lotus;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author Vladimir Radchuk
 * 
 */
public class LotusActionExecutor {

    private static final Log log = LogFactory.getLog(LotusActionExecutor.class);

    /**
     * Default constructor
     */
    protected LotusActionExecutor() {
        super();
    }

    /**
     * Executes the lotus action object and returns the result back
     * 
     * @param action
     * @return
     * @throws Exception
     */
    protected <T> T executeLotusAction(LotusAction<T> action) throws Exception {
        try {
            action.connect();
            return action.execute();
        } finally {
            try {
                action.disconnect();
            } catch (Exception e) {
                log.error("executeLotusAction()", e);
            }
        }
    }
}
