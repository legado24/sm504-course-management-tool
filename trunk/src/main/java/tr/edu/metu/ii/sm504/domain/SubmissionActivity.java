package tr.edu.metu.ii.sm504.domain;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "SUBMISSION_ACTIVITY")
public class SubmissionActivity extends AuditableEntity {

    @NotNull
    @ManyToOne
    private Activity activity;
}
