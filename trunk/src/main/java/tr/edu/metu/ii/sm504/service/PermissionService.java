package tr.edu.metu.ii.sm504.service;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.domain.Permission;
import tr.edu.metu.ii.sm504.jsf.search.SearchCriteria;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 11.03.2012
 * Time: 01:26
 * To change this template use File | Settings | File Templates.
 */
@Service
public class PermissionService extends AuditableEntityService<Permission>{

    public List<Permission> findPermissions(SearchCriteria searchCriteria, int first, String orderBy, SortOrder sortOrder) {
        orderBy = (orderBy != null) ? orderBy : "name";
        String orderDirection = (SortOrder.ASCENDING.equals(sortOrder)) ? " ASC" : " DESC";
        return getEntityManager().createQuery("select r from Permission r order by r." + orderBy + orderDirection, Permission.class)
                .setFirstResult(first).setMaxResults(searchCriteria.getPageSize())
                .getResultList();
    }


    public int getNumberOfPermissions(SearchCriteria searchCriteria) {
        Long l = (Long) getEntityManager().createQuery("select count(r.id) from Permission r")
                .getSingleResult();
        return l.intValue();
    }

    public long countPermissions() {
        return getEntityManager().createQuery("SELECT COUNT(o) FROM Permission o", Long.class).getSingleResult();
    }

    public List<Permission> findAllPermissions() {
        return getEntityManager().createQuery("SELECT o FROM Permission o", Permission.class).getResultList();
    }

    public Permission findPermission(Long id) {
        if (id == null) return null;
        return getEntityManager().find(Permission.class, id);
    }

    public List<Permission> findPermissionEntries(int firstResult, int maxResults) {
        return getEntityManager().createQuery("SELECT o FROM Permission o", Permission.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Transactional
    public Permission merge(Permission updatedPermission) {
        Permission merged = this.getEntityManager().merge(updatedPermission);
        this.getEntityManager().flush();
        return merged;
    }
}
