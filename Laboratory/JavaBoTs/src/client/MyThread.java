package client;

import interfaces.Task;
import server.SlaveNode;

/**
 * MyThread
 */
public class MyThread extends Thread{
    private Task[] bag;
    public MyThread(Task[] bag) {
        this.bag = bag;
    }

    @Override
    public void run() {
        for (Task task : this.bag) {
            SlaveNode.lookUpFor(task);
        }
    }
    
}