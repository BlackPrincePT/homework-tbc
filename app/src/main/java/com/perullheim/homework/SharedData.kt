package com.perullheim.homework

import com.perullheim.homework.model.Category
import com.perullheim.homework.model.Item

object SharedData {
    val categories = mutableListOf(
        Category.ALL,
        Category.PARTY,
        Category.CAMPING,
        Category.CASUAL,
        Category.FORMAL,
        Category.SPORT
    )

    val items = listOf(
        Item(R.drawable.outfit_1, "Belt suit blazer", 120, mutableListOf(Category.PARTY, Category.CASUAL)),
        Item(R.drawable.outfit_2, "Belt suit blazer", 120, mutableListOf(Category.CASUAL, Category.SPORT)),
        Item(R.drawable.outfit_3, "Belt suit blazer", 120, mutableListOf(Category.CASUAL)),
        Item(R.drawable.outfit_4, "Belt suit blazer", 120, mutableListOf(Category.CASUAL, Category.SPORT)),
        Item(R.drawable.outfit_5, "Belt suit blazer", 120, mutableListOf(Category.CASUAL, Category.SPORT)),
        Item(R.drawable.outfit_6, "Belt suit blazer", 120, mutableListOf(Category.PARTY, Category.FORMAL)),
        Item(R.drawable.outfit_7, "Belt suit blazer", 120, mutableListOf(Category.FORMAL)),
        Item(R.drawable.outfit_8, "Belt suit blazer", 120, mutableListOf(Category.PARTY))
    )
}