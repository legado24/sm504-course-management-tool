package tr.edu.metu.ii.sm504.service;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.domain.Permission;
import tr.edu.metu.ii.sm504.jsf.search.SearchCriteria;
import tr.edu.metu.ii.sm504.repository.PermissionRepository;

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
public class PermissionService implements Serializable {

    @Autowired
    private PermissionRepository permissionRepository;

    public List<Permission> findPermissions(SearchCriteria searchCriteria, int first, String orderBy, SortOrder sortOrder) {
        return permissionRepository.findPermissions(searchCriteria, first, orderBy, sortOrder);
    }

    public int getNumberOfPermissions(SearchCriteria searchCriteria) {
        return permissionRepository.getNumberOfPermissions(searchCriteria);
    }

    public long countPermissions() {
        return permissionRepository.countPermissions();
    }

    public List<Permission> findAllPermissions() {
        return permissionRepository.findAllPermissions();
    }

    public Permission findPermission(Long id) {
        return permissionRepository.findPermission(id);
    }

    public List<Permission> findPermissionEntries(int firstResult, int maxResults) {
        return permissionRepository.findPermissionEntries(firstResult, maxResults);
    }

    public void persist(Permission permission) {
        permissionRepository.persist(permission);
    }

    public Permission merge(Permission updatedPermission) {
        return permissionRepository.merge(updatedPermission);
    }

    public void remove(Permission permission) {
        permissionRepository.remove(permission);
    }

}
