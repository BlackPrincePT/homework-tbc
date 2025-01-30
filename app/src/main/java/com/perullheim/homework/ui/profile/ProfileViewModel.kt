package com.perullheim.homework.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.perullheim.homework.datastore.UserPreferencesRepository
import com.perullheim.homework.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val userPreferencesRepository: UserPreferencesRepository) : ViewModel() {

    fun updateUser(user: User) {
        viewModelScope.launch(Dispatchers.IO) {
            userPreferencesRepository.updateUser(user)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val userPreferencesRepository = UserPreferencesRepository.getInstance(application)
                ProfileViewModel(userPreferencesRepository)
            }
        }
    }
}