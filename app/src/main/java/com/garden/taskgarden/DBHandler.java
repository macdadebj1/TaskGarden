package com.garden.taskgarden;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.util.ArrayList;


/**
 * DO NOT INTERFACE WITH THIS CLASS DIRECTLY!!!
 * IF YOU WANT TO TALK TO THE DATABASE, PLEASE TALK TO DBInterface.java
 * */
public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "taskDB.db";
    public static final String TABLE_NAME = "Tasks";
    public static final String COLUMN_ID = "TaskID";
    public static final String COLUMN_NAME = "TaskTitle";
    public static final String COLUMN_DESCRIPTION = "TaskDescription";
    public static final String COLUMN_COMPLETED = "Completed";
    public static final String COLUMN_COMPLETEDBY = "CompletedBy";

    public static final String debugTag = "DBHandler";

    public DBHandler(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (";
        CREATE_TABLE += COLUMN_ID + " INTEGER PRIMARY KEY NOT NULL, " +
                COLUMN_NAME + " TEXT, " +
                COLUMN_DESCRIPTION + " TEXT, " +
                COLUMN_COMPLETED + " INTEGER, " +
                COLUMN_COMPLETEDBY + " DATETIME "+
                ")";
        db.execSQL(CREATE_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public ArrayList<Task> loadHandler(){
        ArrayList<Task> TaskArray = new ArrayList<>();
        String query ="SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){
            TaskArray.add(new Task(cursor.getString(1),cursor.getString(2),cursor.getInt(4)));
        }
        cursor.close();
        db.close();
        return TaskArray;
    }
// Need to check whether an entry already exists before adding :)
    public void addHandler(Task task){
        try {
            ContentValues values = new ContentValues();
            values.put(COLUMN_ID, task.getTaskID());
            values.put(COLUMN_NAME, task.getTitle());
            values.put(COLUMN_COMPLETED, task.getCompleted());
            values.put(COLUMN_COMPLETEDBY, task.getTimeToCompletedBy());
            values.put(COLUMN_DESCRIPTION, task.getTaskDescription());
            SQLiteDatabase db = this.getWritableDatabase();
            db.insert(TABLE_NAME, null, values);
            db.close();
        } catch(SQLiteConstraintException e){
            Log.d(debugTag,"Got SQLiteConstraintException in addHandler method of DBHandler");
        }
    }

    public Task findHandler(int taskId){

        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = '" + taskId + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        Task task = new Task();
        if(cursor.moveToFirst()){
            cursor.moveToFirst();
            task.updateID(cursor.getInt(0));
            task.updateTitle(cursor.getString(1));
            task.updateDescription(cursor.getString(2));
            switch(cursor.getInt(3)){
                case 1:
                    task.updateCompleted(true);
                    break;
                case 0:
                default:
                    task.updateCompleted(false);
                    break;

            }
            task.updateCompletedBy(cursor.getInt(4));
        }
        cursor.close();
        db.close();
        return task;
    }

    public boolean deleteHandler(int ID){
        boolean result = false;
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = '"+ ID +"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        Task task = new Task();
        if(cursor.moveToFirst()){
            task.updateID(Integer.parseInt(cursor.getString(0)));
            db.delete(TABLE_NAME,COLUMN_ID +"=?",new String[]{String.valueOf(task.getTaskID())});
            // for the love of GOD, please revisit this steaming pile of crap, took from online tutorial and just want it to work :)
            cursor.close();
            result = true;
        }
        db.close();
        return result;
    }

    public boolean updateTask(Task task){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        //args.put(COLUMN_ID,ID);
        args.put(COLUMN_NAME, task.getTitle());
        args.put(COLUMN_COMPLETED, task.getCompleted());
        args.put(COLUMN_COMPLETEDBY, task.getTimeToCompletedBy());
        args.put(COLUMN_DESCRIPTION, task.getTaskDescription());
        return db.update(TABLE_NAME,args,COLUMN_ID+" = "+ task.getTaskID(),null) > 0;
    }


}
