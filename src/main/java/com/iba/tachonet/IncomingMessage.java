package com.iba.tachonet;

/**
 * @author Vladimir Radchuk
 * 
 */
public interface IncomingMessage {
    public void accept(IncomingMessageVisitor visitor)
            throws MessageProcessingException;
}
