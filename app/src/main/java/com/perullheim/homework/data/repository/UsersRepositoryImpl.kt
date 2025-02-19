package com.perullheim.homework.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.perullheim.homework.data.api.NetworkUtils
import com.perullheim.homework.data.api.UsersService
import com.perullheim.homework.data.api.model.Resource
import com.perullheim.homework.data.cache.DatabaseManager
import com.perullheim.homework.data.cache.model.CachedUser
import com.perullheim.homework.data.mediator.HomeRemoteMediator
import com.perullheim.homework.domain.model.User
import com.perullheim.homework.domain.model.pagination.Pagination
import com.perullheim.homework.domain.repository.UsersRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val api: UsersService,
    private val cache: DatabaseManager,
    private val networkUtils: NetworkUtils
) : UsersRepository {

    @OptIn(ExperimentalPagingApi::class)
    override fun getUsersPagingData(): Flow<PagingData<User>> {
        return Pager(
            config = PagingConfig(
                pageSize = 6,
                enablePlaceholders = false
            ),
            remoteMediator = HomeRemoteMediator(api, cache, networkUtils),
            pagingSourceFactory = { cache.usersDao().pagingSource() }
        ).flow.map { pagingData ->
            pagingData.map(CachedUser::toDomain)
        }
    }
}
