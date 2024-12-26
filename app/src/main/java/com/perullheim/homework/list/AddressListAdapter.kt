package com.perullheim.homework.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.perullheim.homework.R
import com.perullheim.homework.data.Address
import com.perullheim.homework.databinding.AddressViewHolderBinding

class AddressDiffUtil : DiffUtil.ItemCallback<Address>() {
    override fun areItemsTheSame(oldItem: Address, newItem: Address): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Address, newItem: Address): Boolean {
        return oldItem == newItem
    }
}

class AddressListAdapter(private val listener: Listener) :
    ListAdapter<Address, AddressListAdapter.AddressViewHolder>(AddressDiffUtil()) {

    interface Listener {
        var selectedAddress: Address?
        fun editAddressClicked(address: Address)
        fun onLongPress(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AddressViewHolder {
        return AddressViewHolder(
            AddressViewHolderBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: AddressViewHolder, position: Int) {
        holder.onBind()
    }

    inner class AddressViewHolder(private val binding: AddressViewHolderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            val address = getItem(adapterPosition)

            with(binding) {
                tvName.text = address.label
                tvAddress.text = itemView.context.getString(
                    R.string.address_template,
                    address.street,
                    address.city
                )

                ivCheckBox.setOnClickListener {
                    if (listener.selectedAddress != address) {
                        select(address)
                    } else {
                        listener.selectedAddress = null
                    }
                }

                tvEdit.setOnClickListener {
                    listener.editAddressClicked(address)
                }

                root.setOnLongClickListener {
                    listener.onLongPress(adapterPosition)
                    true
                }
            }


        }

        private fun select(address: Address) {
            listener.selectedAddress = address

            with(binding) {
                ivCheckBox.setImageResource(R.drawable.icon_checked)
                tvEdit.visibility = View.VISIBLE
            }
        }

        fun unselect() {
            with(binding) {
                ivCheckBox.setImageResource(R.drawable.icon_unchecked)
                tvEdit.visibility = View.GONE
            }
        }
    }
}