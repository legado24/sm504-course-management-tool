package tr.edu.metu.ii.sm504.domain;

import javax.persistence.ManyToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "STUDENT")
public class Student extends AuditableEntity {

    private String studentNumber;

    @ManyToOne
    private User user;

    @ManyToOne
    private Course course;

    @ManyToOne
    private ProjectTeam projectTeam;
}
