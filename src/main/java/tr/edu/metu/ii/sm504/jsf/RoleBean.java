package tr.edu.metu.ii.sm504.jsf;

import org.primefaces.component.autocomplete.AutoComplete;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.message.Message;
import org.primefaces.component.selectmanymenu.SelectManyMenu;
import org.primefaces.event.CloseEvent;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.stereotype.Service;
import tr.edu.metu.ii.sm504.domain.Permission;
import tr.edu.metu.ii.sm504.domain.Role;
import tr.edu.metu.ii.sm504.domain.User;
import tr.edu.metu.ii.sm504.jsf.converter.PermissionConverter;
import tr.edu.metu.ii.sm504.jsf.converter.UserConverter;
import tr.edu.metu.ii.sm504.service.RoleService;
import tr.edu.metu.ii.sm504.util.ApplicationUtil;

import javax.annotation.PostConstruct;
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.context.FacesContext;
import javax.faces.convert.DateTimeConverter;
import javax.faces.event.ActionEvent;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
        this.setRole(new Role());
    }

    public void setupFormForEdit(Long id) {
        this.reset();
        role = roleService.findRole(id);
        this.setSelectedPermissions(role.getPermissions());
    }

    public void setupFormForView(Long id) {
        this.reset();
        role = roleService.findRole(id);
    }

    public String persist() {
        try{
            String message = "";
            if (role.getId() != null) {
                roleService.merge(role);
                message = "Successfully updated";
            } else {
                roleService.persist(role);
                message = "Successfully created";
            }

            FacesMessage facesMessage = new FacesMessage(message);
            FacesContext.getCurrentInstance().addMessage(null, facesMessage);
            reset();
            //return findAllRoles();
        }catch (Throwable t){
            ApplicationUtil.handleExceptionForUI(t);
        }
        return null;
    }

    public void remove(Long id) {
        this.role = roleService.findRole(id);
        this.delete();
    }

    private String name = "Roles";

    private List<Role> allRoles;

    private boolean dataVisible = false;

    private List<String> columns;

    private List<Permission> selectedPermissions;

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
        dataVisible = !allRoles.isEmpty();
        return null;
    }

    public boolean isDataVisible() {
        return dataVisible;
    }

    public void setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }

    public Role getRole() {
        if (role == null) {
            role = new Role();
        }
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<Permission> getSelectedPermissions() {
        return selectedPermissions;
    }

    public void setSelectedPermissions(List<Permission> selectedPermissions) {
        if (selectedPermissions != null) {
            role.setPermissions(selectedPermissions);
        }
        this.selectedPermissions = selectedPermissions;
    }

    public String onEdit() {
        if (role != null && role.getPermissions() != null) {
            selectedPermissions = new ArrayList<Permission>(role.getPermissions());
        }
        return null;
    }

    public String displayList() {
        findAllRoles();
        return "role";
    }

    public String delete() {
        roleService.remove(role);
        FacesMessage facesMessage = new FacesMessage("Successfully deleted");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllRoles();
    }

    public void reset() {
        role = null;
        selectedPermissions = null;
    }

}
