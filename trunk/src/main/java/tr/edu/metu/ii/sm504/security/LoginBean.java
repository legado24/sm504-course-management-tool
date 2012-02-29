package tr.edu.metu.ii.sm504.security;

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
public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;

    @ManagedProperty("#{authenticationService}")
    private AuthenticationService authenticationService;

    public String login() {
        boolean success = authenticationService.login(username, password);
        if (success) {
            return "index.html"; // return to application but being logged now
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Username or password incorrect."));
            return "login.xhtml";
        }
    }

    public String logout(){
        authenticationService.logout();
        return "/login.xhtml?faces-redirect=true";
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
