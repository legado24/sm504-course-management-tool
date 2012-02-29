package tr.edu.metu.ii.sm504.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "SCHEDULE")
public class Schedule extends AuditableEntity {

    @NotNull
    @ManyToOne
    private ScheduleTemplate scheduleTemplate;

    @NotNull
    @ManyToOne
    private Semester semester;
}
