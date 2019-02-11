package interfaces;

import java.io.Serializable;

/**
 * Task
 */
public class Task implements Serializable {
    private int taskId;
    private int requirementId;
    private int length;
    private int output;

    /**
     * @return the taskId
     */
    public int getTaskId() {
        return taskId;
    }

    /**
     * @return the output
     */
    public int getOutput() {
        return output;
    }

    /**
     * @param output the output to set
     */
    public void setOutput(int output) {
        this.output = output;
    }

    /**
     * @return the length
     */
    public int getLength() {
        return length;
    }

    /**
     * @param length the length to set
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * @return the requirementId
     */
    public int getRequirementId() {
        return requirementId;
    }

    /**
     * @param requirementId the requirementId to set
     */
    public void setRequirementId(int requirementId) {
        this.requirementId = requirementId;
    }

    /**
     * @param taskId the taskId to set
     */
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

}