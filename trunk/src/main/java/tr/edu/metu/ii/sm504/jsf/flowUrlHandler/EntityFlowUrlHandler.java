package tr.edu.metu.ii.sm504.jsf.flowUrlHandler;

import org.apache.commons.lang3.StringUtils;
import org.springframework.webflow.context.servlet.DefaultFlowUrlHandler;
import org.springframework.webflow.core.collection.AttributeMap;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 08.03.2012
 * Time: 00:34
 * To change this template use File | Settings | File Templates.
 */
public class EntityFlowUrlHandler extends DefaultFlowUrlHandler {

    private static List EXCEPTIONAL_CASES = new ArrayList();

    static {
        EXCEPTIONAL_CASES.add("login-flow");
        EXCEPTIONAL_CASES.add("logout-flow");
    }
    
    /**
     * URL stateful oldugu icin SpringWebFlow get parameterlerini URLde gostermiyor
     * Ama bu handler sayesinde parametreleri URL'e tekrar yaziyorum
     *
     * @param flowId
     * @param flowExecutionKey
     * @param request
     * @return
     */
    public String createFlowExecutionUrl(String flowId, String flowExecutionKey, HttpServletRequest request) {
        final StringBuffer url = new StringBuffer(super.createFlowExecutionUrl(flowId, flowExecutionKey, request));

        if (!isAvailableForURLRewrite(flowId)) {
            return url.toString();
        }
        
        Map input = new HashMap();
        Iterator entries = request.getParameterMap().entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry<String, Object[]> entry = (Map.Entry) entries.next();
            if (!(entry.getKey().equals("execution")
                    || entry.getKey().equals("javax.faces.ViewState")
                    )) {
                input.put(entry.getKey(), entry.getValue()[0]);
            }
        }

        if (input != null && !input.isEmpty()) {
            url.append('&');
            appendQueryParameters(url, input, getEncodingScheme(request));
        }

        return url.toString();
    }

    private boolean isAvailableForURLRewrite(String flowId) {
        if (StringUtils.isEmpty(flowId)) {
            return false;
        }
        return !EXCEPTIONAL_CASES.contains(flowId);
    }
}
