package com.iba.tachonet.lotus.agent;

import com.iba.tachonet.lotus.LotusActionExecutor;

/**
 * @author Vladimir Radchuk
 * 
 */
public class LotusAgentRunner extends LotusActionExecutor {

    /**
     * Default constructor
     */
    public LotusAgentRunner() {
        super();
    }

    /**
     * @return
     * @throws Exception
     */
    public AgentExecutionResult runMS2TCNCheckCardStatusAgent()
            throws Exception {
        return executeLotusAction(new MS2TCNCheckCardStatusAgent());
    }

    /**
     * @return
     * @throws Exception
     */
    public AgentExecutionResult runMS2TCNCheckIssuedCardsAgent()
            throws Exception {
        return executeLotusAction(new MS2TCNCheckIssuedCardsAgent());
    }

    /**
     * @return
     * @throws Exception
     */
    public AgentExecutionResult runMS2TCNIssuedCardDLAgent() throws Exception {
        return executeLotusAction(new MS2TCNIssuedCardDLAgent());
    }

    /**
     * @return
     * @throws Exception
     */
    public AgentExecutionResult runMS2TCNModCardStatusAgent() throws Exception {
        return executeLotusAction(new MS2TCNModCardStatusAgent());
    }

    /**
     * @return
     * @throws Exception
     */
    public AgentExecutionResult runTCN2MSBatchAgent() throws Exception {
        return executeLotusAction(new TCN2MSBatchAgent());
    }
}
