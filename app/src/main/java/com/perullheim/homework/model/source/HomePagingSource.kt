package com.perullheim.homework.model.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.perullheim.homework.model.data.UserDto
import com.perullheim.homework.model.service.home.HomeService

class HomePagingSource private constructor(private val homeService: HomeService) :
    PagingSource<Int, UserDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserDto> {
        return try {
            val position = params.key ?: 1
            val response = homeService.fetchUsers(position)
            val body = response.body()

            if (response.isSuccessful && body != null) {
                LoadResult.Page(
                    data = body.data,
                    prevKey = if (position == 1) null else (position - 1),
                    nextKey = if (position == body.totalPages) null else (position + 1)
                )
            } else {
                throw Exception("No Response")
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, UserDto>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    companion object {
        val instance = HomePagingSource(HomeService.instance)
    }
}