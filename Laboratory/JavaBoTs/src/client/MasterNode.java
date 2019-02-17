package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.Task;

/**
 * MasterNode
 */
public class MasterNode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String ip = "localhost";
        System.setProperty("java.security.policy", "file:./src/client/client.policy");
        try {
            Registry registry = LocateRegistry.getRegistry(ip);

            Task[] BoT = { new Task("T1", "ImageProcessing", (long) 5000),
                    new Task("T2", "ImageProcessing", (long) 10000), new Task("T3", "ImageProcessing", (long) 15000),
                    new Task("T4", "ImageProcessing", (long) 20000), new Task("T5", "ImageProcessing", (long) 30000),
                    new Task("T6", "ImageProcessing", (long) 5000), new Task("T7", "ImageProcessing", (long) 10000),
                    new Task("T8", "ImageProcessing", (long) 15000), new Task("T9", "ImageProcessing", (long) 20000),
                    new Task("T10", "ImageProcessing", (long) 30000),

                    new Task("T11", "DataMining", (long) 5000), new Task("T12", "DataMining", (long) 10000),
                    new Task("T13", "DataMining", (long) 15000), new Task("T14", "DataMining", (long) 20000),
                    new Task("T15", "DataMining", (long) 30000), new Task("T16", "DataMining", (long) 5000),
                    new Task("T17", "DataMining", (long) 10000), new Task("T18", "DataMining", (long) 15000),
                    new Task("T19", "DataMining", (long) 20000), new Task("T20", "DataMining", (long) 30000),
                    new Task("T21", "DataMining", (long) 5000), new Task("T22", "DataMining", (long) 10000),
                    new Task("T23", "DataMining", (long) 15000), new Task("T24", "DataMining", (long) 20000),
                    new Task("T25", "DataMining", (long) 30000), new Task("T26", "DataMining", (long) 5000),
                    new Task("T27", "DataMining", (long) 10000), new Task("T28", "DataMining", (long) 15000),
                    new Task("T29", "DataMining", (long) 20000), new Task("T30", "DataMining", (long) 30000),

                    new Task("T31", "Bioinformatics", (long) 5000), new Task("T32", "Bioinformatics", (long) 10000),
                    new Task("T33", "Bioinformatics", (long) 15000), new Task("T34", "Bioinformatics", (long) 20000),
                    new Task("T35", "Bioinformatics", (long) 30000), new Task("T36", "Bioinformatics", (long) 5000),
                    new Task("T37", "Bioinformatics", (long) 10000), new Task("T38", "Bioinformatics", (long) 15000),
                    new Task("T39", "Bioinformatics", (long) 20000), new Task("T40", "Bioinformatics", (long) 30000),
                    new Task("T41", "Bioinformatics", (long) 5000), new Task("T42", "Bioinformatics", (long) 10000),
                    new Task("T43", "Bioinformatics", (long) 15000), new Task("T44", "Bioinformatics", (long) 20000),
                    new Task("T45", "Bioinformatics", (long) 30000) };
            for (Task task : BoT) {
                System.out.println("Sending execution task: " + task.getTaskId());
                ExecutionThread ex = new ExecutionThread(task, registry);
                ex.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}