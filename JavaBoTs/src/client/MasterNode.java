/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package client;

import interfaces.Bioinformatics;
import interfaces.DataMining;
import interfaces.ImageProcessing;
import interfaces.Task;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pmeji
 */
public class MasterNode implements DataMining, Bioinformatics, ImageProcessing {

    public MasterNode() {
    }

    public static void registerBio() {
        try {
            String name = "Bioinformatics";
            MasterNode engine = new MasterNode();
            Bioinformatics stub
                    = (Bioinformatics) UnicastRemoteObject.exportObject((Remote) engine, 0);
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
            DataMining stub
                    = (DataMining) UnicastRemoteObject.exportObject((Remote) engine, 0);
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
            ImageProcessing stub
                    = (ImageProcessing) UnicastRemoteObject.exportObject((Remote) engine, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);

            System.out.println("ImageProcessing bound");
        } catch (Exception e) {
            System.err.println("ImageProcessing exception:");
            e.printStackTrace();
        }
    }
    
    
    public boolean createTaskBag(Task aTask){
        if(aTask.getRequirementId()=="DataMining"){
            
        }else if ("ImageProcessing"){
            
        }else{
                
                }
        
        return true;
    }
        /**
         * @param args the command line arguments
         */
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "file:./src/client/client.policy" );
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            LocateRegistry.createRegistry(1099);
        } catch (Exception e) {
            System.err.println("ImageProcessing exception:");
            e.printStackTrace();
        }
        
        registerImage();
        registerData();
        registerBio();
        // TODO code application logic here
    }

    @Override
    public Task executeBioTask(Task aTask) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    public Task executeDataTask(Task aTask) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    

    public Task executeImageTask(Task aTask) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
