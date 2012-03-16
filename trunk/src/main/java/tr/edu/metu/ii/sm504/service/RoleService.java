package tr.edu.metu.ii.sm504.service;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.domain.AuditableEntity;
import tr.edu.metu.ii.sm504.domain.Role;
import tr.edu.metu.ii.sm504.jsf.search.SearchCriteria;
import tr.edu.metu.ii.sm504.repository.RoleRepository;

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
public class RoleService implements Serializable {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> findRoles(SearchCriteria searchCriteria, int first, String orderBy, SortOrder sortOrder) {
        return roleRepository.findRoles(searchCriteria, first, orderBy, sortOrder);
    }

    public int getNumberOfRoles(SearchCriteria searchCriteria) {
        return roleRepository.getNumberOfRoles(searchCriteria);
    }

    public long countRoles() {
        return roleRepository.countRoles();
    }

    public List<Role> findAllRoles() {
        return roleRepository.findAllRoles();
    }

    public Role findRole(Long id) {
        return roleRepository.findRole(id);
    }

    public List<Role> findRoleEntries(int firstResult, int maxResults) {
        return roleRepository.findRoleEntries(firstResult, maxResults);
    }

    public void persist(Role role) {
        roleRepository.persist(role);
    }

    public Role merge(Role role) {
        return roleRepository.merge(role);
    }

    public void remove(Role role) {
        roleRepository.remove(role);
    }
}
