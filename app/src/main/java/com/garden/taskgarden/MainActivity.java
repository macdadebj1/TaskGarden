package com.garden.taskgarden;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView lst;
    EditText taskId;
    EditText taskName;

    String debugTag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lst = (TextView) findViewById(R.id.lst);
        taskId = (EditText) findViewById(R.id.taskId);
        taskName = (EditText) findViewById(R.id.taskName);
    }



    public void loadTasks(View view){
        try {
            DBHandler dbHandler = new DBHandler(this, null, null, 1);
            lst.setText(dbHandler.loadHandler());
            taskId.setText("");
            taskName.setText("");
        } catch(Exception e){
            Log.d(debugTag,"Got unexpected Exception when trying to load tasks in Main Activity." + e.toString());
        }
    }

    public void addTask(View view){
        try {
            DBHandler dbHandler = new DBHandler(this, null, null, 1);
            int ID = Integer.parseInt(taskId.getText().toString());
            String title = taskName.getText().toString();
            Task task = new Task();
            task.updateID(ID);
            task.updateTitle(title);
            dbHandler.addHandler(task);
            taskId.setText("");
            taskName.setText("");
        } catch(java.lang.NumberFormatException e){
            Log.d(debugTag,"Got NumberFormatException while trying to add task in Main Activity!");
        }

    }
    public void findTask(View view) throws NullTaskException {
        try {
            int ID = Integer.parseInt(taskId.getText().toString());

            DBHandler dbHandler = new DBHandler(this, null, null, 1);
            Log.d(debugTag, "Value of ID int: " + taskId.getText().toString());
            Task task = dbHandler.findHandler(ID);

            if (task != null) {
                lst.setText(String.valueOf(task.getTaskID()) + " " + task.getTitle() + System.getProperty("line.separator"));
                taskId.setText("");
                taskName.setText("");
            } else {
                lst.setText("No Match Found");
                taskId.setText("");
                taskName.setText("");
                throw new NullTaskException("This task returned a null in findTask method in MainActivity!");
            }
        } catch(java.lang.NumberFormatException e){
            Log.d(debugTag,"Got NumberFormatException while trying to find task in Main Activity!");
        }
    }

    public void deleteTask(View view){
        try {
            int ID = Integer.parseInt(taskId.getText().toString());
            DBHandler dbHandler = new DBHandler(this, null, null, 1);
            boolean result = dbHandler.deleteHandler(ID);
            if (result) {
                taskId.setText("");
                taskName.setText("");
                lst.setText("Record Deleted");
            } else {
                taskId.setText("No Match Found");
            }
        } catch(java.lang.NumberFormatException e){
            Log.d(debugTag, "Got NumberFormatException while trying to delete task in Main Activity!");
        }
    }

    public void updateTask(View view){
        try {
            int ID = Integer.parseInt(taskId.getText().toString());
            DBHandler dbHandler = new DBHandler(this, null, null, 1);
            boolean result = dbHandler.updateName(ID, taskName.getText().toString());
            if (result) {
                taskId.setText("");
                taskName.setText("");
                lst.setText("Record Updated");
            } else {
                taskId.setText("No Match Found");
            }
        } catch(java.lang.NumberFormatException e){
            Log.d(debugTag, "Got NumberFormatException while trying to update task in Main Activity!");
        }
    }
}