/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 *
 * @author pmeji
 */
public interface Play extends Remote {
    
    public Credential login(String name, String password, String ip) throws RemoteException;
    //IP servidor
    //Conexion Monstruo
    
}
