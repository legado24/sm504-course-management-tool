package tr.edu.metu.ii.sm504.security;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 28.02.2012
 * Time: 15:02
 * To change this template use File | Settings | File Templates.
 */

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import tr.edu.metu.ii.sm504.util.ApplicationUtil;

import javax.annotation.Resource;
import javax.faces.bean.ApplicationScoped;

@Service("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService {

    @Resource(name = "authenticationManager")
    private AuthenticationManager authenticationManager; // specific for Spring Security

    @Override
    public boolean login(String username, String password) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        username, password));
        if (authenticate.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            return true;
        }

        return false;
    }

    @Override
    public void logout() {
        SecurityContextHolder.clearContext();
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }
}
