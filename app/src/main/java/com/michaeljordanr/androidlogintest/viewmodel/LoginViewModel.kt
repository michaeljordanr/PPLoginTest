package com.michaeljordanr.androidlogintest.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class LoginViewModel : ViewModel() {

    var isLogged = MutableStateFlow(false)

    suspend fun login(login: String, password: String): Boolean {
        if (login.isNotEmpty() && password.isNotEmpty()) {
            isLogged.emit(login == "login" && password == "pass")
        }
        return false
    }

}