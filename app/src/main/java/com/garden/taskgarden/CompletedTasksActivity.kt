package com.garden.taskgarden

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.garden.taskgarden.RecyclerView.CompletedTasksAdaptor
import com.garden.taskgarden.RecyclerView.PaddingItemDecoration
import com.garden.taskgarden.RecyclerView.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.completed_tasks.*

class CompletedTasksActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.completed_tasks)

        initRecyclerView()
    }

    private fun initRecyclerView(){
        taskList.apply{
            val completedTasksList = loadCompletedTasks()
            completedTaskAdaptor.layoutManager = LinearLayoutManager(this@CompletedTasksActivity)
            val topSpacingDecoration = PaddingItemDecoration(30)
            completedTaskAdaptor.addItemDecoration((topSpacingDecoration))
            completedTaskAdaptor.adapter = CompletedTasksAdaptor(completedTasksList)
            //completedTaskAdaptor.setHasFixedSize(true)
        }

    }

    /**
     * loadCompletedTasks talks to the database to get all tasks that are marked as completed.
     * @return ArrayList of completed Task objects.
     * */
    fun loadCompletedTasks() : ArrayList<Task>{
        try {
            val dbHandler = DBHandler(this, null, null, 1)
            return dbHandler.loadCompletedHandlers()
        } catch (e: Exception) {
            Log.d(MainActivity.debugTag, "Got $e when trying to load tasks in Main Activity.$e")
        }
        return ArrayList()
    }

    fun openMain(view: View?){
        val intent = Intent(this@CompletedTasksActivity, MainActivity::class.java)
        startActivity(intent)
    }


}