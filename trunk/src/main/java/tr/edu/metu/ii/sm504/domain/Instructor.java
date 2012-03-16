package tr.edu.metu.ii.sm504.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 16.03.2012
 * Time: 08:29
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "INSTRUCTOR")
public class Instructor extends AuditableEntity implements IUser {

    @NotNull
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="USER_ID")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "instructor")
    private List<Course> courses = new ArrayList<Course>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Transient
    public Collection<GrantedAuthority> getAuthorities() {
        return getUser().getAuthorities();
    }

    @Transient
    public boolean isAccountNonExpired() {
        return getUser().isAccountNonExpired();
    }


    @Transient
    public boolean isAccountNonLocked() {
        return getUser().isAccountNonLocked();
    }

    @Transient
    public boolean isCredentialsNonExpired() {
        return getUser().isCredentialsNonExpired();
    }

    @Transient
    public boolean isEnabled() {
        return getUser().isEnabled();
    }

    @Transient
    public String getUsername() {
        return getUser().getUsername();
    }


    public void setUsername(String username) {
        getUser().setUsername(username);
    }

    @Transient
    public String getName() {
        return getUser().getName();
    }


    public void setName(String name) {
        getUser().setName(name);
    }

    @Transient
    public String getSurname() {
        return getUser().getSurname();
    }


    public void setSurname(String surname) {
        getUser().setSurname(surname);
    }

    @Transient
    public String getPassword() {
        return getUser().getPassword();
    }


    public void setPassword(String password) {
        getUser().setPassword(password);
    }

    @Transient
    public String getEmail() {
        return getUser().getEmail();
    }


    public void setEmail(String email) {
        getUser().setEmail(email);
    }

    @Transient
    public String getAddress() {
        return getUser().getAddress();
    }


    public void setAddress(String address) {
        getUser().setAddress(address);
    }

    @Transient
    public Date getDueDate() {
        return getUser().getDueDate();
    }


    public void setDueDate(Date dueDate) {
        getUser().setDueDate(dueDate);
    }

    @Transient
    public Boolean getStatus() {
        return getUser().getStatus();
    }


    public void setStatus(Boolean status) {
        getUser().setStatus(status);
    }

    @Transient
    public byte[] getPhoto() {
        return getUser().getPhoto();
    }


    public void setPhoto(byte[] photo) {
        getUser().setPhoto(photo);
    }

    public boolean equals(Object o) {
        return getUser().equals(o);
    }


    public int hashCode() {
        return getUser().hashCode();
    }

    @Transient
    public Set<Role> getRoles() {
        return getUser().getRoles();
    }

    @Override
    public void setRoles(Set<Role> roles) {
        getUser().setRoles(roles);
    }
}
