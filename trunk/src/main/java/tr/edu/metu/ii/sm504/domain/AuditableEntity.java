package tr.edu.metu.ii.sm504.domain;

import java.util.Date;
import javax.persistence.ManyToOne;
import javax.persistence.Query;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.transaction.annotation.Transactional;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(inheritanceType = "TABLE_PER_CLASS", mappedSuperclass = true)
public abstract class AuditableEntity extends Entity {

    //@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date updateTime;

    //@NotNull
    @ManyToOne
    private User updatedBy;

    //@NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date creationTime;

    //@NotNull
    @ManyToOne
    private User createdBy;


    @Transactional
    public void persist() {
        try {
            if (this.entityManager == null) this.entityManager = entityManager();
            Date now = new Date();
            this.setCreationTime(now);
            this.setUpdateTime(now);
            this.setCreatedBy(User.findUser(1L));
            this.setUpdatedBy(User.findUser(1L));
            this.entityManager.persist(this);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    @Transactional
    public void remove() {
        if (this.entityManager == null) this.entityManager = entityManager();
        if (this.entityManager.contains(this)) {
            this.entityManager.remove(this);
        } else {
            Entity attached = entityManager.find(this.getClass(), this.getId());
            this.entityManager.remove(attached);
        }
    }

    @Transactional
    public void flush() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.setUpdatedBy(User.findUser(1L));
        this.setUpdateTime(new Date());
        this.entityManager.flush();
    }

    @Transactional
    public void clear() {
        if (this.entityManager == null) this.entityManager = entityManager();
        this.entityManager.clear();
    }

    
}
