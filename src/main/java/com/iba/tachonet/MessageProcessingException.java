package com.iba.tachonet;

/**
 * @author Vladimir Radchuk
 * 
 */
public class MessageProcessingException extends Exception {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor
     */
    public MessageProcessingException() {
        super();
    }

    /**
     * Default constructor
     * 
     * @param message
     */
    public MessageProcessingException(String message) {
        super(message);
    }

    /**
     * Default constructor
     * 
     * @param cause
     */
    public MessageProcessingException(Throwable cause) {
        super(cause);
    }

    /**
     * Default constructor
     * 
     * @param message
     * @param cause
     */
    public MessageProcessingException(String message, Throwable cause) {
        super(message, cause);
    }
}
