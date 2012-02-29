package tr.edu.metu.ii.sm504.jsf;

import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import tr.edu.metu.ii.sm504.domain.Student;

@RooSerializable
@RooJsfManagedBean(entity = Student.class, beanName = "studentBean")
public class StudentBean {
}
