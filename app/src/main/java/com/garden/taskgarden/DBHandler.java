package com.garden.taskgarden;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
//import java.lang.StringBuilder;

import androidx.annotation.Nullable;

public class DBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "taskDB.db";
    public static final String TABLE_NAME = "Tasks";
    public static final String COLUMN_ID = "TaskID";
    public static final String COLUMN_NAME = "TaskTitle";
    public static final String COLUMN_DESCRIPTION = "TaskDescription";
    public static final String COLUMN_COMPLETED = "Completed";
    public static final String COLUMN_COMPLETEDBY = "CompletedBy";


    public DBHandler(Context context,String name,SQLiteDatabase.CursorFactory factory, int version) {

        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARYKEY, " +
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

    public String loadHandler(){
        String result = "";
        String query ="SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query,null);
        while(cursor.moveToNext()){
            int result_0 = cursor.getInt(0);
            String result_1 = cursor.getString(1);
            result += String.valueOf(result_0) + " " +result_1 + System.getProperty("line.separator");
        }
        cursor.close();
        db.close();
        return result;
    }
// Need to check whether an entry already exists before adding :)
    public void addHandler(Task task){
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, task.getTaskID());
        values.put(COLUMN_NAME,task.getTitle());
        values.put(COLUMN_COMPLETED,task.getCompleted());
        values.put(COLUMN_COMPLETEDBY,task.getTimeToCompletedBy());
        values.put(COLUMN_DESCRIPTION,task.getTaskDescription());
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
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
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = '"+String.valueOf(ID)+"'";
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

    public boolean updateName(int ID, String taskName){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        //args.put(COLUMN_ID,ID);
        args.put(COLUMN_NAME,taskName);
        return db.update(TABLE_NAME,args,COLUMN_ID+" = "+ ID,null) > 0;
    }

    public boolean updateDescription(int ID, String string){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        //args.put(COLUMN_ID,ID);
        args.put(COLUMN_DESCRIPTION,string);
        return db.update(TABLE_NAME,args,COLUMN_ID+" = "+ ID,null) > 0;
    }

    public boolean updateCompleted(int ID, int i){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        //args.put(COLUMN_ID,ID);

        args.put(COLUMN_COMPLETED,i);
        return db.update(TABLE_NAME,args,COLUMN_ID+" = "+ ID,null) > 0;
    }

    public boolean updateDescription(int ID, int i){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        //args.put(COLUMN_ID,ID);
        args.put(COLUMN_COMPLETEDBY,i);
        return db.update(TABLE_NAME,args,COLUMN_ID+" = "+ ID,null) > 0;
    }

}
