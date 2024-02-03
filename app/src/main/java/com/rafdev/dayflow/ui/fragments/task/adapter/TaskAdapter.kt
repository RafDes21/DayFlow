package com.rafdev.dayflow.ui.fragments.task.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafdev.dayflow.R
import com.rafdev.dayflow.data.db.enteties.NoteEntity

class TaskAdapter(
    private val tasks: List<NoteEntity>,
    private val onClickListener: (NoteEntity) -> Unit,
) : RecyclerView.Adapter<TaskViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return TaskViewHolder(layout.inflate(R.layout.layout_description_hour, parent, false), onClickListener)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.render(tasks[position])
//        holder.itemView.setOnClickListener {
//            onClickListener(tasks[holder.adapterPosition])
//        }
    }
}