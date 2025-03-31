package com.perullheim.homework

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewItemDecoration(private val spacing: Int, private val marginStart: Int = spacing) :
    RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)

        if (parent.adapter is CategoriesRecyclerViewAdapter) {
            outRect.right = spacing
            outRect.left = if (position == 0) marginStart else 0

        } else if (parent.adapter is ItemsRecyclerViewAdapter) {
            outRect.right = if (position % 2 == 0) spacing / 2 else spacing
            outRect.left = if (position % 2 == 0) spacing else spacing / 2

            outRect.top = if (position == 0 || position == 1) marginStart else 0
            outRect.bottom = spacing
        }
    }
}