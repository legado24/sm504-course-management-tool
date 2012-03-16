package tr.edu.metu.ii.sm504.repository;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortOrder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.domain.User;
import tr.edu.metu.ii.sm504.jsf.search.SearchCriteria;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 11.03.2012
 * Time: 01:26
 * To change this template use File | Settings | File Templates.
 */
@Repository
public class UserRepository extends AuditableEntityRepository<User> {

    public User findUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }

        return (User) getEntityManager().createQuery("select u from User u where u.username = ?").setParameter(1, username).getSingleResult();
    }

    public List<User> findUsers(SearchCriteria searchCriteria, int first, String orderBy, SortOrder sortOrder) {
        orderBy = (orderBy != null) ? orderBy : "name";
        String orderDirection = (SortOrder.ASCENDING.equals(sortOrder)) ? " ASC" : " DESC";
        return getEntityManager().createQuery("select r from User r order by r." + orderBy + orderDirection, User.class)
                .setFirstResult(first).setMaxResults(searchCriteria.getPageSize())
                .getResultList();
    }


    public int getNumberOfUsers(SearchCriteria searchCriteria) {
        Long l = (Long) getEntityManager().createQuery("select count(r.id) from User r")
                .getSingleResult();
        return l.intValue();
    }

    public long countUsers() {
        return getEntityManager().createQuery("SELECT COUNT(o) FROM User o", Long.class).getSingleResult();
    }

    public List<User> findAllUsers() {
        return getEntityManager().createQuery("SELECT o FROM User o", User.class).getResultList();
    }

    public User findUser(Long id) {
        if (id == null) return null;
        return getEntityManager().find(User.class, id);
    }

    public List<User> findUserEntries(int firstResult, int maxResults) {
        return getEntityManager().createQuery("SELECT o FROM User o", User.class).setFirstResult(firstResult).setMaxResults(maxResults).getResultList();
    }

    @Transactional
    public User merge(User updatedUser) {
        User merged = this.getEntityManager().merge(updatedUser);
        this.getEntityManager().flush();
        return merged;
    }
}
