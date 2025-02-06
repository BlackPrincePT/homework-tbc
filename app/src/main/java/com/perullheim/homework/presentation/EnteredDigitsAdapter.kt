package com.perullheim.homework.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.perullheim.homework.R
import com.perullheim.homework.databinding.EnteredDigitItemBinding

class EnteredDigitsAdapter :
    ListAdapter<Int?, EnteredDigitsAdapter.DigitsViewHolder>(ITEM_COMPARATOR) {

    inner class DigitsViewHolder(private val binding: EnteredDigitItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: Int?) {
            val context = itemView.context
            val drawableRes =
                if (item == null) R.drawable.entered_digit_item_empty else R.drawable.entered_digit_item_full

            binding.root.background = AppCompatResources.getDrawable(context, drawableRes)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DigitsViewHolder {
        return DigitsViewHolder(
            EnteredDigitItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DigitsViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<Int?>() {
    override fun areItemsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Int, newItem: Int): Boolean {
        return oldItem == newItem
    }
}