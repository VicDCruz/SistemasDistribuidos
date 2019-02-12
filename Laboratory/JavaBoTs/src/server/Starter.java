package server;

import java.rmi.registry.LocateRegistry;

import client.MasterNode;
import interfaces.Task;

/**
 * Starter
 */
public class Starter {

    public static void main(String[] args) {
        System.setProperty("java.security.policy", "file:./src/client/client.policy");
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            LocateRegistry.createRegistry(1099);
        } catch (Exception e) {
            System.err.println("ImageProcessing exception:");
            e.printStackTrace();
        }
        new SlaveNode();
        Task[] bagBio = {
            new Task(1, "BioInformatics", 5)
        };
        Task[] bagImage = {
            new Task(1, "ImageProcessing", 5)
        };
        Task[] bagData = {
            new Task(1, "DataMining", 5)
        };
        System.out.println("Hola");
        MasterNode masterNode = new MasterNode(bagBio, bagImage, bagData);
        masterNode.createTaskBag("localhost");
    }
}