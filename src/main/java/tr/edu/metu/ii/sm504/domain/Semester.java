package tr.edu.metu.ii.sm504.domain;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 17.03.2012
 * Time: 07:27
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "SEMESTER")
public class Semester extends AuditableEntity{
    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "DUE_DATE")
    private Date dueDate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }
}
