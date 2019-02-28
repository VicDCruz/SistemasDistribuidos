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
    private String ip;
    private int multicastPort;
    private int tcpPort;

    public Information(String ip, int multicastPort, int tcpPort) {
        this.ip = ip;
        this.multicastPort = multicastPort;
        this.tcpPort = tcpPort;
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
}
