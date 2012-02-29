package tr.edu.metu.ii.sm504.jsf;

import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import tr.edu.metu.ii.sm504.domain.User;

@RooSerializable
@RooJsfManagedBean(entity = User.class, beanName = "userBean")
public class UserBean {
}
