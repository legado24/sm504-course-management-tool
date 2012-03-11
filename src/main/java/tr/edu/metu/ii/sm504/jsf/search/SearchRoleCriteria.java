package tr.edu.metu.ii.sm504.jsf.search;

import tr.edu.metu.ii.sm504.domain.Role;
import tr.edu.metu.ii.sm504.jsf.dataModel.RoleLazyDataModel;
import tr.edu.metu.ii.sm504.service.RoleService;

import javax.faces.model.DataModel;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 07.03.2012
 * Time: 06:59
 * To change this template use File | Settings | File Templates.
 */
public class SearchRoleCriteria implements SearchCriteria, Serializable{
    private static final long serialVersionUID = 1L;

    /**
     * The user-provided search criteria for finding Roles.
     */
    private String searchString = "";

    /**
     * The maximum page size of the Role result list
     */
    private int pageSize = 5;

    /**
     * The page the user is currently on.
     */
    private int currentPage = 1;

    /**
     * Returns a {@link javax.faces.model.DataModel} based on the search criteria.
     * @param role rich domain model to use to retrieve roles.
     */
    public DataModel<Role> getDataModel(RoleService roleService) {
        return new RoleLazyDataModel(this, roleService);
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public String toString() {
        return "[Search Criteria searchString = '" + searchString + "'";
    }

}
