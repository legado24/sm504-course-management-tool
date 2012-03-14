package tr.edu.metu.ii.sm504.security;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import tr.edu.metu.ii.sm504.domain.User;
import tr.edu.metu.ii.sm504.util.ApplicationUtil;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
    @PersistenceContext
    private EntityManager entityManager;

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
            ApplicationUtil.raiseMessageToUI("Your account has been locked", FacesMessage.SEVERITY_WARN);
        }catch (AccountExpiredException e){
            ApplicationUtil.raiseMessageToUI("Your account has expired", FacesMessage.SEVERITY_WARN);
        }catch (CredentialsExpiredException e){
            ApplicationUtil.raiseMessageToUI("Your credentials have expired", FacesMessage.SEVERITY_WARN);
        }catch (AuthenticationException e){
            ApplicationUtil.raiseMessageToUI("Invalid username or password", FacesMessage.SEVERITY_WARN);
        }catch (Throwable t){
            ApplicationUtil.raiseExceptionToUI(t);
        }

        return "failure";
    }

    public void authenticateAsInstructor() {
        // Database'de id'si 1 olan Instructor rolune sahip bir kullanici oldugunu varsayiyorum
        User instructor = (User) entityManager.createQuery("select u from User u, IN (u.roles) r where r.id = 1").getSingleResult();
        authenticationService.login(instructor.getUsername(), instructor.getPassword());
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
