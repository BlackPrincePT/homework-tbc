package com.perullheim.homework.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.perullheim.homework.R
import com.perullheim.homework.databinding.UserItemViewHolderBinding
import com.perullheim.homework.model.User

class UserListAdapter : ListAdapter<User, UserListAdapter.UserViewHolder>(UserDiffUtil()) {

    inner class UserViewHolder(private val binding: UserItemViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            val model = getItem(adapterPosition)
            val context = itemView.context

            with(binding) {
                tvFullName.text = model.fullName
                tvAbout.text = model.about
                tvStatus.text = model.activationStatusMsg

                Glide.with(itemView)
                    .load(model.avatar)
                    .placeholder(AppCompatResources.getDrawable(context, R.drawable.ic_person))
                    .into(imgProfile)
                    .onLoadFailed(
                        AppCompatResources.getDrawable(
                            context,
                            R.drawable.img_load_failed_placeholder
                        )
                    )
            }
        }
    }

    private class UserDiffUtil : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            UserItemViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.onBind()
    }
}