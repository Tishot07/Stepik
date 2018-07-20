package accounts;

public class UserProfile {

    private String user;
    private String pwd;

    public UserProfile(String user, String pwd) {
        this.user = user;
        this.pwd = pwd;
    }

    public String getUser() {
        return user;
    }

    public String getPwd() {
        return pwd;
    }
}
