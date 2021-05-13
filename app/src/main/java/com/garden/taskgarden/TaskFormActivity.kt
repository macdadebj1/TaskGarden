package com.garden.taskgarden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import com.garden.taskgarden.DBInterface
import com.garden.taskgarden.R
import com.garden.taskgarden.Task

class TaskFormActivity : AppCompatActivity() {
    var taskName: EditText? = null
    var taskDescription: EditText? = null
    var taskToCompleteBy: EditText? = null
    private val debugTag = "TaskFormActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_task_form)
    }

    fun addTaskForm(view: View?){
        taskName = findViewById(R.id.etForm_Task)
        taskDescription = findViewById(R.id.etForm_Description)
        taskToCompleteBy = findViewById(R.id.etForm_CompletedBy)
        try {
            val task = Task()
            task.updateTitle(taskName!!.text.toString())
            task.updateDescription(taskDescription!!.text.toString())
            //task.updateCompletedBy(Integer.parseInt(taskToCompleteBy!!.text.toString()))

            DBInterface.addTask(task, this)
            val intent = Intent(this@TaskFormActivity, MainActivity::class.java)
            startActivity(intent)
        } catch (e: Exception) {
            Log.d(debugTag, "Got $e while trying to add task in TaskFormActivity!")
        }
    }
}