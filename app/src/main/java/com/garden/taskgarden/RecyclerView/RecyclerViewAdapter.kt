package com.garden.taskgarden.RecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.garden.taskgarden.DBInterface
import com.garden.taskgarden.MainActivity
import com.garden.taskgarden.R
import com.garden.taskgarden.Task
//import kotlinx.android.synthetic.main.row_layout.view.*
import kotlinx.android.synthetic.main.task_card_view.view.*
import java.util.*

/**
 * This class is responsible for creating and maintaining the RecyclerViews.
 * */
class RecyclerViewAdapter(private val listener: OnItemClickListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    //var context: Context
    //var listener: View.OnClickListener? = null

    private var tasks: List<Task> = ArrayList()

    /**
     * Called when the adapter is first created.
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.task_card_view,parent,false)
        )
        /*context = parent.context
        val lInflater = LayoutInflater.from(context)
        val v = lInflater.inflate(R.layout.row_layout, parent, false)
        return ViewHolder(v)*/
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        /*val index = holder.adapterPosition
        holder.title.text = taskList[position].title
        holder.description.text = taskList[position].description
        holder.view.setOnClickListener { listener!!.onClick(holder.view) }*/
        when(holder){
            is TaskViewHolder -> {
                holder.bind(tasks[position])
            }
        }
    }

    /**
     * getItemCount returns the number of items in the RecyclerView.
     * */
    override fun getItemCount(): Int {
        return tasks.size
    }

    /**
     * submitList used to send an ArrayList to the RecyclerView
     *
     * @param taskList new ArrayList for the RecyclerView to display.
     * */
    fun submitList(taskList: List<Task>){
        tasks = taskList
    }

    /**
     * TaskViewHolder is an inner class which holds a single task, RecyclerView holds one for each
     * task.
     * */
    inner class TaskViewHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView),
        View.OnClickListener{

        val title = itemView.textView
        var description = itemView.textView2
        var time = itemView.time_completed
        var id = 0;
        val deleteButton = itemView.image_delete
        val completedButton = itemView.image_complete
        val editButton = itemView.textView

        /**
         * bind is called whenever each TaskViewHolder is instantiated
         * @param task the task object that will be represented by the ViewHolder.
         * */
        fun bind(task: Task){
            title.text = task.title
            description.text = task.description
            time.text = task.timeToCompletedBy
            id = task.iD;
            Log.d("RecyclerViewAdapter","BIND TASK! $id")
            //time.setText(task.timeToCompletedBy)
        }

        init {
            deleteButton.setOnClickListener(this)
            completedButton.setOnClickListener(this)
            editButton.setOnClickListener(this)
        }
        /**
         * onClick is called by the ViewHolder assigned to each object.
         * */
        override fun onClick(v: View?) {
            Log.d("RecyclerViewAdapter",id.toString())

            when(v?.getId()) {
                R.id.image_delete -> listener.onItemClick(id)
                R.id.image_complete -> listener.onCompletedClick(id)
                R.id.textView -> listener.onEditClick(id)
            }
        }

    }

    interface OnItemClickListener {
        fun onItemClick(id: Int)
        fun onCompletedClick(id: Int)
        fun onEditClick(id: Int)
    }


}