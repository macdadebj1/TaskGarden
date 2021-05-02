package com.garden.taskgarden;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    settingsTalker settings = new settingsTalker(this);

    RecyclerView taskListGUIObject;
    EditText taskId;
    EditText taskName;
    RecyclerViewAdapter adapter;

    DBInterface dbInterface;

    private static final String debugTag = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        taskListGUIObject = findViewById(R.id.taskList);
        taskId = findViewById(R.id.taskId);
        taskName = findViewById(R.id.taskName);
        dbInterface = new DBInterface();

    }



    public void loadTasks(View view){
        try {
            DBHandler dbHandler = new DBHandler(this, null, null, 1);
            ArrayList<Task> TaskList = dbHandler.loadHandler();
            //taskListGUIObject.setText(TaskList.get(0).getTitle() +": "+ TaskList.get(0).getTaskDescription());
            setContentView(R.layout.row_layout);
            adapter = new RecyclerViewAdapter(TaskList,getApplication());
            taskListGUIObject.setAdapter(adapter);
            taskListGUIObject.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            taskId.setText("");
            taskName.setText("");
        } catch(Exception e){
            Log.d(debugTag,"Got unexpected Exception when trying to load tasks in Main Activity." + e.toString());
        }
    }

    public void addTask(View view){
        try {
            int ID = Integer.parseInt(taskId.getText().toString());
            String title = taskName.getText().toString();
            Task task = new Task();
            task.updateID(ID);
            task.updateTitle(title);
            dbInterface.addTask(task,this);
            taskId.setText("");
            taskName.setText("");
        } catch(java.lang.NumberFormatException e){
            Log.d(debugTag,"Got NumberFormatException while trying to add task in Main Activity!");
        }

    }
    public void findTask(View view) throws NullTaskException {
        try {
            int ID = Integer.parseInt(taskId.getText().toString());

            Log.d(debugTag, "Value of ID int: " + taskId.getText().toString());
            Task task = dbInterface.findTask(ID,this);
            taskId.setText("");
            taskName.setText("");
            if (task != null) {
                //taskListGUIObject.setText(String.valueOf(task.getTaskID()) + " " + task.getTitle() + System.getProperty("line.separator"));
            } else {
                //taskListGUIObject.setText("No Match Found");
                throw new NullTaskException("This task returned a null in findTask method in MainActivity!");
            }
        } catch(java.lang.NumberFormatException e){
            Log.d(debugTag,"Got NumberFormatException while trying to find task in Main Activity!");
        }
    }

    public void deleteTask(View view){
        try {
            int ID = Integer.parseInt(taskId.getText().toString());
            if (dbInterface.deleteTask(ID,this)) {
                taskId.setText("");
                taskName.setText("");
                //taskListGUIObject.setText("Record Deleted");
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
            if (dbInterface.updateTask(ID,this,taskName.getText().toString())) {
                taskId.setText("");
                taskName.setText("");
                //taskListGUIObject.setText("Record Updated");
            } else {
                taskId.setText("No Match Found");
            }
        } catch(java.lang.NumberFormatException e){
            Log.d(debugTag, "Got NumberFormatException while trying to update task in Main Activity!");
        }
    }
}