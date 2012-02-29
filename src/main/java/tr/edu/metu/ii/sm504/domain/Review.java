package tr.edu.metu.ii.sm504.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "REVIEW")
public class Review extends AuditableEntity {

    @NotNull
    @ManyToOne
    private ProjectTeam projectTeam;

    @NotNull
    @ManyToOne
    private SubmissionActivity submissionActivity;

    @NotNull
    @ManyToOne
    private Submission submission;
}
