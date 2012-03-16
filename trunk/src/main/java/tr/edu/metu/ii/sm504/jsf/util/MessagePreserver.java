package tr.edu.metu.ii.sm504.jsf.util;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 15.03.2012
 * Time: 06:24
 * To change this template use File | Settings | File Templates.
 */

import org.springframework.binding.message.DefaultMessageContext;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.FlowExecutionListenerAdapter;
import org.springframework.webflow.execution.FlowSession;
import org.springframework.webflow.execution.RequestContext;

import java.io.Serializable;

public class MessagePreserver extends FlowExecutionListenerAdapter  {
    public static final String PRESERVE_MESSAGES = "PRESERVE_MESSAGES";
    private static final String PRESERVED_FLOW_MESSAGES = "PRESERVED_FLOW_MESSAGES";


    public void sessionStarting(RequestContext requestContext,
                                FlowSession flowSession, MutableAttributeMap attributeMap) {
        Serializable preservedMessages = (Serializable)
                requestContext.getExternalContext().getSessionMap().get(PRESERVED_FLOW_MESSAGES);

        if (preservedMessages != null) {
            DefaultMessageContext currentMessageContext =
                    (DefaultMessageContext) requestContext.getMessageContext();
            currentMessageContext.restoreMessages(preservedMessages);
            requestContext.getExternalContext().getSessionMap().remove(PRESERVED_FLOW_MESSAGES);
        }
    }

    public void sessionEnding(RequestContext requestContext,
                              FlowSession flowSession, String string, MutableAttributeMap attributeMap) {

        String preserveMessages = (String) requestContext.getCurrentState().getAttributes().get(PRESERVE_MESSAGES, "false");
        if ("true".equals(preserveMessages)) {
            Serializable messageSnapshot =
                    ((DefaultMessageContext) requestContext.getMessageContext()).createMessagesMemento();

            requestContext.getExternalContext().getSessionMap().
                    put(PRESERVED_FLOW_MESSAGES, messageSnapshot);
        }
    }
}
