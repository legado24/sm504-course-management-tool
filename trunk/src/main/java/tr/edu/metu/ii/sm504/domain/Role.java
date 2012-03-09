package tr.edu.metu.ii.sm504.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

import org.primefaces.model.SortOrder;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.jsf.search.SearchRoleCriteria;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "ROLE")
public class Role extends AuditableEntity {

    @NotNull
    private String name;

    private String description;

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Permission> permissions = new ArrayList<Permission>();

    public static List<Role> findRoles(SearchRoleCriteria searchCriteria, int first, String orderBy, SortOrder sortOrder) {
        orderBy = (orderBy != null) ? orderBy : "name";
        String orderDirection = (SortOrder.ASCENDING.equals(sortOrder)) ? " ASC" : " DESC";
        return entityManager().createQuery("select r from Role r order by r." + orderBy + orderDirection, Role.class)
                .setFirstResult(first).setMaxResults(searchCriteria.getPageSize())
                .getResultList();
    }

    public static int getNumberOfRoles(SearchRoleCriteria searchCriteria) {
        Long l = (Long) entityManager().createQuery("select count(r.id) from Role r")
                .getSingleResult();
        return l.intValue();
    }

    public List<Permission> getPermissions() {
        return this.permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }
}
