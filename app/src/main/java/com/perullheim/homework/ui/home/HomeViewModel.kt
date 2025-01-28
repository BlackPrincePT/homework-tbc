package com.perullheim.homework.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.perullheim.homework.model.source.HomePagingSource

class HomeViewModel : ViewModel() {

    val pageData = Pager(
        PagingConfig(pageSize = 6)
    ) { HomePagingSource.instance }
        .flow
        .cachedIn(viewModelScope)

}