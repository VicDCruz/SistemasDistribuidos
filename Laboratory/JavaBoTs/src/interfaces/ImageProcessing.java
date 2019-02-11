package interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * ImageProcessing
 */
public interface ImageProcessing extends Remote{

    public Task executeImageTask(Task aTask) throws RemoteException;    
}