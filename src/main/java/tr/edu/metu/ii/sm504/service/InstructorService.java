package tr.edu.metu.ii.sm504.service;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.metu.ii.sm504.domain.Course;
import tr.edu.metu.ii.sm504.domain.Instructor;
import tr.edu.metu.ii.sm504.domain.User;
import tr.edu.metu.ii.sm504.jsf.search.SearchCriteria;
import tr.edu.metu.ii.sm504.repository.InstructorRepository;
import tr.edu.metu.ii.sm504.repository.UserRepository;
import tr.edu.metu.ii.sm504.util.SpringPropertiesUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Instructor: ekisa
 * Date: 11.03.2012
 * Time: 01:26
 * To change this template use File | Settings | File Templates.
 */
@Service
public class InstructorService implements Serializable {

    @Autowired
    private InstructorRepository instructorRepository;

    public List<Instructor> findInstructors(SearchCriteria searchCriteria, int first, String orderBy, SortOrder sortOrder) {
        return instructorRepository.findInstructors(searchCriteria, first, orderBy, sortOrder);
    }

    public int getNumberOfInstructors(SearchCriteria searchCriteria) {
        return instructorRepository.getNumberOfInstructors(searchCriteria);
    }

    public long countInstructors() {
        return instructorRepository.countInstructors();
    }

    public List<Instructor> findAllInstructors() {
        return instructorRepository.findAllInstructors();
    }

    public Instructor findInstructor(Long id) {
        return instructorRepository.findInstructor(id);
    }

    public List<Instructor> findInstructorEntries(int firstResult, int maxResults) {
        return instructorRepository.findInstructorEntries(firstResult, maxResults);
    }

    public void persist(Instructor instructor) {
        instructorRepository.persist(instructor);
    }

    public Instructor merge(Instructor updatedInstructor) {
        return instructorRepository.merge(updatedInstructor);
    }

    public void remove(Instructor instructor) {
        instructorRepository.remove(instructor);
    }
}
