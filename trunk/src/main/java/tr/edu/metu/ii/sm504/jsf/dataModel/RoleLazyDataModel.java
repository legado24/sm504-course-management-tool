package tr.edu.metu.ii.sm504.jsf.dataModel;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import tr.edu.metu.ii.sm504.domain.Role;
import tr.edu.metu.ii.sm504.jsf.search.SearchRoleCriteria;
import tr.edu.metu.ii.sm504.service.RoleService;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 07.03.2012
 * Time: 07:01
 * To change this template use File | Settings | File Templates.
 */
public class RoleLazyDataModel extends LazyDataModel<Role>{

    private static final long serialVersionUID = -8832831134966938627L;

    SearchRoleCriteria searchCriteria;

    RoleService roleService;

    private Role selected;

    public RoleLazyDataModel(SearchRoleCriteria searchCriteria, RoleService roleService) {
        this.searchCriteria = searchCriteria;
        this.roleService = roleService;
    }

    @Override
    public int getRowCount() {
        return roleService.getNumberOfRoles(searchCriteria);
    }

    @Override
    public List<Role> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        searchCriteria.setCurrentPage(first / pageSize + 1);
        return roleService.findRoles(searchCriteria, first, sortField, sortOrder);
    }

    public Role getSelected() {
        return selected;
    }

    public void setSelected(Role selected) {
        this.selected = selected;
    }

}
