package com.perullheim.homework.presentation.profile

import androidx.lifecycle.ViewModel
import com.perullheim.homework.data.datastore.DataStoreManager
import com.perullheim.homework.domain.repositories.UserSessionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val userSessionRepository: UserSessionRepository
) : ViewModel() {

    val currentUserToken
        get() = userSessionRepository.userToken

    suspend fun logout() {
        userSessionRepository.deleteUserToken()
        DataStoreManager.oneTimeUserToken.value = null
    }
}