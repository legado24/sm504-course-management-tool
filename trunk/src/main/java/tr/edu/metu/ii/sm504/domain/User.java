package tr.edu.metu.ii.sm504.domain;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;
import org.springframework.roo.classpath.operations.jsr303.RooUploadedFile;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import tr.edu.metu.ii.sm504.jsf.ApplicationBean;

@RooJavaBean
@RooToString
@RooJpaActiveRecord(table = "USER")
public class User extends AuditableEntity implements UserDetails {

    @NotNull
    private String username;

    @NotNull
    private String name;

    @NotNull
    private String surname;

    @NotNull
    private String password;

    @NotNull
    @Email
    private String email;

    private String address;

    @NotNull
    @Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(style = "M-")
    private Date dueDate;

    @NotNull
    private Boolean status;

    @RooUploadedFile(contentType = "image/jpeg")
    @Lob
    private byte[] photo;

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<Role> roles = new HashSet<Role>();

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for (Role role : this.getRoles()) {
            authorities.add(new GrantedAuthorityImpl(role.getName()));
        }

        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return (new Date().after(dueDate));
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isAccountNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return status;
    }

    public static User findUserByUsername(String username) {
        if (StringUtils.isEmpty(username)) {
            return null;
        }

        return (User) entityManager().createQuery("select u from User u where u.username = '" + username + "'").getSingleResult();
    }
}
