package tr.edu.metu.ii.sm504.jsf;

import org.springframework.roo.addon.jsf.managedbean.RooJsfManagedBean;
import org.springframework.roo.addon.serializable.RooSerializable;
import tr.edu.metu.ii.sm504.domain.ProjectTeam;

@RooSerializable
@RooJsfManagedBean(entity = ProjectTeam.class, beanName = "projectTeamBean")
public class ProjectTeamBean {
}
