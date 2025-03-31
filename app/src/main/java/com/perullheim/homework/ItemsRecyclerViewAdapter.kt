package com.perullheim.homework

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.perullheim.homework.SharedData.items
import com.perullheim.homework.databinding.ViewHolderItemBinding
import com.perullheim.homework.model.Category
import com.perullheim.homework.model.Item

class ItemsRecyclerViewAdapter() :
    RecyclerView.Adapter<ItemsRecyclerViewAdapter.ItemViewHolder>() {

    lateinit var onFavoriteAdded: () -> Unit

    var currentFilter: Category = Category.ALL
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    val filteredItems: List<Item>
        get() = if (currentFilter != Category.ALL) items.filter { currentFilter in it.category } else items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            ViewHolderItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return filteredItems.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.onBind()
    }

    inner class ItemViewHolder(private val binding: ViewHolderItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind() {
            val model = filteredItems[adapterPosition]
            with(binding) {
                ivModel.setImageResource(model.image)
                tvTitle.text = model.title
                tvPrice.text = itemView.context.getString(R.string.price, model.price)
                setupHeartIcon(model)

                viewFavorite.setOnClickListener {
                    if (Category.FAVOURITE in model.category)
                        items.first { it == model }.category.removeAll { it == Category.FAVOURITE }
                    else
                        items.first { it == model }.category.add(Category.FAVOURITE)

                    setupHeartIcon(model)
                    onFavoriteAdded()
                }
            }
        }

        private fun setupHeartIcon(model: Item) {
            val resID =
                if (Category.FAVOURITE in model.category) R.drawable.icon_heart_filled else R.drawable.icon_heart
            binding.ivHeart.setImageResource(resID)
        }
    }
}