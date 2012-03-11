package tr.edu.metu.ii.sm504.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.roo.addon.dod.RooDataOnDemand;
import org.springframework.stereotype.Component;
import tr.edu.metu.ii.sm504.service.UserService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.security.SecureRandom;
import java.util.*;

@Component
@Configurable
public class UserDataOnDemand {
    private Random rnd = new SecureRandom();

    @Autowired
    private UserService userService;

    private List<User> data;

    public User getNewTransientUser(int index) {
        User obj = new User();
        setAddress(obj, index);
        setCreatedBy(obj, index);
        setCreationTime(obj, index);
        setDueDate(obj, index);
        setEmail(obj, index);
        setName(obj, index);
        setPassword(obj, index);
        setPhoto(obj, index);
        setStatus(obj, index);
        setSurname(obj, index);
        setUpdateTime(obj, index);
        setUpdatedBy(obj, index);
        setUsername(obj, index);
        return obj;
    }

    public void setAddress(User obj, int index) {
        String address = "address_" + index;
        obj.setAddress(address);
    }

    public void setCreatedBy(User obj, int index) {
        User createdBy = obj;
        obj.setCreatedBy(createdBy);
    }

    public void setCreationTime(User obj, int index) {
        Date creationTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreationTime(creationTime);
    }

    public void setDueDate(User obj, int index) {
        Date dueDate = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setDueDate(dueDate);
    }

    public void setEmail(User obj, int index) {
        String email = "foo" + index + "@bar.com";
        obj.setEmail(email);
    }

    public void setName(User obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }

    public void setPassword(User obj, int index) {
        String password = "password_" + index;
        obj.setPassword(password);
    }

    public void setPhoto(User obj, int index) {
        byte[] photo = String.valueOf(index).getBytes();
        obj.setPhoto(photo);
    }

    public void setStatus(User obj, int index) {
        Boolean status = Boolean.TRUE;
        obj.setStatus(status);
    }

    public void setSurname(User obj, int index) {
        String surname = "surname_" + index;
        obj.setSurname(surname);
    }

    public void setUpdateTime(User obj, int index) {
        Date updateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdateTime(updateTime);
    }

    public void setUpdatedBy(User obj, int index) {
        User updatedBy = obj;
        obj.setUpdatedBy(updatedBy);
    }

    public void setUsername(User obj, int index) {
        String username = "username_" + index;
        obj.setUsername(username);
    }

    public User getSpecificUser(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        User obj = data.get(index);
        Long id = obj.getId();
        return userService.findUser(id);
    }

    public User getRandomUser() {
        init();
        User obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return userService.findUser(id);
    }

    public boolean modifyUser(User obj) {
        return false;
    }

    public void init() {
        int from = 0;
        int to = 10;
        data = userService.findUserEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'User' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }

        data = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User obj = getNewTransientUser(i);
            try {
                userService.persist(obj);
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            userService.flush(obj);
            data.add(obj);
        }
    }
}
