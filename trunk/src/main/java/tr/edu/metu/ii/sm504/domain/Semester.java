package tr.edu.metu.ii.sm504.domain;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "SEMESTER")
public class Semester extends AuditableEntity {

    @Min(1000L)
    @Max(9999L)
    private Integer year;

    private String name;
}
