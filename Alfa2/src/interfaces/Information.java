/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.Serializable;

/**
 *
 * @author daniel
 */
public class Information implements Serializable {
    private final String ip;
    private final String userId;
    private final int multicastPort;
    private final String multicastIp;
    private final int tcpPort;

    public Information(String ip, int multicastPort, int tcpPort, String multicastIp, String userId) {
        this.ip = ip;
        this.userId = userId;
        this.multicastPort = multicastPort;
        this.multicastIp = multicastIp;
        this.tcpPort = tcpPort;
    }

    public String getUserId() {
        return userId;
    }

    public String getIp() {
        return ip;
    }

    public int getMulticastPort() {
        return multicastPort;
    }

    public int getTcpPort() {
        return tcpPort;
    }

    public String getMulticastIp() {
        return multicastIp;
    }
}
