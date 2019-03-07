package master;

/**
 * User
 */
public class User {
    private String userName;
    private String password;
    private String ip;

    public User(String userName, String password, String ip) {
        this.userName = userName;
        this.password = password;
        this.ip = ip;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }
    
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }
}