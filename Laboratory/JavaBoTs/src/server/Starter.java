package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

/**
 * Starter
 */
public class Starter {

    public static void main(String[] args) throws RemoteException {
        int port = 1099;
        LocateRegistry.createRegistry(port);

        SlaveNode[] slaveNodes = {
            new SlaveNode(),
            new SlaveNode(),
            new SlaveNode()
        };

        String[] services = {
            "Bioinformatics",
            "DataMining",
            "ImageProcessing"
        };

        for (int i = 0; i < services.length; i++) {
            String service = services[i];
            SlaveNode slaveNode = slaveNodes[i];
            slaveNode.registry(service);
        }
    }
}