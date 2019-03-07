/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.Serializable;
/**
 *
 * @author pmeji
 */
public class Credential implements Serializable {
    private String InetAddressNum;
    private int socketGroupNum;

    public Credential(String InetAddressNum, int socket) {
        this.InetAddressNum = InetAddressNum;
        this.socketGroupNum = socket;
    }

    public Credential() {
    }

    public String getInetAddressNum() {
        return InetAddressNum;
    }

    public void setInetAddressNum(String InetAddressNum) {
        this.InetAddressNum = InetAddressNum;
    }

    public int getSocketGroupNum() {
        return socketGroupNum;
    }

    public void setSocketGroupNum(int socketGroupNum) {
        this.socketGroupNum = socketGroupNum;
    }


    
    
    
    
}
