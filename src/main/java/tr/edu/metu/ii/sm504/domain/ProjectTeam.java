package tr.edu.metu.ii.sm504.domain;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "PROJECT_TEAM")
public class ProjectTeam extends AuditableEntity {

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectTeam")
    private Set<Student> students = new HashSet<Student>();

    @ManyToOne
    private Course course;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectTeam")
    private Set<Submission> submissions = new HashSet<Submission>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "projectTeam")
    private Set<Review> reviews = new HashSet<Review>();
}
