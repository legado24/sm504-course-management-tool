package tr.edu.metu.ii.sm504.repository;

import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.domain.Course;
import tr.edu.metu.ii.sm504.domain.Instructor;
import tr.edu.metu.ii.sm504.jsf.search.SearchCriteria;
import tr.edu.metu.ii.sm504.util.ApplicationUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 11.03.2012
 * Time: 01:26
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class CourseRepository extends AuditableEntityRepository<Course> {

    public List<Course> findCourses(SearchCriteria searchCriteria, int first, String orderBy, SortOrder sortOrder) {
        orderBy = (orderBy != null) ? orderBy : "name";
        String orderDirection = (SortOrder.ASCENDING.equals(sortOrder)) ? " ASC" : " DESC";
        return getEntityManager().createQuery("select o from Course o order by o." + orderBy + orderDirection, Course.class)
                .setFirstResult(first).setMaxResults(searchCriteria.getPageSize())
                .getResultList();
    }


    public int getNumberOfCourses(SearchCriteria searchCriteria) {
        Long l = (Long) getEntityManager().createQuery("select count(r.id) from Course r")
                .getSingleResult();
        return l.intValue();
    }

    public long countCourses() {
        return getEntityManager().createQuery("SELECT COUNT(o) FROM Course o", Long.class).getSingleResult();
    }

    public List<Course> findAllCourses() {
        return getEntityManager().createQuery("SELECT o FROM Course o", Course.class).getResultList();
    }

    public Course findCourse(Long id) {
        if (id == null) return null;
        return getEntityManager().find(Course.class, id);
    }

    public List<Course> findCourseEntries(int firstResult, int maxResults) {
        return getEntityManager().createQuery("SELECT o FROM Course o", Course.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Transactional
    public Course merge(Course updatedCourse) {
        Course merged = this.getEntityManager().merge(updatedCourse);
        this.getEntityManager().flush();
        return merged;
    }

    public List<Course> findAllActiveCoursesOfInstructor(Instructor instructor) {
        return getEntityManager().createQuery("select c from Course c, in(c.semester) s where c.instructor = :instructor and s.dueDate > :now")
                .setParameter("instructor", instructor)
                .setParameter("now", ApplicationUtil.getCurrentDate())
        .getResultList();
    }
}
