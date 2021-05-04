package com.garden.taskgarden

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*

class RecyclerViewAdapter(private val taskList: ArrayList<Task>, application: Application) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    var context: Context
    var listener: View.OnClickListener? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val lInflater = LayoutInflater.from(context)
        val v = lInflater.inflate(R.layout.row_layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val index = holder.adapterPosition
        holder.title.text = taskList[position].title
        holder.description.text = taskList[position].description
        holder.view.setOnClickListener { listener!!.onClick(holder.view) }
    }

    override fun getItemCount(): Int {
        return taskList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var description: TextView
        var view: View

        init {
            title = itemView.findViewById<View>(R.id.Tasktitle) as TextView
            description = itemView.findViewById<View>(R.id.Taskdescription) as TextView
            view = itemView
        }
    }

    init {
        context = application
    }
}