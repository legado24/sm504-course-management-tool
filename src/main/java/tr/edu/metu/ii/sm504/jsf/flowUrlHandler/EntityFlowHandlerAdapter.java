package tr.edu.metu.ii.sm504.jsf.flowUrlHandler;

import org.springframework.faces.webflow.JsfFlowHandlerAdapter;
import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.core.collection.LocalAttributeMap;
import org.springframework.webflow.core.collection.MutableAttributeMap;
import org.springframework.webflow.execution.repository.NoSuchFlowExecutionException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 08.03.2012
 * Time: 05:24
 * To change this template use File | Settings | File Templates.
 */
public class EntityFlowHandlerAdapter extends JsfFlowHandlerAdapter {

    /**
     * URL bookmark ile ulasilirsa request parametreleri kaybetmeden yeni bir execution token olusturuyor
     *
     * @param flowId
     * @param e
     * @param request
     * @param response
     * @throws IOException
     */
    protected void defaultHandleException(String flowId, FlowException e, HttpServletRequest request,
                                          HttpServletResponse response) throws IOException {
        if (e instanceof NoSuchFlowExecutionException && flowId != null) {
            if (!response.isCommitted()) {
                Map parameterMap = new HashMap();
                Iterator entries = request.getParameterMap().entrySet().iterator();
                while (entries.hasNext()) {
                    Map.Entry<String, Object[]> entry = (Map.Entry) entries.next();
                    if (!(entry.getKey().equals("execution")
                            || entry.getKey().startsWith("javax.faces."))) {
                        parameterMap.put(entry.getKey(), entry.getValue()[0]);
                    }
                }
                MutableAttributeMap parameters = new LocalAttributeMap(parameterMap);
                // by default, attempt to restart the flow
                String flowUrl = getFlowUrlHandler().createFlowDefinitionUrl(flowId, parameters, request);
                sendRedirect(flowUrl, request, response);
            }
        } else {
            throw e;
        }
    }
}
