package com.rafdev.dayflow.ui.fragments.spent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafdev.dayflow.R
import com.rafdev.dayflow.data.db.enteties.SpentEntity

class SpentAdapter(private val spentList: List<SpentEntity>) : RecyclerView.Adapter<SpentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpentViewHolder {
        val layout = LayoutInflater.from(parent.context)
        return SpentViewHolder(layout.inflate(R.layout.layout_item_detail, parent, false))
    }

    override fun getItemCount(): Int = spentList.size

    override fun onBindViewHolder(holder: SpentViewHolder, position: Int) {
        holder.render(spentList[position])
    }
}