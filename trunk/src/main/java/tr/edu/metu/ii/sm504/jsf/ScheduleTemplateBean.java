package tr.edu.metu.ii.sm504.jsf;

import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import tr.edu.metu.ii.sm504.domain.ScheduleTemplate;

@RooSerializable
@RooJsfManagedBean(entity = ScheduleTemplate.class, beanName = "scheduleTemplateBean")
public class ScheduleTemplateBean {
}
