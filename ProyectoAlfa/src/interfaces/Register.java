package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Register
 */
public interface Register extends Remote {

    public Information logIn(String username, String password, String ip) throws RemoteException;
}