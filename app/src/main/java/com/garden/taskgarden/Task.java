package com.garden.taskgarden;


/**
 * Task represents a task object
 *
 * @author Blake MacDade
 * */
public class Task {
    private int taskID;
    private String taskTitle;
    private String taskDescription;
    private boolean completed;
    private int timeCompletedBy;

    /**
     * Constructor for task object.
     *
     * @param title title of the task.
     * @param description description of the task.
     * @param completeBy date to complete the task by.
     * */
    public Task(String title, String description, int completeBy){
        this.taskTitle = title;
        this.taskDescription = description;
        this.timeCompletedBy = completeBy;
        this.completed = false;
    }
    /**
     * Blank constructor for Task.
     * Instantiates all values to their defaults.
     * */
    public Task(){} //Should probably remove..? doesn't really matter, just for simple testing...

    /**
     * updateID updates the id of the object.
     *
     * @param ID the new ID to give the object.
     * */
    public void updateID(int ID){
        this.taskID = ID;
    }

    /**
     * updateTitle updates the title of the object.
     *
     * @param newTitle the new title to give the object.
     * */
    public void updateTitle(String newTitle){
        this.taskTitle = newTitle;
    }

    /**
     * updateDescription updates the description of the object.
     *
     * @param newDescription the new description to give the object.
     * */
    public void updateDescription(String newDescription){
        this.taskDescription = newDescription;
    }

    /**
     * updateCompleted updates the completed state of the object.
     *
     * @param state the new completed state to give the object.
     * */
    public void updateCompleted(boolean state){
        this.completed = state;
    }

    /**
     * updateCompletedBy updates the time to complete by of the object.
     *
     * @param time the new time to complete by to give the object.
     * */
    public void updateCompletedBy(int time){
        this.timeCompletedBy = time;
    }

    /**
     * getTitle getter method for the Task's title.
     *
     * @return title string of the object.
     * */
    public String getTitle(){
        return this.taskTitle;
    }

    /**
     * getDescription
     *
     * @return description of the object.
     * */
    public String getDescription(){
        return this.taskDescription;
    }
    /**
     * getID
     *
     * @return the id of the object.
     * */
    public int getID(){
        return taskID;
    }

    /**
     * getCompleted
     *
     * @return the completed state of the object.
     * */
    public boolean getCompleted(){
        return this.completed;
    }

    /**
     * getTimeToCompleteBy
     *
     * @return time to complete the task by.
     * */
    public int getTimeToCompletedBy(){
        return timeCompletedBy;
    }

    /**
     * toString
     *
     * @return a string representation of the task title and the task description.
     * */
    public String toString(){
        return taskTitle + ";" + taskDescription + ";";
    }
}
