package com.garden.taskgarden;

import android.content.Context;
import android.util.Log;

/**
 * This Class acts as an abstraction layer between DBHandler.java and any activity that may want to
 * query the database.
 *
 * @author Blake MacDade
 * */
public class DBInterface {

    String debugTag = "DBInterface";
    // added a comment to test the commit


    /**
     * findTask is a simple method with no error checking whatsoever, does a simple
     * database lookup and returns any result.
     *
     * @param ID The task ID that the database will be queried with.
     * @param context Application Context.
     *
     * @return new Task object that represents data with primary key of ID, CAN BE NULL!
     * */
    public static Task findTask(int ID, Context context) {
        DBHandler dbHandler = new DBHandler(context, null, null, 1);
        return dbHandler.findHandler(ID);
    }
    /**
     * addTask is a simple method with no error checking, adds a new task to the database.
     *
     * @param task Task object to add to the database.
     * @param context Application Context.
     *
     * */
    public static void addTask(Task task, Context context){
        DBHandler dbHandler = new DBHandler(context, null, null, 1);
        dbHandler.addHandler(task);

    }

    /**
     * deleteTask is a simple method with no error checking, deletes a task from the database;
     *
     * @param ID The task ID of the record to delete.
     * @param context Application Context.
     *
     * @return returns the state of the deletion, true if successful.
     * */
    public static boolean deleteTask(int ID, Context context){
        DBHandler dbHandler = new DBHandler(context, null, null, 1);
        return dbHandler.deleteHandler(ID);
    }

    /**
     * updateTask is a simple method with no error checking, deletes a task from the database;
     *
     * @param ID The task ID of the record to delete.
     * @param context Application Context.
     * @param updatedTitle updated title of the task.
     *
     * @return returns the state of the update, true if successful.
     * */
    public static boolean updateTask(int ID,Context context, String updatedTitle){
        DBHandler dbHandler = new DBHandler(context, null, null, 1);
        return dbHandler.updateName(ID, updatedTitle);
    }

}
