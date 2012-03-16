package tr.edu.metu.ii.sm504.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
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

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "permissions")
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
