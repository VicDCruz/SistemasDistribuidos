package interfaces;


/**
 * Task
 */
public class Task  {
    private int taskId;
    private String requirementId;
    private int length;
    private int output;

    public Task() {
    }

    public Task(int taskId, String requirement, int length) {
        this.taskId = taskId;
        this.requirementId = requirement;
        this.length = length;
        this.output = 3;
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
