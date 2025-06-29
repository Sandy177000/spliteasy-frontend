package com.example.spliteasy.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spliteasy.data.model.LoginResponseBody
import com.example.spliteasy.data.repository.AuthRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(val repository: AuthRepositoryImpl): ViewModel() {

    private val _user = MutableLiveData<LoginResponseBody?>()
    // made this private to avoid accessing this state directly by others outside this class
    val user: LiveData<LoginResponseBody?> = _user
    // this read only value of type LiveData needs to be used else where outside this class

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val responseBody = repository.login(email, password)
                _user.value = responseBody
                _error.value = null
            } catch (e: Exception) {
                _error.value = "Login error: ${e.message}"
                _user.value = null
            }
        }
    }
}