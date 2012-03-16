package tr.edu.metu.ii.sm504.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import tr.edu.metu.ii.sm504.repository.RoleRepository;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.security.SecureRandom;
import java.util.*;

@Configurable
@Component
public class RoleDataOnDemand {
    private Random rnd = new SecureRandom();

    private List<Role> data;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserDataOnDemand userDataOnDemand;

    public Role getNewTransientRole(int index) {
        Role obj = new Role();
        setCreatedBy(obj, index);
        setCreationTime(obj, index);
        setDescription(obj, index);
        setName(obj, index);
        setUpdateTime(obj, index);
        setUpdatedBy(obj, index);
        return obj;
    }

    public void setCreatedBy(Role obj, int index) {
        User createdBy = userDataOnDemand.getRandomUser();
        obj.setCreatedBy(createdBy);
    }

    public void setCreationTime(Role obj, int index) {
        Date creationTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreationTime(creationTime);
    }

    public void setDescription(Role obj, int index) {
        String description = "description_" + index;
        obj.setDescription(description);
    }

    public void setName(Role obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }

    public void setUpdateTime(Role obj, int index) {
        Date updateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdateTime(updateTime);
    }

    public void setUpdatedBy(Role obj, int index) {
        User updatedBy = userDataOnDemand.getRandomUser();
        obj.setUpdatedBy(updatedBy);
    }

    public Role getSpecificRole(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Role obj = data.get(index);
        Long id = obj.getId();
        return roleRepository.findRole(id);
    }

    public Role getRandomRole() {
        init();
        Role obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return roleRepository.findRole(id);
    }

    public boolean modifyRole(Role obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = roleRepository.findRoleEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Role' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }

        data = new ArrayList<Role>();
        for (int i = 0; i < 10; i++) {
            Role obj = getNewTransientRole(i);
            try {
                roleRepository.persist(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            roleRepository.flush(obj);
            data.add(obj);
        }
    }
}
