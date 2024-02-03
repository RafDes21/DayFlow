package com.rafdev.dayflow.ui.fragments.task.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rafdev.dayflow.data.db.enteties.NoteEntity
import com.rafdev.dayflow.databinding.LayoutDescriptionHourBinding

class TaskViewHolder(view: View, private val onClickListener: (NoteEntity) -> Unit,) : RecyclerView.ViewHolder(view) {

    private val binding = LayoutDescriptionHourBinding.bind(view)

    fun render(item: NoteEntity) {

        binding.tvDescription.text = item.description
        binding.tvHour.text = item.hour
        binding.ivDelete.setOnClickListener{
            onClickListener(item)
        }

    }

}