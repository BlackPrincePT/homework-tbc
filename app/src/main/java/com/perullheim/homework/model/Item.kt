package com.perullheim.homework.model

data class Item(val image: Int, val title: String, val price: Int, val category: MutableList<Category>)