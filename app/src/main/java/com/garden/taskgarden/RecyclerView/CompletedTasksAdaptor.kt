package com.garden.taskgarden.RecyclerView

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.garden.taskgarden.R
import com.garden.taskgarden.Task
import kotlinx.android.synthetic.main.task_card_view.view.*

class CompletedTasksAdaptor(private val completedList: List<Task>) :
        RecyclerView.Adapter<CompletedTasksAdaptor.CompletedTasksViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CompletedTasksViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.task_card_view,parent,
                false)
        return CompletedTasksViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CompletedTasksViewHolder, position: Int) {
        val currentItem = completedList[position]

        when(holder){
            is CompletedTasksAdaptor.CompletedTasksViewHolder -> {
                holder.bind(completedList[position])
            }
        }
    }

    override fun getItemCount() = completedList.size

    class CompletedTasksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title = itemView.textView
        val description = itemView.textView2
        var id = 0;

        fun bind(task: Task){
            title.text = task.title
            description.text = task.description
            id = task.iD;
            Log.d("RecyclerViewAdapter","BIND TASK! $id")

        }

    }

}