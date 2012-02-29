package tr.edu.metu.ii.sm504.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "ROLE")
public class Role extends AuditableEntity {

    @NotNull
    private String name;

    private String description;

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Permission> permissions = new HashSet<Permission>();
}
