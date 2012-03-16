package tr.edu.metu.ii.sm504.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.DelegatingFilterProxy;
import tr.edu.metu.ii.sm504.util.SpringPropertiesUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 29.02.2012
 * Time: 13:34
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationDelegatingFilterProxy extends DelegatingFilterProxy{

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String loginDisabled = SpringPropertiesUtil.getProperty("login.disabled");
        boolean isLoginDisabled = false;
        if (StringUtils.isNotEmpty(loginDisabled) && loginDisabled.equalsIgnoreCase("true")) {
            isLoginDisabled = true;
        }

        if (!isLoginDisabled) {
            super.doFilter(request, response, filterChain);
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
