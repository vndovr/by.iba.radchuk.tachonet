package com.iba.tachonet;

/**
 * @author Vladimir Radchuk
 * 
 */
public final class Executor {

    public static final Executor executor = new Executor();

    /**
     * Default constructor
     */
    private Executor() {
        super();
    }

    /**
     * @param action
     * @throws Exception
     */
    public static void execute(Action action) throws Exception {
        action.execute(new ExecutionContext());
    }
}
