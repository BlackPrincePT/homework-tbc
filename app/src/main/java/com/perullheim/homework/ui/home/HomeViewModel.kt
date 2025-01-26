package com.perullheim.homework.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.model.service.home.HomeService
import com.perullheim.homework.model.service.home.UserResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val _pageData = MutableStateFlow<UserResponse?>(null)
    val pageData: StateFlow<UserResponse?> get() = _pageData

    fun getUsers(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = HomeService.instance.fetchUsers(page)

            if (response.isSuccessful)
                _pageData.emit(response.body()!!)
        }
    }
}