package com.iba.tachonet.message;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.configuration.ConfigurationException;

import com.iba.tachonet.IncomingMessage;
import com.iba.tachonet.utils.Resources;

/**
 * @author Vladimir Radchuk
 * 
 */
public class ThreadPool {

    private static Holder holder = getImpl();

    /**
     * Default constructor
     */
    private ThreadPool() {
        super();
    }

    /**
     * Log the xml message to Notes database or log file
     * 
     * @param xml
     */
    public static void logXmlMessage(String xml, String consumer) {
        holder.execute(new MessageLoggerThread(xml, consumer));
    }

    /**
     * Processes the incoming message
     * 
     * @param message
     */
    public static void processIncomingMessage(IncomingMessage message) {
        holder.execute(new RequestProcessorThread(message));
    }

    /**
     * @return
     */
    private static Holder getImpl() {
        try {
            return new Active();
        } catch (Exception e) {
            return new Failed(e);
        }
    }

    /**
     * @author Vladimir Radchuk
     * 
     */
    private interface Holder {
        public void execute(Runnable runnable);
    }

    /**
     * @author Vladimir Radchuk
     * 
     */
    private static class Active implements Holder {
        private final ExecutorService threadPool;

        /**
         * @throws ConfigurationException
         */
        Active() {
            super();
            threadPool = Resources.isSystemThreadPoolEnabled() ? Executors
                    .newFixedThreadPool(Resources.getSystemPoolThreadsCount())
                    : null;
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * com.iba.tachonet.message.ThreadPool.Holder#execute(java.lang.Runnable
         * )
         */
        public void execute(Runnable runnable) {
            if (Resources.isSystemThreadPoolEnabled())
                threadPool.submit(runnable);
            else
                runnable.run();
        }
    }

    /**
     * @author Vladimir Radchuk
     * 
     */
    private static class Failed implements Holder {
        private final RuntimeException e;

        /**
         * @param e
         */
        public Failed(Exception e) {
            super();
            this.e = e instanceof RuntimeException ? (RuntimeException) e
                    : new RuntimeException(e);
        }

        /*
         * (non-Javadoc)
         * 
         * @see
         * com.iba.tachonet.message.ThreadPool.Holder#execute(java.lang.Runnable
         * )
         */
        public void execute(Runnable runnable) {
            throw e;
        }
    }
}
