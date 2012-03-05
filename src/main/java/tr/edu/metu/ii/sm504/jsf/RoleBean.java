package tr.edu.metu.ii.sm504.jsf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import tr.edu.metu.ii.sm504.domain.Permission;
import tr.edu.metu.ii.sm504.domain.Role;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@RooSerializable
@RooJsfManagedBean(entity = Role.class, beanName = "roleBean")
public class RoleBean {

    private PermissionBean permissionBean;
    private Role role;

    public RoleBean(PermissionBean permissionBean) {
        this.permissionBean = permissionBean;
        permissionBean.findAllPermissions();
    }

    public void prepareEdit() {
        this.reset();
        this.setRole(new Role());
    }

    public String persist() {
        String message = "";
        if (role.getId() != null) {
            role.merge();
            message = "Successfully updated";
        } else {
            role.persist();
            message = "Successfully created";
        }

        FacesMessage facesMessage = new FacesMessage(message);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
        reset();
        return findAllRoles();
    }
}
