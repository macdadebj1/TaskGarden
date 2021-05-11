package com.garden.taskgarden

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.garden.taskgarden.DBInterface.addTask
import com.garden.taskgarden.DBInterface.deleteTask
import com.garden.taskgarden.DBInterface.findTask
import com.garden.taskgarden.DBInterface.updateTask

class MainActivity : AppCompatActivity() {
    var settingsTalker: SettingsTalker? = null
    var taskListGUIObject: RecyclerView? = null
    var taskId: EditText? = null
    var taskName: EditText? = null
    var adapter: RecyclerViewAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskListGUIObject = findViewById(R.id.taskList)
        taskId = findViewById(R.id.taskId)
        taskName = findViewById(R.id.taskName)
        //dbInterface = new DBInterface();
        settingsTalker = SettingsTalker(this)
    }

    fun loadTasks(view: View?) {
        try {
            val dbHandler = DBHandler(this, null, null, 1)
            val TaskList = dbHandler.loadHandler()
            //taskListGUIObject.setText(TaskList.get(0).getTitle() +": "+ TaskList.get(0).getTaskDescription());
            //setContentView(R.layout.row_layout);
            adapter = RecyclerViewAdapter(TaskList, application)
            taskListGUIObject!!.adapter = adapter
            taskListGUIObject!!.layoutManager = LinearLayoutManager(this@MainActivity)
            taskId!!.setText("")
            taskName!!.setText("")
        } catch (e: Exception) {
            Log.d(debugTag, "Got unexpected Exception when trying to load tasks in Main Activity.$e")
        }
    }

    fun addTask(view: View?) {
        try {
            //int ID = Integer.parseInt(taskId.getText().toString());
            val title = taskName!!.text.toString()
            val task = Task()
            //task.updateID(ID);
            task.updateTitle(title)
            task.updateDescription("TestDescription!!!")
            addTask(task, this)
            taskId!!.setText("")
            taskName!!.setText("")
        } catch (e: NumberFormatException) {
            Log.d(debugTag, "Got NumberFormatException while trying to add task in Main Activity!")
        }
    }

    fun findTask(view: View?): Task? {
        try {
            val ID: Int = Integer.parseInt(taskId!!.text.toString())
            Log.d(debugTag, "Value of ID int: " + taskId!!.text.toString())
            val task = findTask(ID, this)
            taskId!!.setText("")
            taskName!!.setText("")
            if (task == null) {
                throw NullTaskException("This task returned a null in findTask method in MainActivity!")
            }
            return task
        } catch (e: NumberFormatException) {
            Log.d(debugTag, "Got NumberFormatException while trying to find task in Main Activity!")
        }
        return null
    }

    fun deleteTask(view: View?) {
        try {
            val ID: Int = Integer.parseInt(taskId!!.text.toString())
            if (deleteTask(ID, this)) {
                taskId!!.setText("")
                taskName!!.setText("")
                //taskListGUIObject.setText("Record Deleted");
            } else {
                taskId!!.setText("No Match Found")
            }
        } catch (e: NumberFormatException) {
            Log.d(debugTag, "Got NumberFormatException while trying to delete task in Main Activity!")
        }
    }

    fun updateTask(view: View?) {
        val task = Task()
        task.updateTitle(taskName!!.text.toString())
        task.updateID(Integer.parseInt(taskId!!.text.toString()))
        try {
            if (updateTask(task, this)) {
                taskId!!.setText("")
                taskName!!.setText("")
                //taskListGUIObject.setText("Record Updated");
            } else {
                taskId!!.setText("No Match Found")
            }
        } catch (e: NumberFormatException) {
            Log.d(debugTag, "Got NumberFormatException while trying to update task in Main Activity!")
        }
    }

    companion object {
        //DBInterface dbInterface;
        private const val debugTag = "MainActivity"
    }

    // For some reason .java has an unknown reference, cant find anything that will get it to work
    fun openForm(view: View?){
        val intent = Intent(this@MainActivity, TaskFormActivity::class.java)
        startActivity(intent)

    }
}