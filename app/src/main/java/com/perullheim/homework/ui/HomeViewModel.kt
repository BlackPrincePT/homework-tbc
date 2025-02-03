package com.perullheim.homework.ui

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.perullheim.homework.data.DatabaseManager
import com.perullheim.homework.data.UserRepository
import com.perullheim.homework.service.HomeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class HomeViewModel private constructor(
    private val userRepository: UserRepository,
    val isConnected: Boolean
) : ViewModel() {

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean>
        get() = _isLoading

    val users = userRepository.readAllData()

    private fun fetchUsersRemote() {
        viewModelScope.launch(Dispatchers.IO) {

            if (users.first().isEmpty() && isConnected) {
                Log.d("Petre", isConnected.toString())
                _isLoading.emit(value = true)
                userRepository.fetchUsersFromRemote()
            }

            _isLoading.emit(value = false)
        }
    }

    init {
        fetchUsersRemote()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = checkNotNull(this[APPLICATION_KEY])
                val userDao = DatabaseManager.getInstance(application).userDao()
                val userRepository = UserRepository(userDao, HomeService.INSTANCE)

                val connectivityManager =
                    application.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                val capabilities =
                    connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
                val isConnected =
                    capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)

                HomeViewModel(userRepository, isConnected)
            }
        }
    }
}