package server;

import java.rmi.NotBoundException;
import java.rmi.Remote;
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
    public void registerBio() {
        try {
            String name = "Bioinformatics";
            SlaveNode engine = new SlaveNode();
            Bioinformatics stub = (Bioinformatics) UnicastRemoteObject.exportObject((Remote) engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);

            System.out.println("BioInformatics bound");
        } catch (Exception e) {
            System.err.println("BioInformatics exception:");
            e.printStackTrace();
        }
    }

    public void registerData() {
        try {
            String name = "DataMining";
            SlaveNode engine = new SlaveNode();
            DataMining stub = (DataMining) UnicastRemoteObject.exportObject((Remote) engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);

            System.out.println("DataMining bound");
        } catch (Exception e) {
            System.err.println("DataMining exception:");
            e.printStackTrace();
        }
    }

    public void registerImage() {
        try {
            String name = "ImageProcessing";
            SlaveNode engine = new SlaveNode();
            ImageProcessing stub = (ImageProcessing) UnicastRemoteObject.exportObject((Remote) engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);

            System.out.println("ImageProcessing bound");
        } catch (Exception e) {
            System.err.println("ImageProcessing exception:");
            e.printStackTrace();
        }
    }

    @Override
    public int executeBioTask(Task aTask) {
        try {
            Thread.sleep(aTask.getLength() * 20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return aTask.getOutput();
    }

    @Override
    public int executeDataTask(Task aTask) {
        try {
            Thread.sleep(aTask.getLength() * 20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return aTask.getOutput();
    }

    @Override
    public int executeImageTask(Task aTask) {
        try {
            Thread.sleep(aTask.getLength() * 20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return aTask.getOutput();
    }

    public static void lookUpFor(Task task) {
        try {
            Registry registry = LocateRegistry.getRegistry("localhost");
            String service = task.getRequirementId();
            if (service == "BioInformatics") {
                Bioinformatics bioinformatics = (Bioinformatics) registry.lookup(task.getRequirementId());
                bioinformatics.executeBioTask(task);
            } else if (service == "DataMining") {
                DataMining dataMining = (DataMining) registry.lookup(task.getRequirementId());
                dataMining.executeDataTask(task);
            } else {
                ImageProcessing imageProcessing = (ImageProcessing) registry.lookup(task.getRequirementId());
                imageProcessing.executeImageTask(task);
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }

    public SlaveNode() {
        this.registerBio();
        this.registerData();
        this.registerImage();
    }
    
}