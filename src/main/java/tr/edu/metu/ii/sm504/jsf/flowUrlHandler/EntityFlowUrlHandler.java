package tr.edu.metu.ii.sm504.jsf.flowUrlHandler;

import org.springframework.webflow.context.servlet.DefaultFlowUrlHandler;
import org.springframework.webflow.core.collection.AttributeMap;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 08.03.2012
 * Time: 00:34
 * To change this template use File | Settings | File Templates.
 */
public class EntityFlowUrlHandler extends DefaultFlowUrlHandler {

    /**
     * URL stateful oldugu icin SpringWebFlow get parameterlerini URLde gostermiyor
     * Ama bu handler sayesinde parametreleri URL'e tekrar yaziyorum
     *
     * @param flowId
     * @param flowExecutionKey
     * @param request
     * @return
     */
    public String createFlowExecutionUrl(String flowId, String flowExecutionKey, HttpServletRequest request){
        final StringBuffer url = new StringBuffer(super.createFlowExecutionUrl(flowId, flowExecutionKey, request));
        Map input = new HashMap();
        Iterator entries = request.getParameterMap().entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object[]> entry = (Map.Entry) entries.next();
            if (!entry.getKey().equals("execution")) {
                input.put(entry.getKey(), entry.getValue()[0]);
            }
        }

        if (input != null && !input.isEmpty()) {
            url.append('&');
            appendQueryParameters(url, input, getEncodingScheme(request));
        }

        return url.toString();
    }
}
