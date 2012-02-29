package tr.edu.metu.ii.sm504.jsf;

import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import tr.edu.metu.ii.sm504.domain.Review;

@RooSerializable
@RooJsfManagedBean(entity = Review.class, beanName = "reviewBean")
public class ReviewBean {
}
