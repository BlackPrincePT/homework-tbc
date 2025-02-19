package com.perullheim.homework.presentation.home

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.perullheim.homework.domain.model.User
import com.perullheim.homework.domain.repository.UsersRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class HomePagingSource @Inject constructor(
    private val usersRepository: UsersRepository
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val position = params.key ?: 1
            val pagination = usersRepository.getMoreUsers(position)

            val users = usersRepository.getUsers()

            LoadResult.Page(
                data = users.first(),
                prevKey = if (position == 1) null else (position - 1),
                nextKey = if (position == pagination.totalPages) null else (position + 1)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, User>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}