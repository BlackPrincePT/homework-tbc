package com.perullheim.homework.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.perullheim.homework.R
import com.perullheim.homework.databinding.ChatItemViewHolderBinding
import com.perullheim.homework.model.Chat

class ContactsListAdapter :
    ListAdapter<Chat, ContactsListAdapter.ChatItemViewHolder>(ChatDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatItemViewHolder {
        return ChatItemViewHolder(
            ChatItemViewHolderBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ChatItemViewHolder, position: Int) {
        holder.onBind()
    }

    inner class ChatItemViewHolder(private val binding: ChatItemViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            val model = getItem(adapterPosition)

            with(binding) {
                tvTitle.text = model.owner
                tvLastMessage.text = model.lastMessage
                tvLastMessageTime.text = model.lastActive

                // Unread Messages Indication
                if (model.unreadMessages > 0)
                    tvUnreadMessages.apply {
                        text = model.unreadMessages.toString()
                        visibility = View.VISIBLE
                    }
                else
                    tvUnreadMessages.visibility = View.INVISIBLE

                // Special Message Indication
                when (model.lastMessageType) {
                    Chat.Type.TEXT -> imgSpecialMessageIndication.visibility = View.INVISIBLE
                    else -> {
                        val icon =
                            if (model.lastMessageType == Chat.Type.FILE) R.drawable.ic_attachment else R.drawable.ic_microphone

                        imgSpecialMessageIndication.apply {
                            setImageResource(icon)
                            visibility = View.VISIBLE
                        }
                    }
                }
            }
        }
    }

    private class ChatDiffUtil : DiffUtil.ItemCallback<Chat>() {
        override fun areItemsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Chat, newItem: Chat): Boolean {
            return oldItem == newItem
        }
    }
}