package tr.edu.metu.ii.sm504.domain;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 16.03.2012
 * Time: 08:26
 * To change this template use File | Settings | File Templates.
 */
public interface IUser extends UserDetails {
    public String getUsername();
    public void setUsername(String username);

    public String getName();
    public void setName(String name);

    public String getSurname();
    public void setSurname(String surname);

    public String getPassword();
    public void setPassword(String password);

    public String getEmail();
    public void setEmail(String email);

    public String getAddress();
    public void setAddress(String address);

    public Date getDueDate();
    public void setDueDate(Date dueDate);

    public Boolean getStatus();
    public void setStatus(Boolean status);

    public byte[] getPhoto();
    public void setPhoto(byte[] photo);

    public Set<Role> getRoles();
    public void setRoles(Set<Role> roles);
}
