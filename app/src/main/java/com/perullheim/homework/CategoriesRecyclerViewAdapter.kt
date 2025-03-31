package com.perullheim.homework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.perullheim.homework.SharedData.categories
import com.perullheim.homework.SharedData.items
import com.perullheim.homework.databinding.ViewHolderCategoryBinding
import com.perullheim.homework.model.Category

class CategoriesRecyclerViewAdapter :
    RecyclerView.Adapter<CategoriesRecyclerViewAdapter.CategoryViewHolder>() {

    lateinit var onSelectionChanged: (selectedCategory: Category) -> Unit

    var selectedPosition = 0
        set(value) {
            field = value

            val selectedCategory = categories[value]
            onSelectionChanged(selectedCategory)
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            ViewHolderCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.onBind(position == selectedPosition)
    }

    fun checkFavoriteCategory() {
        val favoriteIndex = 1

        if (Category.FAVOURITE !in categories && items.any { Category.FAVOURITE in it.category }) {
            categories.add(favoriteIndex, Category.FAVOURITE)
            if (selectedPosition != 0) selectedPosition++
            notifyItemInserted(favoriteIndex)
        } else if (Category.FAVOURITE in categories && items.none { Category.FAVOURITE in it.category }) {
            categories.removeAt(favoriteIndex)
            selectedPosition = 0
            notifyItemRemoved(favoriteIndex)
        }
    }

    inner class CategoryViewHolder(private val binding: ViewHolderCategoryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(isSelected: Boolean) {
            with(binding) {
                tvTitle.text = categories[adapterPosition].displayName

                selection(isSelected)

                itemView.isSelected = isSelected

                itemView.setOnClickListener {
                    notifyItemChanged(selectedPosition)
                    selectedPosition = adapterPosition
                    notifyItemChanged(selectedPosition)
                }
            }
        }

        private fun selection(isSelected: Boolean) {
            with(binding) {
                if (isSelected) {
                    tvTitle.background = ContextCompat.getDrawable(
                        itemView.context, R.drawable.background_category_selected
                    )
                    tvTitle.setTextColor(
                        ContextCompat.getColor(itemView.context, R.color.category_selected_text)
                    )
                } else {
                    tvTitle.background = ContextCompat.getDrawable(
                        itemView.context, R.drawable.background_category_unselected
                    )
                    tvTitle.setTextColor(
                        ContextCompat.getColor(itemView.context, R.color.category_unselected_text)
                    )
                }
            }
        }
    }
}