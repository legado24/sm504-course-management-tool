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
@RooJpaActiveRecord(table = "COURSE")
public class Course extends AuditableEntity {

    private String name;

    private String code;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<LectureNote> lectureNotes = new HashSet<LectureNote>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "course")
    private Set<Student> students = new HashSet<Student>();
}
