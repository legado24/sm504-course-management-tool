package tr.edu.metu.ii.sm504.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

import java.util.ArrayList;
import java.util.List;

//@RooJavaBean
//@RooToString
//@RooJpaActiveRecord(table = "PERMISSON")
@Entity
@Table(name = "PERMISSION")
public class Permission extends AuditableEntity {

    @NotNull
    private String name;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles = new ArrayList<Role>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
