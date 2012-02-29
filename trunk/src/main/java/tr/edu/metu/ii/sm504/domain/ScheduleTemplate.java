package tr.edu.metu.ii.sm504.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "SCHEDULE_TEMPLATE")
public class ScheduleTemplate extends AuditableEntity {

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    private Set<Activity> activities = new HashSet<Activity>();
}
