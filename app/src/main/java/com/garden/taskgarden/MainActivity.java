package com.garden.taskgarden;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView lst;
    EditText taskId;
    EditText taskName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lst = (TextView) findViewById(R.id.lst);
        taskId = (EditText) findViewById(R.id.taskId);
        taskName = (EditText) findViewById(R.id.taskName);
    }



    public void loadTasks(View view){
        DBHandler dbHandler = new DBHandler(this,null,null,1);
        lst.setText(dbHandler.loadHandler());
        taskId.setText("");
        taskName.setText("");

    }

    public void addTask(View view){
        DBHandler dbHandler = new DBHandler(this,null,null ,1);
        int id = Integer.parseInt(taskId.getText().toString());
        String title = taskName.getText().toString();
        Task task = new Task();
        task.updateID(id);
        task.updateTitle(title);
        dbHandler.addHandler(task);
        taskId.setText("");
        taskName.setText("");
    }
    public void findTask(View view){
        DBHandler dbHandler = new DBHandler(this,null,null ,1);
        Task task = dbHandler.findHandler(Integer.parseInt(taskId.getText().toString()));
        if(task != null){
            lst.setText(String.valueOf(task.getTaskID())+" "+task.getTitle()+ System.getProperty("line.separator"));
            taskId.setText("");
            taskName.setText("");
        } else{
            lst.setText("No Match Found");
            taskId.setText("");
            taskName.setText("");
        }
    }

    public void deleteTask(View view){
        DBHandler dbHandler = new DBHandler(this,null,null ,1);
        boolean result = dbHandler.deleteHandler(Integer.parseInt(taskId.getText().toString()));
        if(result){
            taskId.setText("");
            taskName.setText("");
            lst.setText("Record Deleted");
        }else{
            taskId.setText("No Match Found");
        }
    }

    public void updateTask(View view){
        DBHandler dbHandler = new DBHandler(this,null,null ,1);
        boolean result = dbHandler.updateName(Integer.parseInt(taskId.getText().toString()), taskName.getText().toString());
        if(result){
            taskId.setText("");
            taskName.setText("");
            lst.setText("Record Updated");
        }else {
            taskId.setText("No Match Found");
        }
    }
}