package tr.edu.metu.ii.sm504.jsf.flowUrlHandler;

import org.springframework.web.util.WebUtils;
import org.springframework.webflow.mvc.servlet.FlowHandlerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 08.03.2012
 * Time: 05:24
 * To change this template use File | Settings | File Templates.
 */
public class EntityIdFlowHandlerAdapter extends FlowHandlerAdapter{
    @Override
    protected void sendRedirect(String url, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String encodingScheme = request.getCharacterEncoding();
        if (encodingScheme == null) {
            encodingScheme = WebUtils.DEFAULT_CHARACTER_ENCODING;
        }

        //appendQueryParameters(new StringBuffer(url), request.getParameterMap(), encodingScheme);

        super.sendRedirect(url, request, response);    //To change body of overridden methods use File | Settings | File Templates.
    }

    protected void appendQueryParameters(StringBuffer url, Map parameters, String encodingScheme) {
        Iterator entries = parameters.entrySet().iterator();
        while (entries.hasNext()) {
            Map.Entry entry = (Map.Entry) entries.next();
            appendQueryParameter(url, entry.getKey(), entry.getValue(), encodingScheme);
            if (entries.hasNext()) {
                url.append('&');
            }
        }
    }

    private void appendQueryParameter(StringBuffer url, Object key, Object value, String encodingScheme) {
        String encodedKey = encode(key, encodingScheme);
        String encodedValue = encode(value, encodingScheme);
        url.append(encodedKey).append('=').append(encodedValue);
    }

    private String encode(Object value, String encodingScheme) {
        return value != null ? urlEncode(value.toString(), encodingScheme) : "";
    }

    private String urlEncode(String value, String encodingScheme) {
        try {
            return URLEncoder.encode(value, encodingScheme);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException("Cannot url encode " + value);
        }
    }

}
