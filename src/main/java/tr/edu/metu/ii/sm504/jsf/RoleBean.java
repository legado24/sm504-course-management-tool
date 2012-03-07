package tr.edu.metu.ii.sm504.jsf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import org.springframework.stereotype.Service;
import tr.edu.metu.ii.sm504.domain.Permission;
import tr.edu.metu.ii.sm504.domain.Role;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@RooSerializable
@RooJsfManagedBean(entity = Role.class, beanName = "roleBean")
@Service
public class RoleBean {

    private Role role;

    public RoleBean() {
    }

    public void prepareEdit(Long id) {
        this.reset();
        if (id != null) {
            role = Role.findRole(id);
        }else{
            this.setRole(new Role());
        }
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
}
