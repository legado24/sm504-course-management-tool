package tr.edu.metu.ii.sm504.jsf.flowUrlHandler;

import org.springframework.webflow.context.servlet.DefaultFlowUrlHandler;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 08.03.2012
 * Time: 00:34
 * To change this template use File | Settings | File Templates.
 */
public class EntityIdFlowUrlHandler extends DefaultFlowUrlHandler {
    public String createFlowExecutionUrl(String flowId, String flowExecutionKey, HttpServletRequest request){
        final String initialUrl = super.createFlowExecutionUrl(flowId,flowExecutionKey,request);
        String id = request.getParameter("id");
        if (id != null) {
            final String urlWithId = addIdToUrl(initialUrl, id);
            return urlWithId;
        } else
            return initialUrl;
    }


    public String addIdToUrl(final String initialUrl, final String id)
    {
        final int posQuestionMark = initialUrl.indexOf("?");
        if (posQuestionMark == -1) {
            return initialUrl + "?id=" + id;
        }
        else { //questionMark exists
            final String firstPartUrl = initialUrl.substring(0, posQuestionMark);
            final String secondPartUrl = initialUrl.substring(posQuestionMark);
            return firstPartUrl +secondPartUrl + "&id="+ id;
        }
    }
}
