package com.perullheim.homework.presentation.resorts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.perullheim.homework.databinding.ResortItemViewHolderBinding
import com.perullheim.homework.domain.model.Resort

class ResortsListAdapter : ListAdapter<Resort, ResortsListAdapter.ResortItem>(ITEM_COMPARATOR) {

    inner class ResortItem(private val binding: ResortItemViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Resort) {
            with(binding) {
                tvLocation.text = item.location
                tvReactionCount.text = item.reactionCount.toString()
                tvTitle.text = item.title
                tvPrice.text = item.price

                Glide.with(itemView)
                    .load(item.coverUrl)
                    .into(imgResort)

                rbRating.rating = item.rating.toFloat()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResortItem {
        return ResortItem(
            ResortItemViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ResortItem, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Resort>() {
    override fun areItemsTheSame(oldItem: Resort, newItem: Resort): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Resort, newItem: Resort): Boolean {
        return oldItem == newItem
    }
}