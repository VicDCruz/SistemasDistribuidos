package interfaces;

import java.io.Serializable;

/**
 * Task
 */
public class Task implements Serializable{
    private String taskId;
    private String requirementId;
    private long length;
    private String output;

    public Task() {
        super();
    }

    public Task(String taskId, String requirement, long length) {
        this.taskId = taskId;
        this.requirementId = requirement;
        this.length = length;
        this.output = "";
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public long getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }
}
