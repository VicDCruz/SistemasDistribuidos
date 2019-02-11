package client;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import interfaces.Bioinformatics;
import interfaces.DataMining;
import interfaces.ImageProcessing;
import interfaces.Task;

/**
 * MasterNode
 */
public class MasterNode implements Bioinformatics, DataMining, ImageProcessing {

    public static void registerBio() {
        try {
            String name = "BioInformatics";
            MasterNode engine = new MasterNode();
            Bioinformatics stub = (Bioinformatics) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);

            System.out.println("BioInformatics bound");
        } catch (Exception e) {
            System.err.println("BioInformatics exception:");
            e.printStackTrace();
        }
    }

    public static void registerData() {
        try {
            String name = "DataMining";
            MasterNode engine = new MasterNode();
            DataMining stub = (DataMining) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);

            System.out.println("DataMining bound");
        } catch (Exception e) {
            System.err.println("DataMining exception:");
            e.printStackTrace();
        }
    }

    public static void registerImage() {
        try {
            String name = "ImageProcessing";
            MasterNode engine = new MasterNode();
            ImageProcessing stub = (ImageProcessing) UnicastRemoteObject.exportObject(engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);

            System.out.println("ImageProcessing bound");
        } catch (Exception e) {
            System.err.println("ImageProcessing exception:");
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        System.setProperty("java.security.policy", "file:./src/client/client.policy");

        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            LocateRegistry.createRegistry(1099);
        } catch (RemoteException e) {
            e.printStackTrace();
        }

        MasterNode.registerBio();
        MasterNode.registerData();
        MasterNode.registerImage();      
    }

    @Override
    public Task executeImageTask(Task aTask) {
        return null;
    }

    @Override
    public Task executeDataTask(Task aTask) {
        return null;
    }

    @Override
    public Task executeBioTask(Task aTask) {
        return null;
    }

    public boolean createTaskBag() {
        return true;
    }
}