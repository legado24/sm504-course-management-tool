package tr.edu.metu.ii.sm504.security.role;

import tr.edu.metu.ii.sm504.domain.Role;
import tr.edu.metu.ii.sm504.domain.User;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 13.03.2012
 * Time: 08:10
 * To change this template use File | Settings | File Templates.
 */
public enum UserEnum {

    INSTRUCTOR(1L), ASSISTANT(2L), STUDENT(3L);

    private Long roleId;

    private UserEnum() {
    }

    private UserEnum(Long roleId) {
        this.roleId = roleId;
    }

    public static boolean isInstructor(User user) {
        for (Iterator<Role> iterator = user.getRoles().iterator(); iterator.hasNext(); ) {
            Role role = iterator.next();
            if (role.getId().equals(INSTRUCTOR.getRoleId())) {
                return true;
            }
        }

        return false;
    }

    public static boolean isAssistant(User user) {
        for (Iterator<Role> iterator = user.getRoles().iterator(); iterator.hasNext(); ) {
            Role role = iterator.next();
            if (role.getId().equals(ASSISTANT.getRoleId())) {
                return true;
            }
        }

        return false;
    }

    public static boolean isStudent(User user) {
        for (Iterator<Role> iterator = user.getRoles().iterator(); iterator.hasNext(); ) {
            Role role = iterator.next();
            if (role.getId().equals(STUDENT.getRoleId())) {
                return true;
            }
        }

        return false;
    }

    public Long getRoleId() {
        return roleId;
    }
}
