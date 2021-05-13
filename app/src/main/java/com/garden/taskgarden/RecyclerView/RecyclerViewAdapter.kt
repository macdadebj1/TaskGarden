package com.garden.taskgarden.RecyclerView

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.garden.taskgarden.R
import com.garden.taskgarden.Task
import kotlinx.android.synthetic.main.row_layout.view.*
import java.util.*

//class RecyclerViewAdapter(private val taskList: ArrayList<Task>, application: Application) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
class RecyclerViewAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    //var context: Context
    //var listener: View.OnClickListener? = null

    private var tasks: List<Task> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TaskViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.row_layout,parent,false)
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

    override fun getItemCount(): Int {
        return tasks.size
    }

    fun submitList(taskList: List<Task>){
        tasks = taskList
    }
    /*class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var description: TextView
        //var timeToCompleteBy: EditText
        var view: View

        init {
            title = itemView.findViewById<View>(R.id.Tasktitle) as TextView
            description = itemView.findViewById<View>(R.id.Taskdescription) as TextView
            //timeToCompleteBy = itemView.findViewById<View>(R.id.timeToCompleteBy)
            view = itemView
        }
    }

    init {
        context = application
    }*/

    class TaskViewHolder constructor(itemView: View):RecyclerView.ViewHolder(itemView){
        val title = itemView.Tasktitle
        var descripton = itemView.Taskdescription
        var time = itemView.timeToCompleteBy

        fun bind(task: Task){
            title.text = task.title
            descripton.text = task.description
            //time.setText(task.timeToCompletedBy)
        }
    }


}