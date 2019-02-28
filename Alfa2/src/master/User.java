package master;

/**
 * User
 */
public class User implements Comparable<User>{

    private String name;
    private String password;
    private String ip;
    private int score;

    public User(String name, String password, String ip) {
        this.score = 0;
        this.name = name;
        this.password = password;
        this.ip = ip;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score the score to set
     */
    public void setScore(int score) {
        this.score = score;
    }

    public boolean equals(Object anObject) {
        if (anObject == null) return false;
        if (anObject.getClass() != this.getClass()) return false;
        User newUser = (User) anObject;
        boolean output = newUser.getIp().equals(this.ip) &&
                            newUser.getName().equals(this.name) &&
                            newUser.getPassword().equals(this.password) &&
                            newUser.getScore() == this.score;
        return output;
    }

    @Override
    public int compareTo(User anObject) {
        if (anObject == null) return -1;
        if (anObject.getClass() != this.getClass()) return -1;
        User newUser = (User) anObject;
        int output = newUser.getIp().compareTo(this.ip);
        return output;
    }

}