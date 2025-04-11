package com.perullheim.homework.model

import androidx.annotation.ColorRes
import com.perullheim.homework.R

enum class FurnitureColor(val displayName: String, @ColorRes val colorResId: Int) {
    BLACK("Black", R.color.black),
    WHITE("White", R.color.brand_white),
    BROWN("Brown", R.color.brown),
    BLUE_GREY("Blue Grey", R.color.blue_grey)
}