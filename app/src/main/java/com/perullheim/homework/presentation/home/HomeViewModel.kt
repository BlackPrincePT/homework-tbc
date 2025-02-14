package com.perullheim.homework.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val pagingSource: HomePagingSource
) : ViewModel() {

    val pageData = Pager(
        PagingConfig(pageSize = 6),
    ) { pagingSource }
        .flow
        .cachedIn(viewModelScope)
}