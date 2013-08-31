package com.iba.tachonet.lotus.agent;

import javax.xml.bind.JAXBElement;

import lotus.domino.Document;

import com.iba.tachonet.IncomingMessage;
import com.iba.tachonet.message.RequestProcessorVisitor;
import com.iba.tachonet.utils.MessageUtils;

/**
 * @author Vladimir Radchuk
 * 
 */
class TCN2MSBatchAgent extends DocumentProcessorAgent {
    private static final String TCN2MSBatchAgentView = "view";

    /**
     * @param view
     */
    public TCN2MSBatchAgent() {
        super(TCN2MSBatchAgentView);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.iba.tachonet.lotus.agent.DocumentProcessorAgent#process(lotus.domino
     * .Document)
     */
    @Override
    protected void process(Document document) throws Exception {
        ((IncomingMessage) ((JAXBElement<?>) MessageUtils.unmarshall(document
                .getItemValueString("xml"))).getValue())
                .accept(new RequestProcessorVisitor());

        // mark document status as XML processed
    }
}
