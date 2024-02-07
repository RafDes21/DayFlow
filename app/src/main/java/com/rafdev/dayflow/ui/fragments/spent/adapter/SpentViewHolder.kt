package com.rafdev.dayflow.ui.fragments.spent.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.rafdev.dayflow.data.db.enteties.SpentEntity
import com.rafdev.dayflow.databinding.LayoutItemDetailBinding

class SpentViewHolder(
    view: View, private val onClickListener: (SpentEntity) -> Unit
) :
    RecyclerView.ViewHolder(view) {

    private val binding = LayoutItemDetailBinding.bind(view)
    fun render(item: SpentEntity) {
        binding.tvTitle.text = item.name
        binding.tvDescription.text = item.description
        binding.tvCategory.text = item.category
        binding.tvPrice.text = item.price.toString()
        binding.tvDate.text = item.date

        binding.ivDelete.setOnClickListener {
            onClickListener(item)
        }

    }


}