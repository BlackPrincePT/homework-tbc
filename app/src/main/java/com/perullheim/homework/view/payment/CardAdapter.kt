package com.perullheim.homework.view.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.perullheim.homework.databinding.MastercardCardBinding
import com.perullheim.homework.databinding.VisaCardBinding
import com.perullheim.homework.model.Card
import com.perullheim.homework.model.CardType

class CardAdapter(private val onLongClick: (card: Card) -> Unit) :
    ListAdapter<Card, RecyclerView.ViewHolder>(CardDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            CardType.VISA.ordinal -> VisaCardViewHolder(
                VisaCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )

            else -> MastercardCardViewHolder(
                MastercardCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is VisaCardViewHolder -> holder.onBind()
            is MastercardCardViewHolder -> holder.onBind()
        }
    }

    override fun getItemViewType(position: Int): Int {
        return getItem(position).type.ordinal
    }

    inner class VisaCardViewHolder(private val binding: VisaCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            val model = getItem(adapterPosition)

            with(binding) {
                tvCardholderNameValue.text = model.cardholderName
                tvCardNumber.text = model.cardNumber
                tvValidThru.text = model.expirationDate

                root.setOnLongClickListener {
                    onLongClick.invoke(model)
                    true
                }
            }
        }
    }

    inner class MastercardCardViewHolder(private val binding: MastercardCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            val model = getItem(adapterPosition)

            with(binding) {
                tvCardholderNameValue.text = model.cardholderName
                tvCardNumber.text = model.cardNumber
                tvValidThru.text = model.expirationDate

                root.setOnLongClickListener {
                    onLongClick.invoke(model)
                    true
                }
            }
        }
    }

    class CardDiffUtil : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem == newItem
        }

    }
}