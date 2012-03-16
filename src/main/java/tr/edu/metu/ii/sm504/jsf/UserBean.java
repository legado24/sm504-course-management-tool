package tr.edu.metu.ii.sm504.jsf;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import tr.edu.metu.ii.sm504.domain.User;
import tr.edu.metu.ii.sm504.service.UserService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@Configurable
@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean implements Serializable{

    @Autowired
    private UserService userService;

    private String name = "Users";

    private User user;

    private List<User> allUsers;

    private boolean dataVisible = false;

    private List<String> columns;

    @PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("name");
    }

    public String getName() {
        return name;
    }

    public List<String> getColumns() {
        return columns;
    }

    public List<User> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(List<User> allUsers) {
        this.allUsers = allUsers;
    }

    public String findAllUsers() {
        allUsers = userService.findAllUsers();
        dataVisible = !allUsers.isEmpty();
        return null;
    }

    public boolean isDataVisible() {
        return dataVisible;
    }

    public void setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }

    public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    /*public List<User> completeUpdatedBy(String query) {
        List<User> suggestions = new ArrayList<User>();
        for (User user : userService.findAllUsers()) {
            String userStr = String.valueOf(user.getUpdateTime() +  " "  + user.getCreationTime() +  " "  + user.getUsername() +  " "  + user.getName());
            if (userStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(user);
            }
        }
        return suggestions;
    }

    public List<User> completeCreatedBy(String query) {
        List<User> suggestions = new ArrayList<User>();
        for (User user : userService.findAllUsers()) {
            String userStr = String.valueOf(user.getUpdateTime() +  " "  + user.getCreationTime() +  " "  + user.getUsername() +  " "  + user.getName());
            if (userStr.toLowerCase().startsWith(query.toLowerCase())) {
                suggestions.add(user);
            }
        }
        return suggestions;
    }*/

    public String onEdit() {
        return null;
    }


    public String displayList() {
        findAllUsers();
        return "user";
    }

    public String displayCreateDialog() {
        user = new User();
        return "user";
    }

    public String persist() {
        String message = "";
        if (user.getId() != null) {
            userService.merge(user);
            message = "Successfully updated";
        } else {
            userService.persist(user);
            message = "Successfully created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialog.hide()");
        context.execute("editDialog.hide()");

        FacesMessage facesMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllUsers();
    }

    public String delete() {
        userService.remove(user);
        FacesMessage facesMessage = new FacesMessage("Successfully deleted");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllUsers();
    }

    public void reset() {
        user = null;
    }

    public void handleDialogClose(CloseEvent event) {
        reset();
    }

}
