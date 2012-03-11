package tr.edu.metu.ii.sm504.service;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.domain.Role;
import tr.edu.metu.ii.sm504.jsf.search.SearchCriteria;
import tr.edu.metu.ii.sm504.jsf.search.SearchRoleCriteria;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 11.03.2012
 * Time: 01:26
 * To change this template use File | Settings | File Templates.
 */
@Service
public class RoleService extends AuditableEntityService<Role>{

    public List<Role> findRoles(SearchCriteria searchCriteria, int first, String orderBy, SortOrder sortOrder) {
        orderBy = (orderBy != null) ? orderBy : "name";
        String orderDirection = (SortOrder.ASCENDING.equals(sortOrder)) ? " ASC" : " DESC";
        return getEntityManager().createQuery("select r from Role r order by r." + orderBy + orderDirection, Role.class)
                .setFirstResult(first).setMaxResults(searchCriteria.getPageSize())
                .getResultList();
    }


    public int getNumberOfRoles(SearchCriteria searchCriteria) {
        Long l = (Long) getEntityManager().createQuery("select count(r.id) from Role r")
                .getSingleResult();
        return l.intValue();
    }

    public long countRoles() {
        return getEntityManager().createQuery("SELECT COUNT(o) FROM Role o", Long.class).getSingleResult();
    }

    public List<Role> findAllRoles() {
        return getEntityManager().createQuery("SELECT o FROM Role o", Role.class).getResultList();
    }

    public Role findRole(Long id) {
        if (id == null) return null;
        return getEntityManager().find(Role.class, id);
    }

    public List<Role> findRoleEntries(int firstResult, int maxResults) {
        return getEntityManager().createQuery("SELECT o FROM Role o", Role.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

}
