package com.iba.tachonet;

/**
 * @author Vladimir Radchuk
 * 
 */
public interface Action {

    /**
     * method used to execute action
     */
    public void execute(ExecutionContext context) throws Exception;
}
