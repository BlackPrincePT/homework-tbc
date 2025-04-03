package com.perullheim.homework.domain.model.pagination

data class Pagination(
    val currentPage: Int,
    val totalPages: Int,
) {

    companion object {
        val EMPTY = Pagination(currentPage = 1, totalPages = 1)
    }
}