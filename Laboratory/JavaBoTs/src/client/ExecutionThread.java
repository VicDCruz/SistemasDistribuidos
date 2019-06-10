package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

import interfaces.Bioinformatics;
import interfaces.DataMining;
import interfaces.ImageProcessing;
import interfaces.Task;

/**
 * ExecutionThread
 */
public class ExecutionThread extends Thread {
    private Task task;
    private Registry registry;

    public ExecutionThread(Task task, Registry registry) {
        this.task = task;
        this.registry = registry;
    }

    public void run() {
        try {
            if (this.task.getRequirementId().equals("DataMining")) {
                int answer;
                DataMining dataMining = (DataMining) registry.lookup(this.task.getRequirementId());
                answer = dataMining.executeDataTask(this.task);
                System.out.println("DataMining task " + this.task.getTaskId() + " obtuvo respuesta: " + answer);
            } else if (this.task.getRequirementId().equals("ImageProcessing")) {
                ImageProcessing imageProcessing = (ImageProcessing) registry.lookup(this.task.getRequirementId());
                int answer = imageProcessing.executeImageTask(this.task);
                System.out.println("ImageProcessing task " + this.task.getTaskId() + " obtuvo respuesta: " + answer);
            } else {
                Bioinformatics bioinformatics = (Bioinformatics) registry.lookup(this.task.getRequirementId());
                int answer = bioinformatics.executeBioTask(this.task);
                System.out.println("Bioinformatics task " + this.task.getTaskId() + " obtuvo respuesta: " + answer);
            }
        } catch (RemoteException | NotBoundException e) {
            e.printStackTrace();
        }
    }
}