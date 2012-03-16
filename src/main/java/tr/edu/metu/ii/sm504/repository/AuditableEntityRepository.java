package tr.edu.metu.ii.sm504.repository;

import tr.edu.metu.ii.sm504.domain.AuditableEntity;
import tr.edu.metu.ii.sm504.domain.User;
import tr.edu.metu.ii.sm504.util.ApplicationUtil;

import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 11.03.2012
 * Time: 01:44
 * To change this template use File | Settings | File Templates.
 */
public abstract class AuditableEntityRepository<T extends AuditableEntity> extends EntityRepository<AuditableEntity> {

    public void persist(AuditableEntity auditableEntity) {
        try {
            User user = getEntityManager().find(User.class, 1L);

            Date now = ApplicationUtil.getCurrentDate();
            auditableEntity.setCreationTime(now);
            auditableEntity.setUpdateTime(now);
            auditableEntity.setCreatedBy(user);
            auditableEntity.setUpdatedBy(user);
            super.persist(auditableEntity);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void flush(AuditableEntity auditableEntity) {
        User user = getEntityManager().find(User.class, 1L);
        auditableEntity.setUpdatedBy(user);
        auditableEntity.setUpdateTime(ApplicationUtil.getCurrentDate());
        super.flush(auditableEntity);
    }

}
