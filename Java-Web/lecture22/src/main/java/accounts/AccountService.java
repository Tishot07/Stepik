package accounts;

import java.util.HashMap;
import java.util.Map;

public class AccountService {

    final Map<String, UserProfile> users = new HashMap<>();
    final Map<String, UserProfile> session = new HashMap<>();

    public void addUser(UserProfile user) {
        users.put(user.getUser(), user);
    }

    public UserProfile getUser(String user) {
        return users.get(user);
    }

    public UserProfile getSession(String sessia) {
        return session.get(sessia);
    }

    public void addSessia(String sessia, UserProfile user) {
        session.put(sessia, user);
    }

    public void deleteSession(String sessia) {
        session.remove(sessia);
    }
}
