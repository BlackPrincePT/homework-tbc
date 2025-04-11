package com.perullheim.homework.model

import androidx.annotation.DrawableRes
import com.perullheim.homework.R

data class Furniture(
    val name: String,
    val price: Double,
    val color: FurnitureColor,
    @DrawableRes val imageResId: Int
) {
    companion object {
        val exampleFurniture = listOf(
            Furniture(
                name = "High Chair",
                price = 60.0,
                color = FurnitureColor.BLUE_GREY,
                imageResId = R.drawable.high_chair
            ),
            Furniture(
                name = "Couch",
                price = 120.0,
                color = FurnitureColor.BLACK,
                imageResId = R.drawable.black_couch
            ),
            Furniture(
                name = "Couch",
                price = 150.0,
                color = FurnitureColor.WHITE,
                imageResId = R.drawable.white_couch
            ),
            Furniture(
                name = "Dining Table Set",
                price = 450.0,
                color = FurnitureColor.BROWN,
                imageResId = R.drawable.dining_table_set
            ),
            Furniture(
                name = "Classic Chair",
                price = 90.0,
                color = FurnitureColor.BROWN,
                imageResId = R.drawable.classic_chair
            )
        )
    }
}