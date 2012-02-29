// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package tr.edu.metu.ii.sm504.domain;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tr.edu.metu.ii.sm504.domain.Course;
import tr.edu.metu.ii.sm504.domain.CourseDataOnDemand;
import tr.edu.metu.ii.sm504.domain.User;
import tr.edu.metu.ii.sm504.domain.UserDataOnDemand;

privileged aspect CourseDataOnDemand_Roo_DataOnDemand {
    
    declare @type: CourseDataOnDemand: @Component;
    
    private Random CourseDataOnDemand.rnd = new SecureRandom();
    
    private List<Course> CourseDataOnDemand.data;
    
    @Autowired
    private UserDataOnDemand CourseDataOnDemand.userDataOnDemand;
    
    public Course CourseDataOnDemand.getNewTransientCourse(int index) {
        Course obj = new Course();
        setCode(obj, index);
        setCreatedBy(obj, index);
        setCreationTime(obj, index);
        setName(obj, index);
        setUpdateTime(obj, index);
        setUpdatedBy(obj, index);
        return obj;
    }
    
    public void CourseDataOnDemand.setCode(Course obj, int index) {
        String code = "code_" + index;
        obj.setCode(code);
    }
    
    public void CourseDataOnDemand.setCreatedBy(Course obj, int index) {
        User createdBy = userDataOnDemand.getRandomUser();
        obj.setCreatedBy(createdBy);
    }
    
    public void CourseDataOnDemand.setCreationTime(Course obj, int index) {
        Date creationTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setCreationTime(creationTime);
    }
    
    public void CourseDataOnDemand.setName(Course obj, int index) {
        String name = "name_" + index;
        obj.setName(name);
    }
    
    public void CourseDataOnDemand.setUpdateTime(Course obj, int index) {
        Date updateTime = new GregorianCalendar(Calendar.getInstance().get(Calendar.YEAR), Calendar.getInstance().get(Calendar.MONTH), Calendar.getInstance().get(Calendar.DAY_OF_MONTH), Calendar.getInstance().get(Calendar.HOUR_OF_DAY), Calendar.getInstance().get(Calendar.MINUTE), Calendar.getInstance().get(Calendar.SECOND) + new Double(Math.random() * 1000).intValue()).getTime();
        obj.setUpdateTime(updateTime);
    }
    
    public void CourseDataOnDemand.setUpdatedBy(Course obj, int index) {
        User updatedBy = userDataOnDemand.getRandomUser();
        obj.setUpdatedBy(updatedBy);
    }
    
    public Course CourseDataOnDemand.getSpecificCourse(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        Course obj = data.get(index);
        Long id = obj.getId();
        return Course.findCourse(id);
    }
    
    public Course CourseDataOnDemand.getRandomCourse() {
        init();
        Course obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return Course.findCourse(id);
    }
    
    public boolean CourseDataOnDemand.modifyCourse(Course obj) {
        return false;
    }
    
    public void CourseDataOnDemand.init() {
        int from = 0;
        int to = 10;
        data = Course.findCourseEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'Course' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<Course>();
        for (int i = 0; i < 10; i++) {
            Course obj = getNewTransientCourse(i);
            try {
                obj.persist();
            } catch (ConstraintViolationException e) {
                StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getConstraintDescriptor()).append(":").append(cv.getMessage()).append("=").append(cv.getInvalidValue()).append("]");
                }
                throw new RuntimeException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
    
}
