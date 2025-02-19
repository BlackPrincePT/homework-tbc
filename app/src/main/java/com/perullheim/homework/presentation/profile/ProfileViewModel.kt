package com.perullheim.homework.presentation.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.perullheim.homework.data.repository.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val authRepositoryImpl: AuthRepositoryImpl
) : ViewModel() {

    private val _shouldLogout = MutableSharedFlow<Boolean>()
    val shouldLogout: SharedFlow<Boolean> = _shouldLogout.asSharedFlow()

    fun logout() {
        viewModelScope.launch {
            authRepositoryImpl.clearUserSession()
            _shouldLogout.emit(true)
        }
    }
}