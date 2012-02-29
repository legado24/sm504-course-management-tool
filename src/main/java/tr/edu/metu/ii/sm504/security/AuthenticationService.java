package tr.edu.metu.ii.sm504.security;

/**
 * Created by IntelliJ IDEA.
 * User: ekisa
 * Date: 28.02.2012
 * Time: 15:03
 * To change this template use File | Settings | File Templates.
 */
public interface AuthenticationService {
    public boolean login(String username, String password);

    public void logout();

    void bypassAuthentication();
}
