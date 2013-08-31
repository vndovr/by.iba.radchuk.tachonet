package com.iba.tachonet;

import com.iba.tachonet.lotus.LotusDAO;

/**
 * @author Vladimir Radchuk
 * 
 */
public final class ExecutionContext {

    private LotusDAO lotusDAO = null;

    /**
     * Default constructor
     */
    public ExecutionContext() {
        super();
    }

    /**
     * Returns the Lotus Notes DAO object
     * 
     * @return
     */
    public LotusDAO getLotusDAO() {
        if (lotusDAO == null)
            lotusDAO = new LotusDAO();
        return lotusDAO;
    }
}
