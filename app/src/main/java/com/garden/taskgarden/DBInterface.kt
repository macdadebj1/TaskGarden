package com.garden.taskgarden;

import android.content.Context;

/**
 * This Class acts as an abstraction layer between DBHandler.java and any activity that may want to
 * query the database.
 *
 * @author Blake MacDade
 * */
public class DBInterface {

    static String debugTag = "DBInterface";
    static SettingsTalker talker;
    private static String settingsName = "numTasks";


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
        int id;
        talker = new SettingsTalker(context);
        id = talker.getIntEntry(settingsName);
        DBHandler dbHandler = new DBHandler(context, null, null, 1);
        if(id == -1){
            talker.addEntry(settingsName,1);
            id = 1;
        }
        task.updateID(id);
        dbHandler.addHandler(task);
        talker.updateEntry(settingsName,id+1);

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
     * @param task The updated task object.
     * @param context Application Context.
     *
     * @return returns the state of the update, true if successful.
     * */
    public static boolean updateTask(Task task,Context context){
        DBHandler dbHandler = new DBHandler(context, null, null, 1);
        return dbHandler.updateTask(task);
    }

}