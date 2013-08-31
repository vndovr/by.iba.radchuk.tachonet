package com.iba.tachonet.lotus.agent;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vladimir Radchuk
 * 
 */
public class AgentExecutionResult implements Serializable {
    private static final long serialVersionUID = 1L;

    private Set<String> successful = new HashSet<String>();
    private Set<String> failed = new HashSet<String>();

    /**
     * Default constructor
     */
    public AgentExecutionResult() {
        super();
    }

    /**
     * @param docUNID
     */
    public void addSuccessful(String docUNID) {
        successful.add(docUNID);
    }

    /**
     * @param docUNID
     */
    public void addFailed(String docUNID) {
        failed.add(docUNID);
    }

    /**
     * @return
     */
    public Set<String> getSuccessful() {
        return successful;
    }

    /**
     * @return
     */
    public Set<String> getFailed() {
        return failed;
    }

    /**
     * @return
     */
    public int getSuccessfullCount() {
        return successful.size();
    }

    /**
     * @return
     */
    public int getFailedCount() {
        return failed.size();
    }
}
