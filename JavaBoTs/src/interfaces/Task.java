/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;


/**
 *
 * @author pmeji
 */
public class Task  {
    private int taskId;
    private String requirementId;
    private int length;
    private int output;

    public Task() {
    }

    public Task(int taskId, String requirement, int length, int output) {
        this.taskId = taskId;
        this.requirementId = requirement;
        this.length = length;
        this.output = output;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getOutput() {
        return output;
    }

    public void setOutput(int output) {
        this.output = output;
    }
    
    
   
    
    
}
