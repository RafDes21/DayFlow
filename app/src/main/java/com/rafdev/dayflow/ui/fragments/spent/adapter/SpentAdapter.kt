package com.rafdev.dayflow.ui.fragments.spent.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rafdev.dayflow.R
import com.rafdev.dayflow.data.db.enteties.SpentEntity

class SpentAdapter(
    private val spentList: List<SpentEntity>,
    private val onClickListener: (SpentEntity) -> Unit
) : RecyclerView.Adapter<SpentViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.layout_item_detail, parent, false)
        return SpentViewHolder(itemView, onClickListener)
    }

    override fun getItemCount(): Int = spentList.size

    override fun onBindViewHolder(holder: SpentViewHolder, position: Int) {
        holder.render(spentList[position])
    }
}