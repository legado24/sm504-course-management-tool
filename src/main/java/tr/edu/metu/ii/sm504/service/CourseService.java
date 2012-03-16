package tr.edu.metu.ii.sm504.service;

import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.metu.ii.sm504.domain.Course;
import tr.edu.metu.ii.sm504.domain.IUser;
import tr.edu.metu.ii.sm504.domain.Instructor;
import tr.edu.metu.ii.sm504.domain.User;
import tr.edu.metu.ii.sm504.jsf.search.SearchCriteria;
import tr.edu.metu.ii.sm504.repository.CourseRepository;

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
public class CourseService implements Serializable{

    @Autowired
    private CourseRepository courseRepository;

    public List<Course> findAllActiveCoursesOfInstructor(Instructor instructor) {
        return courseRepository.findAllActiveCoursesOfInstructor(instructor);
    }

    public List<Course> findCourses(SearchCriteria searchCriteria, int first, String orderBy, SortOrder sortOrder) {
        return courseRepository.findCourses(searchCriteria, first, orderBy, sortOrder);
    }

    public int getNumberOfCourses(SearchCriteria searchCriteria) {
        return courseRepository.getNumberOfCourses(searchCriteria);
    }

    public long countCourses() {
        return courseRepository.countCourses();
    }

    public List<Course> findAllCourses() {
        return courseRepository.findAllCourses();
    }

    public Course findCourse(Long id) {
        return courseRepository.findCourse(id);
    }

    public List<Course> findCourseEntries(int firstResult, int maxResults) {
        return courseRepository.findCourseEntries(firstResult, maxResults);
    }

    public void persist(Course course) {
        courseRepository.persist(course);
    }

    public Course merge(Course updatedCourse) {
        return courseRepository.merge(updatedCourse);
    }

    public void remove(Course course) {
        courseRepository.remove(course);
    }

}
