package tr.edu.metu.ii.sm504.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tr.edu.metu.ii.sm504.domain.User;
import tr.edu.metu.ii.sm504.service.UserService;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 28.02.2012
 * Time: 15:05
 * To change this template use File | Settings | File Templates.
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl  implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userService.findUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("No user named : \"" + username + "\" is found.");
        }



        return user;
    }


}
