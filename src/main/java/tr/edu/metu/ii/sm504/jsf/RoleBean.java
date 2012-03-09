package tr.edu.metu.ii.sm504.jsf;

import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.stereotype.Service;
import tr.edu.metu.ii.sm504.domain.Permission;
import tr.edu.metu.ii.sm504.domain.Role;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

@RooSerializable
@RooJsfManagedBean(entity = Role.class, beanName = "roleBean")
@Service
public class RoleBean {

    private Role role;

    public RoleBean() {
    }

    public void setupFormForCreate() {
        this.reset();
        this.setRole(new Role());
    }

    public void setupFormForEdit(Long id) {
        this.reset();
        role = Role.findRole(id);
    }

    public void setupFormForView(Long id) {
        this.reset();
        role = Role.findRole(id);
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
        //return findAllRoles();
        return null;
    }

    public void remove(Long id) {
        this.role = Role.findRole(id);
        this.delete();
    }
}
