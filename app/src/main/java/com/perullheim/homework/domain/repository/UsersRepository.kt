package com.perullheim.homework.domain.repository

import androidx.paging.PagingData
import com.perullheim.homework.domain.model.User
import com.perullheim.homework.domain.model.pagination.Pagination
import kotlinx.coroutines.flow.Flow

interface UsersRepository {
    fun getUsersPagingData(): Flow<PagingData<User>>
}