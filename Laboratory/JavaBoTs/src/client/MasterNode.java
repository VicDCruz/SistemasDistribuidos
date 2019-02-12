package client;

import java.rmi.registry.LocateRegistry;

import interfaces.Task;

/**
 * MasterNode
 */
public class MasterNode {

    private Task[] bagBio;
    private Task[] bagImage;
    private Task[] bagData;

    public MasterNode(Task[] bagBio, Task[] bagImage, Task[] bagData) {
        this.bagBio = bagBio;
        this.bagImage = bagImage;
        this.bagData = bagData;
        }

    public boolean createTaskBag(String registryIp) {
        boolean resp = false;

        MyThread m1 = new MyThread(this.bagBio);
        m1.run();
        MyThread m2 = new MyThread(this.bagData);
        m2.run();
        MyThread m3 = new MyThread(this.bagImage);
        m3.run();

        return resp;
    }

    /**
     * @param args the command line arguments
     */
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
    }
}