package com.perullheim.homework.domain.model.pagination

import com.perullheim.homework.domain.model.User

data class PaginatedUsers(
    val users: List<User>,
    val pagination: Pagination
)