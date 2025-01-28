package com.perullheim.homework.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.perullheim.homework.databinding.UserItemViewHolderBinding
import com.perullheim.homework.model.data.UserDto

class UsersListAdapter :
    PagingDataAdapter<UserDto, UsersListAdapter.UserItemViewHolder>(UserDiffUtil()) {

    inner class UserItemViewHolder(private val binding: UserItemViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val model = getItem(position)

            if (model != null)
                with(binding) {
                    tvFullName.text = model.fullName
                    tvEmail.text = model.email

                    Glide.with(itemView)
                        .load(model.avatar)
                        .into(imgProfile)
                }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        return UserItemViewHolder(
            UserItemViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        holder.onBind(position)
    }

    private class UserDiffUtil : DiffUtil.ItemCallback<UserDto>() {
        override fun areItemsTheSame(oldItem: UserDto, newItem: UserDto): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UserDto, newItem: UserDto): Boolean {
            return oldItem == newItem
        }
    }
}