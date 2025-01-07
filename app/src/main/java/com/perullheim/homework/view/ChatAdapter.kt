package com.perullheim.homework.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.perullheim.homework.databinding.MessagePartnerViewHolderBinding
import com.perullheim.homework.databinding.MessageSelfViewHolderBinding
import com.perullheim.homework.model.Message
import com.perullheim.homework.model.Sender
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

class ChatAdapter : ListAdapter<Message, RecyclerView.ViewHolder>(ChatDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when {
            viewType == Sender.SELF.viewType -> MessageSelfViewHolder(
                MessageSelfViewHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> MessagePartnerViewHolder(
                MessagePartnerViewHolderBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is MessageSelfViewHolder -> holder.onBind()
            is MessagePartnerViewHolder -> holder.onBind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).sender.viewType
    }

    inner class MessageSelfViewHolder(private val binding: MessageSelfViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            val model = getItem(adapterPosition)

            with(binding) {
                tvMessageBody.text = model.body

                tvTimestamp.text = formatDate(model.timestamp)
            }
        }
    }

    inner class MessagePartnerViewHolder(private val binding: MessagePartnerViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            val model = getItem(adapterPosition)

            with(binding) {
                tvMessageBody.text = model.body

                tvTimestamp.text = formatDate(model.timestamp)
            }
        }
    }

    private fun formatDate(timestamp: LocalDateTime): String {

        val now = LocalDateTime.now()
        val currentDate = now.toLocalDate()

        val messageLocalDate = timestamp.toLocalDate()

        return when (messageLocalDate) {
            currentDate -> timestamp.format(
                DateTimeFormatter.ofPattern(
                    "'Today, 'h:mma",
                    Locale.ENGLISH
                )
            )

            currentDate.minusDays(1) -> timestamp.format(
                DateTimeFormatter.ofPattern(
                    "Yesterday, h:mma",
                    Locale.ENGLISH
                )
            )

            else -> timestamp.format(
                DateTimeFormatter.ofPattern(
                    "d MMM, h:mma",
                    Locale.ENGLISH
                )
            )
        }
    }

    class ChatDiffUtil : DiffUtil.ItemCallback<Message>() {
        override fun areItemsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Message, newItem: Message): Boolean {
            return oldItem == newItem
        }

    }
}