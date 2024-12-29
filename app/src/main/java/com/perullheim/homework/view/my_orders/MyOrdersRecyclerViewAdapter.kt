package com.perullheim.homework.view.my_orders

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.perullheim.homework.R
import com.perullheim.homework.databinding.MyOrderViewHolderBinding
import com.perullheim.homework.model.Order
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

private class MyOrdersDiffUtil : DiffUtil.ItemCallback<Order>() {
    override fun areItemsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Order, newItem: Order): Boolean {
        return oldItem == newItem
    }
}

class MyOrdersRecyclerViewAdapter(private val listener: Listener) :
    ListAdapter<Order, MyOrdersRecyclerViewAdapter.MyOrderViewHolder>(MyOrdersDiffUtil()) {

        interface Listener {
            fun detailsClicked(order: Order)
        }

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

    inner class MyOrderViewHolder(private val binding: MyOrderViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            val context = itemView.context

            getItem(adapterPosition).apply {
                with(binding) {
                    tvOrderNumber.text = context.getString(R.string.order_number, orderNumber)
                    tvTrackingNumber.text = trackingNumber

                    tvQuantity.text = orderItems.size.toString()
                    tvPriceAmount.text =
                        context.getString(R.string.price_template, orderItems.sumOf { it.price })

                    tvOrderStatus.text = status.name
                    tvOrderStatus.setTextColor(context.getColor(status.colorResId))

                    val date = Date(lastUpdatedTimestamp)
                    val formatInfo = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                    tvLastUpdatedTimestamp.text = formatInfo.format(date)

                    btnDetails.setOnClickListener {
                        listener.detailsClicked(this@apply)
                    }
                }
            }
        }
    }
}