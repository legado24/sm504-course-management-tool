package tr.edu.metu.ii.sm504.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import org.primefaces.model.SortOrder;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.jsf.search.SearchRoleCriteria;

@Entity
@Table(name = "ROLE")
public class Role extends AuditableEntity {

    @NotNull
    private String name;

    private String description;

    @NotNull
    @ManyToMany
    private List<Permission> permissions = new ArrayList<Permission>();

    public List<Permission> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
