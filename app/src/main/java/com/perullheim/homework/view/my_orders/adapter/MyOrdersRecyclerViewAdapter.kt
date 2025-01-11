package com.perullheim.homework.view.my_orders.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.perullheim.homework.R
import com.perullheim.homework.databinding.MyOrderViewHolderBinding
import com.perullheim.homework.model.DeliveryStatus
import com.perullheim.homework.model.Order

class MyOrdersRecyclerViewAdapter(private val onActionClicked: (itemPosition: Int) -> Unit) :
    ListAdapter<Order, MyOrdersRecyclerViewAdapter.MyOrderViewHolder>(OrderDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyOrderViewHolder {
        return MyOrderViewHolder(
            MyOrderViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyOrderViewHolder, position: Int) {
        holder.onBind()
    }

    private fun createCircularDrawable(
        context: Context,
        @ColorRes colorResId: Int
    ): GradientDrawable {
        val color = ContextCompat.getColor(context, colorResId)
        return GradientDrawable().apply {
            shape = GradientDrawable.OVAL
            setColor(color)
        }
    }

    inner class MyOrderViewHolder(private val binding: MyOrderViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            val model = getItem(adapterPosition)
            val context = itemView.context

            with(binding) {
                imgFurniture.setImageResource(model.furniture.imageResId)
                tvTitle.text = model.furniture.name
                iconColor.background =
                    createCircularDrawable(context, model.furniture.color.colorResId)
                tvColorAndQuantity.text = context.getString(
                    R.string.color_and_quantity_template,
                    model.furniture.color.displayName,
                    model.quantity
                )
                tvDeliveryStatus.text = model.deliveryStatus.itemDisplayName
                tvPrice.text = context.getString(R.string.price_template, model.totalPrice)
                btnAction.text = when {
                    model.deliveryStatus == DeliveryStatus.ACTIVE -> context.getString(R.string.track_order)
                    model.deliveryStatus == DeliveryStatus.COMPLETED && model.feedback == null -> {
                        btnAction.setOnClickListener {
                            onActionClicked.invoke(adapterPosition)
                        }

                        context.getString(
                            R.string.leave_review
                        )
                    }
                    else -> context.getString(R.string.buy_again)
                }
            }
        }
    }

    private class OrderDiffUtil : DiffUtil.ItemCallback<Order>() {
        override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
            return oldItem == newItem
        }
    }
}