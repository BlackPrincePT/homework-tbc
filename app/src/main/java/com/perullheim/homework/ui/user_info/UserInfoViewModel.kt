package com.perullheim.homework.ui.user_info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.perullheim.homework.datastore.UserPreferencesRepository
import com.perullheim.homework.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserInfoViewModel(userPreferencesRepository: UserPreferencesRepository) : ViewModel() {

    val savedUser: Flow<User?> = userPreferencesRepository.userPreferencesFlow
        .map {
            User(
                firstName = it.firstName,
                lastName = it.lastName,
                email = it.email
            )
        }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val userPreferencesRepository = UserPreferencesRepository.getInstance(application)
                UserInfoViewModel(userPreferencesRepository)
            }
        }
    }
}