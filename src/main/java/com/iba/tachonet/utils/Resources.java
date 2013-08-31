package com.iba.tachonet.utils;

import java.math.BigInteger;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

/**
 * @author Vladimir Radchuk
 * 
 */
public class Resources {

    private static Holder holder = getImpl();

    /**
     * Default constructor
     */
    protected Resources() {
        super();
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
     * Resutrns the message handler addres of TachoNET system
     * 
     * @return
     */
    public static String getTCNMessageHandlerAddress() {
        return holder.getConfiguration().getString("TCN.MessageHandlerAddress");
    }

    /**
     * Resutrns the message handler addres of member's system
     * 
     * @return
     */
    public static String getMemberMessageHandlerAddress() {
        return holder.getConfiguration().getString(
                "Member.MessageHandlerAddress");
    }

    /**
     * Returns the end point for GetPhonexSearchKeys service
     * 
     * @return
     */
    public static String getTCNWSEndPointTransliterateToUSAscii() {
        return holder.getConfiguration().getString(
                "TCN.WSEndPoint.TransliterateToUSAscii");
    }

    /**
     * Returns the end point for GetPhonexSearchKeys service
     * 
     * @return
     */
    public static String getTCNWSEndPointGetPhonexSearchKeysService() {
        return holder.getConfiguration().getString(
                "TCN.WSEndPoint.GetPhonexSearchKeys");
    }

    /**
     * Returns the header version information
     * 
     * @return
     */
    public static String getTCNRequestHeaderVersion() {
        return holder.getConfiguration().getString("TCN.RequestHeader.Version",
                "1.4");
    }

    /**
     * Returns the header online timeout value
     * 
     * @return
     */
    public static BigInteger getTCNRequestHeaderOnlineTimeout() {
        return holder.getConfiguration().getBigInteger(
                "TCN.RequestHeader.OnlineTimeout", BigInteger.valueOf(60));
    }

    /**
     * Returns the header batch timeout value
     * 
     * @return
     */
    public static BigInteger getTCNRequestHeaderBatchTimeout() {
        return holder.getConfiguration().getBigInteger(
                "TCN.RequestHeader.BatchTimeout", BigInteger.valueOf(172800));
    }

    /**
     * Returns the header From field value
     * 
     * @return
     */
    public static String getTCNRequestHeaderSender() {
        return holder.getConfiguration().getString("TCN.RequestHeader.Sender");
    }

    /**
     * Returns the header To field value
     * 
     * @return
     */
    public static String getTCNRequestHeaderReceiver() {
        return holder.getConfiguration()
                .getString("TCN.RequestHeader.Receiver");
    }

    /**
     * Returns true if special batch processing is enabled
     * 
     * @return
     */
    public static boolean isSystemBatchProcessingEnabled() {
        return holder.getConfiguration().getBoolean(
                "System.EnableBatchProcessing", false);
    }

    /**
     * Returns true if log of incoming messages is enabled
     * 
     * @return
     */
    public static boolean isSystemLogIncomingMessagesEnabled() {
        return holder.getConfiguration().getBoolean(
                "System.Log.IncomingMessages", true);
    }

    /**
     * Returns true if log of outgoing messages is enabled
     * 
     * @return
     */
    public static boolean isSystemLogOutgoingMessagesEnabled() {
        return holder.getConfiguration().getBoolean(
                "System.Log.OutgoingMessages", true);
    }

    /**
     * Returns true if system should log messages to Domino server
     * 
     * @return
     */
    public static boolean isSystemLogToNotesEnabled() {
        return holder.getConfiguration()
                .getBoolean("System.Log.ToNotes", false);
    }

    /**
     * Returns true if system should generate test id for all requests to TCN
     * 
     * @return
     */
    public static boolean isTCNSystemTestModeEnabled() {
        return holder.getConfiguration()
                .getBoolean("TCN.System.TestMode", true);
    }

    /**
     * Returns the test id for TCN requests in test mode
     * 
     * @return
     */
    public static String getTCNRequestTestId() {
        return holder.getConfiguration().getString("TCN.Request.TestId",
                "BY2TCNTestId");
    }

    /**
     * @return
     */
    public static int getSystemPoolThreadsCount() {
        return holder.getConfiguration()
                .getInt("System.Pool.Threads.Count", 30);
    }

    /**
     * @return
     */
    public static boolean isSystemThreadPoolEnabled() {
        return holder.getConfiguration().getBoolean(
                "System.Thread.Pool.Enabled", false);
    }

    /**
     * @author Vladimir Radchuk
     * 
     */
    private interface Holder {
        public Configuration getConfiguration() throws RuntimeException;
    }

    /**
     * @author Vladimir Radchuk
     * 
     */
    private static class Active implements Holder {
        private final Configuration c;

        /**
         * @throws ConfigurationException
         */
        Active() throws ConfigurationException {
            super();
            c = new PropertiesConfiguration("system.properties");
        }

        /*
         * (non-Javadoc)
         * 
         * @see com.iba.tachonet.utils.Resources.Holder#getConfiguration()
         */
        public Configuration getConfiguration() throws RuntimeException {
            return c;
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
         * @see com.iba.tachonet.utils.Resources.Holder#getConfiguration()
         */
        public Configuration getConfiguration() throws RuntimeException {
            throw e;
        }
    }
}