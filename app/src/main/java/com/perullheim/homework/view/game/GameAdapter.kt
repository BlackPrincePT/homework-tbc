package com.perullheim.homework.view.game

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.perullheim.homework.databinding.GameSlotViewHolderBinding
import com.perullheim.homework.model.GameSlot

class GameAdapter(private val listener: Listener) :
    ListAdapter<GameSlot, GameAdapter.GameSlotViewHolder>(GameSlotsDiffUtil()) {

    interface Listener {
        fun onSlotClick(pos: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameSlotViewHolder {
        return GameSlotViewHolder(
            GameSlotViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: GameSlotViewHolder, position: Int) {
        holder.onBind()
    }

    inner class GameSlotViewHolder(private val binding: GameSlotViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            val model = getItem(adapterPosition)

            binding.tvGameSlot.setOnClickListener {
                if (model.value == null) {
                    listener.onSlotClick(adapterPosition)

                    binding.tvGameSlot.text = model.value?.name
                }
            }
        }
    }

    private class GameSlotsDiffUtil : DiffUtil.ItemCallback<GameSlot>() {
        override fun areItemsTheSame(oldItem: GameSlot, newItem: GameSlot): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GameSlot, newItem: GameSlot): Boolean {
            return oldItem == newItem
        }
    }
}