package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import interfaces.Bioinformatics;
import interfaces.DataMining;
import interfaces.ImageProcessing;
import interfaces.Task;

/**
 * SlaveNode
 */
public class SlaveNode implements Bioinformatics, DataMining, ImageProcessing {
    public SlaveNode() {
        super();
    }

    public void registry(String service) {
        System.setProperty("java.security.policy", "file:./src/server/server.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            SlaveNode engine = new SlaveNode();
            if (service.equals("Bioinformatics")) {
                Bioinformatics stub = (Bioinformatics) UnicastRemoteObject.exportObject(engine, 0);
                Registry registry = LocateRegistry.getRegistry();
                registry.rebind(service, stub);
                System.out.println("Bioinformatics Engine bound");
            } else if (service.equals("DataMining")) {
                DataMining stub = (DataMining) UnicastRemoteObject.exportObject(engine, 0);
                Registry registry = LocateRegistry.getRegistry();
                registry.rebind(service, stub);
                System.out.println("DataMining Engine bound");
            } else {
                ImageProcessing stub = (ImageProcessing) UnicastRemoteObject.exportObject(engine, 0);
                Registry registry = LocateRegistry.getRegistry();
                registry.rebind(service, stub);
                System.out.println("ImageProcessing Engine bound");
            }
        } catch (Exception e) {
            System.err.println("ComputeEngine exception:");
            e.printStackTrace();
        }
    }

    @Override
    public int executeImageTask(Task aTask) throws RemoteException {
        try {
            Thread.sleep(aTask.getLength());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        aTask.setOutput("ImageOutput");
        return (int) aTask.getLength();
    }

    @Override
    public int executeDataTask(Task aTask) throws RemoteException {
        try {
            Thread.sleep(aTask.getLength());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        aTask.setOutput("DataOutput");
        return (int) aTask.getLength();
    }

    @Override
    public int executeBioTask(Task aTask) throws RemoteException {
        try {
            Thread.sleep(aTask.getLength());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        aTask.setOutput("BioOutput");
        return (int) aTask.getLength();
    }
    
}