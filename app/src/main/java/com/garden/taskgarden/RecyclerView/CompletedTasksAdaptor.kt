package com.garden.taskgarden.RecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.garden.taskgarden.R
import com.garden.taskgarden.Task
import kotlinx.android.synthetic.main.completedtask_card.view.*
import kotlinx.android.synthetic.main.task_card_view.view.*

/**
 * This class is responsible for creating and maintaining the RecyclerView for completed_tasks.xml.
 * @author Jacob Gear
 * */
class CompletedTasksAdaptor(private val completedList: List<Task>) :
        RecyclerView.Adapter<CompletedTasksAdaptor.CompletedTasksViewHolder>() {

    /**
     * Called when the adapter is first created.
     * */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedTasksViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.completedtask_card,parent,
                false)
        return CompletedTasksViewHolder(itemView)
    }

    /**
     * Function for binding tasks with there task information
     * */
    override fun onBindViewHolder(holder: CompletedTasksViewHolder, position: Int) {

        when(holder){
            is CompletedTasksAdaptor.CompletedTasksViewHolder -> {
                holder.bind(completedList[position])
            }
        }
    }

    /**
     * Function for getting the ArrayList size of completed tasks
     * */
    override fun getItemCount() = completedList.size

    /**
     * CompletedTasksViewHolder is an inner class which holds a single task, RecyclerView holds
     * one for each task.
     * */
    class CompletedTasksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.completedText
        val description = itemView.completedDescription
        var id = 0;

        fun bind(task: Task){
            title.text = task.title
            description.text = task.description
            id = task.iD;
            Log.d("RecyclerViewAdapter","BIND TASK! $id")

        }

    }

}