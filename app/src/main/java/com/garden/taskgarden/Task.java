package com.garden.taskgarden;

public class Task {
    private int taskID;
    private String taskTitle;
    private String taskDescription;
    private boolean completed;
    private int timeCompletedBy;


    public Task(String title, String description, long completeBy){
        this.taskTitle = title;
        this.taskDescription = description;
        this.timeCompletedBy = completeBy;
        this.completed = false;
    }

    public Task(){} //Should probably remove..? doesn't really matter, just for simple testing...

    public void updateTitle(String newTitle){
        this.taskTitle = newTitle;
    }

    public void updateDescription(String newDescription){
        this.taskDescription = newDescription;
    }

    public void markCompleted(){
        this.completed = true;
    }

    public String getTitle(){
        return this.taskTitle;
    }

    public String getTaskDescription(){
        return this.taskDescription;
    }

    public int getTaskID(){
        return taskID;
    }

    public boolean getCompleted(){
        return this.completed;
    }

    public int getTimeToCompletedBy(){
        return timeCompletedBy;
    }

    public String toString(){
        return taskTitle + ";" + taskDescription + ";";
    }
}
