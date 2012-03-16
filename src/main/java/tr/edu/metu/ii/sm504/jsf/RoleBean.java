package tr.edu.metu.ii.sm504.jsf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.metu.ii.sm504.domain.Role;
import tr.edu.metu.ii.sm504.service.RoleService;
import tr.edu.metu.ii.sm504.util.ApplicationUtil;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
@ManagedBean(name = "roleBean")
@SessionScoped
public class RoleBean implements Serializable {
    private static final long serialVersionUID = 1L;

    @Autowired
    private RoleService roleService;

    private Role role;

    public RoleBean() {
    }

    public void setupFormForCreate() {
        this.reset();
    }

    public void setupFormForEdit(Long id) {
        this.reset();
        role = roleService.findRole(id);
    }

    public void setupFormForView(Long id) {
        this.reset();
        role = roleService.findRole(id);
    }

    public String persist() {
        try {
            String message = "";
            if (role.getId() != null) {
                roleService.merge(role);
                message = "Successfully updated";
            } else {
                roleService.persist(role);
                message = "Successfully created";
            }

            ApplicationUtil.raiseMessageToUI(message);
            reset();
        } catch (Throwable t) {
            ApplicationUtil.raiseExceptionToUI(t);
        }
        return null;
    }

    public void remove(Long id) {
        this.role = roleService.findRole(id);
        this.delete();
    }

    private String name = "Roles";

    private List<Role> allRoles;

    private List<String> columns;

    @PostConstruct
    public void init() {
        columns = new ArrayList<String>();
        columns.add("name");
        columns.add("description");
    }

    public String getName() {
        return name;
    }

    public List<String> getColumns() {
        return columns;
    }

    public List<Role> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(List<Role> allRoles) {
        this.allRoles = allRoles;
    }

    public String findAllRoles() {
        allRoles = roleService.findAllRoles();
        return null;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String delete() {
        roleService.remove(role);
        FacesMessage facesMessage = new FacesMessage("Successfully deleted");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllRoles();
    }

    public void reset() {
        role = new Role();
    }

}
