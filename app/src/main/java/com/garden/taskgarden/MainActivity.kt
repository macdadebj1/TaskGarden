package com.garden.taskgarden

//refactor to deal with all imports of DBInterface!
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.garden.taskgarden.DBInterface.findTask
import com.garden.taskgarden.DBInterface.removeTask
import com.garden.taskgarden.DBInterface.updateTask
import com.garden.taskgarden.RecyclerView.PaddingItemDecoration
import com.garden.taskgarden.RecyclerView.RecyclerViewAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.edit_popout.view.*
import kotlinx.android.synthetic.main.task_card_view.*

class MainActivity : AppCompatActivity(), RecyclerViewAdapter.OnItemClickListener {
    private var settingsTalker: SettingsTalker? = null

    // nullable type
    private lateinit var taskAdapter: RecyclerViewAdapter

    //var adapter: RecyclerViewAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //dbInterface = new DBInterface();
        settingsTalker = SettingsTalker(this)
        initRecyclerView()
        loadData()

    }
    /**
     * Loads list of task objects and sends it to the task adapter.
     * */
    private fun loadData(){
        val data = loadTasks()
        taskAdapter.submitList(data)
    }

    private fun initRecyclerView(){
        taskList.apply{
            layoutManager = LinearLayoutManager(this@MainActivity)
            val topSpacingDecoration = PaddingItemDecoration(30)
            addItemDecoration((topSpacingDecoration))
            taskAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter=taskAdapter
        }

    }

    override fun onEditClick(id: Int) {
        editTask(id)
    }

    override fun onCompletedClick(id: Int) {
        completedTask(id)
    }

    override fun onItemClick(id: Int) {
        deleteTask(id)
    }

    /**Updates the recycler view with any new data.*/
    fun updateRecyclerView(){
        loadData()
        taskAdapter.notifyDataSetChanged()
    }

    fun loadTasks() :ArrayList<Task>{
        try {
            val dbHandler = DBHandler(this, null, null, 1)
            return dbHandler.loadUncompletedHandler()
        } catch (e: Exception) {
            Log.d(debugTag, "Got $e when trying to load tasks in Main Activity.$e")
        }
        return ArrayList()
    }


    /**Called from each task to delete itself.*/
    fun deleteTask(id: Int) {
        try {
            //val id: Int = Integer.parseInt(taskId!!.text.toString())
            if (removeTask(id, this)) {
                updateRecyclerView()
            }
        } catch (e: Exception) {
            Log.d(debugTag, "Got $e while trying to delete task in Main Activity!")
        }
    }

    companion object {
        //DBInterface dbInterface;
        const val debugTag = "MainActivity"
    }

    fun openForm(view: View?){
        val intent = Intent(this@MainActivity, TaskFormActivity::class.java)
        startActivity(intent)
    }

    fun openCompletedTasks(view: View?){
        val intent = Intent(this@MainActivity, CompletedTasksActivity::class.java)
        startActivity(intent)
    }

    fun completedTask(id: Int) {
         var task = Task()
         try {
             // get the task object
             task = findTask(id, this)
             // change the task completed value to true
             task.setCompleted(true)
             // update the task
             updateTask(task, this)
             //update recyclerview
             updateRecyclerView()

         } catch (e: Exception) {
             Log.d(debugTag, "Got $e while trying to complete task in Main Activity!")
         }
    }

    fun editTask(id: Int){
        var task = Task()
        // get the task object
        task = findTask(id, this)

        val popoutView = LayoutInflater.from(this).inflate(R.layout.edit_popout, null)
        val buildPopout = AlertDialog.Builder(this).setView(popoutView).setTitle("Edit Task")
        val showPopout = buildPopout.show()
        popoutView.btn_accept.setOnClickListener {
            showPopout.dismiss()
            val editTaskText = popoutView.edit_task.text.toString()
            val editTaskDescription = popoutView.edit_description.text.toString()
            task.setTitle(editTaskText)
            task.setDescription(editTaskDescription)
            updateTask(task, this)
            updateRecyclerView()
        }

    }
}