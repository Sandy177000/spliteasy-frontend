package com.example.spliteasy.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.spliteasy.data.model.User
import com.example.spliteasy.data.repository.AuthRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch


class LoginViewModel(): ViewModel() {

    private val _user = MutableLiveData<User?>()
    val user: LiveData<User?> = _user

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error

    private val repository: AuthRepository = AuthRepository()


    fun loginWithFirebaseToken() {
        val currentUser = FirebaseAuth.getInstance().currentUser
        currentUser?.getIdToken(true)
            ?.addOnSuccessListener { result ->
                val idToken = result.token
                if (idToken != null) {
                    login(idToken)
                } else {
                    _error.postValue("Token not available")
                }
            }?.addOnFailureListener {
                _error.postValue("Firebase auth failed: ${it.message}")
            }
    }

    private fun login(idToken: String) {
        viewModelScope.launch {
            try {
                val response = repository.login(idToken)
                if (response.isSuccessful) {
                    _user.value = response.body()
                    _error.value = null
                } else {
                    _error.value = "Login failed: ${response.code()}"
                }

            } catch (e: Exception) {
                _error.value = e.message
            }
        }

    }
}