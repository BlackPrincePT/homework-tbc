package com.perullheim.homework.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.perullheim.homework.databinding.UserItemViewHolderBinding
import com.perullheim.homework.domain.model.User
import com.perullheim.homework.utils.setImage

class UsersListAdapter :
    PagingDataAdapter<User, UsersListAdapter.UserItemViewHolder>(ITEM_COMPARATOR) {

    inner class UserItemViewHolder(private val binding: UserItemViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: User) {
            with(binding) {
                tvFullName.text = item.fullName
                tvEmail.text = item.email
                imgProfile.setImage(item.avatar)
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
        getItem(position)?.let {
            holder.bind(it)
        }
    }
}

private val ITEM_COMPARATOR = object : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }
}