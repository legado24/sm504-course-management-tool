package tr.edu.metu.ii.sm504.repository;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.domain.Course;
import tr.edu.metu.ii.sm504.domain.Instructor;
import tr.edu.metu.ii.sm504.domain.Instructor;
import tr.edu.metu.ii.sm504.domain.User;
import tr.edu.metu.ii.sm504.jsf.search.SearchCriteria;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * Instructor: ekisa
 * Date: 11.03.2012
 * Time: 01:26
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class InstructorRepository extends AuditableEntityRepository<Instructor> {


    public List<Instructor> findInstructors(SearchCriteria searchCriteria, int first, String orderBy, SortOrder sortOrder) {
        orderBy = (orderBy != null) ? orderBy : "name";
        String orderDirection = (SortOrder.ASCENDING.equals(sortOrder)) ? " ASC" : " DESC";
        return getEntityManager().createQuery("select r from Instructor r order by r." + orderBy + orderDirection, Instructor.class)
                .setFirstResult(first).setMaxResults(searchCriteria.getPageSize())
                .getResultList();
    }


    public int getNumberOfInstructors(SearchCriteria searchCriteria) {
        Long l = (Long) getEntityManager().createQuery("select count(i.id) from Instructor i")
                .getSingleResult();
        return l.intValue();
    }

    public long countInstructors() {
        return getEntityManager().createQuery("SELECT COUNT(o) FROM Instructor o", Long.class).getSingleResult();
    }

    public List<Instructor> findAllInstructors() {
        return getEntityManager().createQuery("SELECT o FROM Instructor o", Instructor.class).getResultList();
    }

    public Instructor findInstructor(Long id) {
        if (id == null) return null;
        return getEntityManager().find(Instructor.class, id);
    }

    public List<Instructor> findInstructorEntries(int firstResult, int maxResults) {
        return getEntityManager().createQuery("SELECT o FROM Instructor o", Instructor.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Transactional
    public Instructor merge(Instructor updatedInstructor) {
        Instructor merged = this.getEntityManager().merge(updatedInstructor);
        this.getEntityManager().flush();
        return merged;
    }

    public Instructor findInstructorByUser(User user) {
        return (Instructor) getEntityManager().createQuery("select i from Instructor i where i.user.id = :userId").setParameter("userId", user.getId()).getSingleResult();
    }


}
