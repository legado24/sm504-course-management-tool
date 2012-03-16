package tr.edu.metu.ii.sm504.jsf.dataModel;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import tr.edu.metu.ii.sm504.domain.Course;
import tr.edu.metu.ii.sm504.jsf.search.SearchCourseCriteria;
import tr.edu.metu.ii.sm504.service.CourseService;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 07.03.2012
 * Time: 07:01
 * To change this template use File | Settings | File Templates.
 */
public class CourseLazyDataModel extends LazyDataModel<Course>{

    private static final long serialVersionUID = -8832831134966938627L;

    SearchCourseCriteria searchCriteria;

    CourseService courseService;

    private Course selected;

    public CourseLazyDataModel(SearchCourseCriteria searchCriteria, CourseService courseService) {
        this.searchCriteria = searchCriteria;
        this.courseService = courseService;
    }

    @Override
    public int getRowCount() {
        return courseService.getNumberOfCourses(searchCriteria);
    }

    @Override
    public List<Course> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        searchCriteria.setCurrentPage(first / pageSize + 1);
        return courseService.findCourses(searchCriteria, first, sortField, sortOrder);
    }

    public Course getSelected() {
        return selected;
    }

    public void setSelected(Course selected) {
        this.selected = selected;
    }

}
