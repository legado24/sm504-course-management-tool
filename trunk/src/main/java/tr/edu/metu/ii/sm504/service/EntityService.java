package tr.edu.metu.ii.sm504.service;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.domain.Entity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 11.03.2012
 * Time: 01:37
 * To change this template use File | Settings | File Templates.
 */
public abstract class EntityService<T extends Entity> implements Serializable {

    @PersistenceContext
    private EntityManager entityManager;

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @Transactional
    public void persist(T object){
        getEntityManager().persist(object);
    }

    @Transactional
    public void remove(T object){
        if (this.getEntityManager().contains(object)) {
            this.getEntityManager().remove(object);
        } else {
            T attached = (T) getEntityManager().find(object.getClass(), object.getId());
            this.getEntityManager().remove(attached);
        }
    }


    @Transactional
    public T merge(T updatedEntity) {
        T merged = this.getEntityManager().merge(updatedEntity);
        //this.getEntityManager().flush();
        this.flush(merged);
        return merged;
    }

    @Transactional
    public void flush(T object){
        getEntityManager().flush();
    }

    @Transactional
    public void clear() {
        this.getEntityManager().clear();
    }

}
