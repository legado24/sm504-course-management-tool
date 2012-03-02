package tr.edu.metu.ii.sm504.jsf;

import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import tr.edu.metu.ii.sm504.domain.SubmissionActivity;

@RooSerializable
@RooJsfManagedBean(entity = SubmissionActivity.class, beanName = "submissionActivityBean")
public class SubmissionActivityBean {
}