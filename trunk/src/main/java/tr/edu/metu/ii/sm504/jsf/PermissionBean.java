package tr.edu.metu.ii.sm504.jsf;

import org.primefaces.context.RequestContext;
import org.primefaces.event.CloseEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import tr.edu.metu.ii.sm504.domain.Permission;
import tr.edu.metu.ii.sm504.service.PermissionService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@RooSerializable
//@RooJsfManagedBean(entity = permissionService.class, beanName = "permissionBean")
@Service
@Configurable
@ManagedBean(name = "permissionBean")
@SessionScoped
public class PermissionBean implements Serializable{

    @Autowired
    private PermissionService permissionService;
    
    private String name = "Permissions";

    private Permission permission;

    private List<Permission> allPermissions;

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

    public List<Permission> getAllPermissions() {
        return allPermissions;
    }

    public void setAllPermissions(List<Permission> allPermissions) {
        this.allPermissions = allPermissions;
    }

    public String findAllPermissions() {
        allPermissions = permissionService.findAllPermissions();
        dataVisible = !allPermissions.isEmpty();
        return null;
    }

    public boolean isDataVisible() {
        return dataVisible;
    }

    public void setDataVisible(boolean dataVisible) {
        this.dataVisible = dataVisible;
    }

    public Permission getPermission() {
        if (permission == null) {
            permission = new Permission();
        }
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public String onEdit() {
        return null;
    }


    public String displayList() {
        findAllPermissions();
        return "permission";
    }

    public String displayCreateDialog() {
        permission = new Permission();
        return "permission";
    }

    public String persist() {
        String message = "";
        if (permission.getId() != null) {
            permissionService.merge(permission);
            message = "Successfully updated";
        } else {
            permissionService.persist(permission);
            message = "Successfully created";
        }
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("createDialog.hide()");
        context.execute("editDialog.hide()");

        FacesMessage facesMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllPermissions();
    }

    public String delete() {
        permissionService.remove(permission);
        FacesMessage facesMessage = new FacesMessage("Successfully deleted");
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllPermissions();
    }

    public void reset() {
        permission = null;
    }

    public void handleDialogClose(CloseEvent event) {
        reset();
    }

}
