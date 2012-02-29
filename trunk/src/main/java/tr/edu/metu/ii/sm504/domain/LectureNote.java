package tr.edu.metu.ii.sm504.domain;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "LECTURE_NOTE")
public class LectureNote extends AuditableEntity {

    private String name;

    @ManyToOne
    private Course course;

    @RooUploadedFile(contentType = "application/pdf")
    @Lob
    private byte[] data;
}
