package tr.edu.metu.ii.sm504.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import tr.edu.metu.ii.sm504.util.ApplicationUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 28.02.2012
 * Time: 15:00
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "loginBean")
@SessionScoped
@Service
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    @Autowired
    private AuthenticationService authenticationService;

    public String login() {
        try{

            boolean success = authenticationService.login(username, password);
            if (success) {
                return "success";
                //return "index.html"; // return to application but being logged now
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username or password incorrect."));
                return "failure";
                //return "login.xhtml";
            }
        }catch (LockedException e){
            ApplicationUtil.handleExceptionForUI("Your account has been locked");
        }catch (AccountExpiredException e){
            ApplicationUtil.handleExceptionForUI("Your account has expired");
        }catch (CredentialsExpiredException e){
            ApplicationUtil.handleExceptionForUI("Your credentials have expired");
        }catch (AuthenticationException e){
            ApplicationUtil.handleExceptionForUI("Invalid username or password");
        }catch (Throwable t){
            ApplicationUtil.handleExceptionForUI(t, "Internal error occured");
        }

        return "failure";
    }


    public String logout(){
        authenticationService.logout();
        return "logout";
    }
    
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
