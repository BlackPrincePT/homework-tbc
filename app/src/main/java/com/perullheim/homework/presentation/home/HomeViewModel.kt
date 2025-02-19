package com.perullheim.homework.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.perullheim.homework.domain.repository.UsersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(usersRepository: UsersRepository) : ViewModel() {
    val pageData = usersRepository.getUsersPagingData()
}