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

    /**
     * onEditClick is called by the clickListener when the user interacts with the edit button
     * of a card in the RecyclerView.
     *
     * @param id the ID of the task to edit.
     * */
    override fun onEditClick(id: Int) {
        editTask(id)
    }

    /**
     * onCompletedClick is called by the clickListener when the user interacts with the complete button
     * of a card in the RecyclerView.
     *
     * @param id the ID of the task to mark as completed.
     * */
    override fun onCompletedClick(id: Int) {
        completedTask(id)
    }

    /**
     * onItemClick is called by the clickListener when the user interacts with the delete button
     * of a card in the RecyckerView.
     *
     * @param id the ID of the task to delete.
     * */
    override fun onItemClick(id: Int) {
        deleteTask(id)
    }

    /**
     * Updates the RecyclerView with any new data.
     * Call whenever you add tasks to the database and want to see them in the RecyclerView.
     * */
    fun updateRecyclerView(){
        loadData()
        taskAdapter.notifyDataSetChanged()
    }

    /**
     * loadTasks gets all the uncompleted tasks from the database and adds them into an ArrayList.
     *
     * @return ArrayList of all uncompleted task objects.
     * */
    fun loadTasks() :ArrayList<Task>{
        try {
            val dbHandler = DBHandler(this, null, null, 1)
            return dbHandler.loadUncompletedHandler()
        } catch (e: Exception) {
            Log.d(debugTag, "Got $e when trying to load tasks in Main Activity.$e")
        }
        return ArrayList()
    }


    /**
     * Called from each task to delete itself.
     * @param id the of the task to delete.
     * */
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

    /**
     * openForm opens the add task form activity. This allows the user to add a task to the database.
     *
     * */
    fun openForm(view: View?){
        val intent = Intent(this@MainActivity, TaskFormActivity::class.java)
        startActivity(intent)
    }

    /**
     * openCompletedTasks opens the completed Tasks activity. This allows the user to see all their
     * completed tasks.
     * */
    fun openCompletedTasks(view: View?){
        val intent = Intent(this@MainActivity, CompletedTasksActivity::class.java)
        startActivity(intent)
    }

    /**
     * completedTask finds the relavent task from the database, takes it out, marks it as completed,
     * and then adds it back into the database.
     * */
    fun completedTask(id: Int) {
         var task = Task()
         try {
             task = findTask(id, this) //Could we not just update the completed flag in the database? this just seems wasteful...
             task.setCompleted(true)
             updateTask(task, this)
             updateRecyclerView()

         } catch (e: Exception) {
             Log.d(debugTag, "Got $e while trying to complete task in Main Activity!")
         }
    }
    /**
     * editTask find the given task in the database and changes it to reflect any updates.
     * @param id the ID of the task to edit.
     * */
    fun editTask(id: Int){
        var task = findTask(id, this)

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