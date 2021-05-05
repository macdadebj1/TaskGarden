package com.garden.taskgarden

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import com.garden.taskgarden.DBInterface
import com.garden.taskgarden.R
import com.garden.taskgarden.Task

class TaskFormActivity : AppCompatActivity() {
    var taskId: EditText? = null
    var taskName: EditText? = null
    var taskDescription: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task_form)
    }

    fun addTaskForm(view: View?){
        taskId = findViewById(R.id.etForm_id)
        taskName = findViewById(R.id.etForm_Task)
        taskDescription = findViewById(R.id.etForm_Description)
        try {
            //int ID = Integer.parseInt(taskId.getText().toString());
            val title = taskName!!.text.toString()
            val description = taskDescription!!.text.toString()
            val task = Task()
            //task.updateID(ID);
            task.updateTitle(title)
            task.updateDescription(description)
            DBInterface.addTask(task, this)
        } catch (e: NumberFormatException) {
            //Log.d(debugTag, "Got NumberFormatException while trying to add task in Main Activity!")
        }
    }
}