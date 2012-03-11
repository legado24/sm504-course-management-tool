package tr.edu.metu.ii.sm504.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;
import tr.edu.metu.ii.sm504.service.PermissionService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.security.SecureRandom;
import java.util.*;

@Configurable
@Component
public class PermissionDataOnDemand {

    private Random rnd = new SecureRandom();

    @Autowired
    private PermissionService permissionService;

    private List<Permission> data;

    @Autowired
    private UserDataOnDemand userDataOnDemand;

    public Permission getNewTransientPermission(int index) {
        Permission obj = new Permission();
        setCreatedBy(obj, index);
        setCreationTime(obj, index);
        setName(obj, index);
        setUpdateTime(obj, index);
        setUpdatedBy(obj, index);
        return obj;
    }

    public void setCreatedBy(Permission obj, int index) {
        User createdBy = userDataOnDemand.getRandomUser();
        obj.setCreatedBy(createdBy);
    }

    public void setCreationTime(Permission obj, int index) {
        Date creationTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreationTime(creationTime);
    }

    public void setName(Permission obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }

    public void setUpdateTime(Permission obj, int index) {
        Date updateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdateTime(updateTime);
    }

    public void setUpdatedBy(Permission obj, int index) {
        User updatedBy = userDataOnDemand.getRandomUser();
        obj.setUpdatedBy(updatedBy);
    }

    public Permission getSpecificPermission(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Permission obj = data.get(index);
        Long id = obj.getId();
        return permissionService.findPermission(id);
    }

    public Permission getRandomPermission() {
        init();
        Permission obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return permissionService.findPermission(id);
    }

    public boolean modifyPermission(Permission obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = permissionService.findPermissionEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Permission' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }

        data = new ArrayList<Permission>();
        for (int i = 0; i < 10; i++) {
            Permission obj = getNewTransientPermission(i);
            try {
                permissionService.persist(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            permissionService.flush(obj);
            data.add(obj);
        }
    }

}
