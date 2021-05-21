package com.garden.taskgarden

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.garden.taskgarden.DBInterface.addTask
import com.garden.taskgarden.DBInterface.deleteTask
import com.garden.taskgarden.DBInterface.findTask
import com.garden.taskgarden.DBInterface.updateTask
import com.garden.taskgarden.RecyclerView.PaddingItemDecoration
import com.garden.taskgarden.RecyclerView.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private var settingsTalker: SettingsTalker? = null
    var taskId: EditText? = null
    var taskName: EditText? = null

    // nullable type
    private lateinit var taskAdapter: RecyclerViewAdapter

    //var adapter: RecyclerViewAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        taskId = findViewById(R.id.taskId)
        taskName = findViewById(R.id.taskName)
        //dbInterface = new DBInterface();
        settingsTalker = SettingsTalker(this)
        initRecyclerView()
        loadData()
    }

    private fun loadData(){
        val data = loadTasks()
        taskAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        taskList.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacingDecoration = PaddingItemDecoration(30)
            addItemDecoration((topSpacingDecoration))
            taskAdapter = RecyclerViewAdapter()
            adapter=taskAdapter
        }

    }

    fun updateRecyclerView(){
        loadData()
        taskAdapter.notifyDataSetChanged()
    }

    fun loadTasks() :ArrayList<Task>{
        try {
            val dbHandler = DBHandler(this, null, null, 1)
            return dbHandler.loadHandler()
        } catch (e: Exception) {
            Log.d(debugTag, "Got $e when trying to load tasks in Main Activity.$e")
        }
        return ArrayList()
    }

    fun addTask(view: View?) {
        try {
            val title = taskName!!.text.toString()
            val task = Task()
            task.updateTitle(title)
            task.updateDescription("TestDescription!!!")
            addTask(task, this)
            taskId!!.setText("")
            taskName!!.setText("")
            updateRecyclerView()
        } catch (e: Exception) {
            Log.d(debugTag, "Got $e while trying to add task in Main Activity!")
        }
    }

    fun findTask(view: View): Task? {
        try {
            val id: Int = Integer.parseInt(taskId!!.text.toString())
            Log.d(debugTag, "Value of ID int: " + taskId!!.text.toString())
            val task = findTask(id, this)
            taskId!!.setText("")
            taskName!!.setText("")
            return task
        } catch (e: Exception) {
            Log.d(debugTag, "Got $e while trying to find task in Main Activity!")
        }
        return null
    }

    fun deleteTask(view: View?) {
        try {
            val id: Int = Integer.parseInt(taskId!!.text.toString())
            if (deleteTask(id, this)) {
                taskId!!.setText("")
                taskName!!.setText("")

                updateRecyclerView()
            } else {
                taskId!!.setText("No Match Found")
            }
        } catch (e: Exception) {
            Log.d(debugTag, "Got $e while trying to delete task in Main Activity!")
        }
    }

    fun updateTask(view: View?) {
        val task = Task()

        try {
            task.updateTitle(taskName!!.text.toString())
            task.updateID(Integer.parseInt(taskId!!.text.toString()))
            if (updateTask(task, this)) {
                taskId!!.setText("")
                taskName!!.setText("")
                updateRecyclerView()
            } else {
                taskId!!.setText("No Match Found")
            }
        } catch (e: Exception) {
            Log.d(debugTag, "Got $e while trying to update task in Main Activity!")
        }
    }

    companion object {
        //DBInterface dbInterface;
        private const val debugTag = "MainActivity"
    }

    fun openForm(view: View?){
        val intent = Intent(this@MainActivity, TaskFormActivity::class.java)
        startActivity(intent)

    }
}