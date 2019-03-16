package master;

import java.util.UUID;

/**
 * User
 */
public class User implements Comparable<User>{

    private final String id;
    private String name;
    private String password;
    private String ip;
    private int tcpPort;
    private int score;

    public User(String name, String password, String ip) {
        this.id = UUID.randomUUID().toString();
        this.score = 0;
        this.name = name;
        this.password = password;
        this.ip = ip;
    }
    
    public User(String ip, String id) {
        this.id = id;
        this.score = 0;
        this.ip = ip;
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @return the tcpPort
     */
    public int getTcpPort() {
        return tcpPort;
    }

    /**
     * @param tcpPort the tcpPort to set
     */
    public void setTcpPort(int tcpPort) {
        this.tcpPort = tcpPort;
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

    @Override
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
        int output = newUser.getId().compareTo(this.id);
        if (output != 0) {
            if (newUser.getName() != null && newUser.getPassword() != null){
                if (newUser.getName().compareTo(this.name) == 0 &&
                        newUser.getPassword().compareTo(this.password) == 0) {
                    output = 0;
                }
            }
        }
        return output;
    }

}