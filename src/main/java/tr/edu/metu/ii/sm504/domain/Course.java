package tr.edu.metu.ii.sm504.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 16.03.2012
 * Time: 08:23
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "COURSE")
public class Course extends AuditableEntity{

    @NotNull
    @Column(name = "NAME")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "INSTRUCTOR_ID")
    private Instructor instructor;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SEMESTER_ID")
    private Semester semester;

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        this.semester = semester;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
