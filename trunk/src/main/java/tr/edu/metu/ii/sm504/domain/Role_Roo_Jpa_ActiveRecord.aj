// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tr.edu.metu.ii.sm504.domain;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.domain.Role;

privileged aspect Role_Roo_Jpa_ActiveRecord {
    
    public static long Role.countRoles() {
        return entityManager().createQuery("SELECT COUNT(o) FROM Role o", Long.class).getSingleResult();
    }
    
    public static List<Role> Role.findAllRoles() {
        return entityManager().createQuery("SELECT o FROM Role o", Role.class).getResultList();
    }
    
    public static Role Role.findRole(Long id) {
        if (id == null) return null;
        return entityManager().find(Role.class, id);
    }
    
    public static List<Role> Role.findRoleEntries(int firstResult, int maxResults) {
        return entityManager().createQuery("SELECT o FROM Role o", Role.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }
    
    @Transactional
    public Role Role.merge() {
        if (this.entityManager == null) this.entityManager = entityManager();
        Role merged = this.entityManager.merge(this);
        this.entityManager.flush();
        return merged;
    }
}
