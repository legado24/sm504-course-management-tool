package tr.edu.metu.ii.sm504.service;

import org.apache.commons.lang3.StringUtils;
import org.primefaces.model.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tr.edu.metu.ii.sm504.domain.IUser;
import tr.edu.metu.ii.sm504.domain.Instructor;
import tr.edu.metu.ii.sm504.domain.User;
import tr.edu.metu.ii.sm504.jsf.search.SearchCriteria;
import tr.edu.metu.ii.sm504.repository.InstructorRepository;
import tr.edu.metu.ii.sm504.repository.UserRepository;
import tr.edu.metu.ii.sm504.security.enumerator.UserEnum;
import tr.edu.metu.ii.sm504.util.SpringPropertiesUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 11.03.2012
 * Time: 01:26
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserService implements Serializable {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private InstructorRepository instructorRepository;

    public boolean isLoginEnabled() {
        String loginDisabled = SpringPropertiesUtil.getProperty("login.disabled");
        boolean loginAvailability = false;
        if (StringUtils.isEmpty(loginDisabled) && loginDisabled.equalsIgnoreCase("false")) {
            loginAvailability = true;
        }

        return loginAvailability;
    }

    public boolean isInstructor(UsernamePasswordAuthenticationToken token) {
        User user = (User) token.getPrincipal();
        return UserEnum.isInstructor(user);
    }

    public boolean isAssistant(UsernamePasswordAuthenticationToken token) {
        User user = (User) token.getPrincipal();
        return UserEnum.isAssistant(user);
    }

    public boolean isStudent(UsernamePasswordAuthenticationToken token) {
        User user = (User) token.getPrincipal();
        return UserEnum.isStudent(user);
    }


    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    public List<User> findUsers(SearchCriteria searchCriteria, int first, String orderBy, SortOrder sortOrder) {
        return userRepository.findUsers(searchCriteria, first, orderBy, sortOrder);
    }

    public int getNumberOfUsers(SearchCriteria searchCriteria) {
        return userRepository.getNumberOfUsers(searchCriteria);
    }

    public long countUsers() {
        return userRepository.countUsers();
    }

    public List<User> findAllUsers() {
        return userRepository.findAllUsers();
    }

    public User findUser(Long id) {
        return userRepository.findUser(id);
    }

    public List<User> findUserEntries(int firstResult, int maxResults) {
        return userRepository.findUserEntries(firstResult, maxResults);
    }

    public void persist(User user) {
        userRepository.persist(user);
    }

    public User merge(User updatedUser) {
        return userRepository.merge(updatedUser);
    }

    public void remove(User user) {
        userRepository.remove(user);
    }

    public Instructor findInstructorByUser(User user) {
        return instructorRepository.findInstructorByUser(user);
    }
}
