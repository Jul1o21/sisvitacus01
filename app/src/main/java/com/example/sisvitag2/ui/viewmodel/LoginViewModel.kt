package com.example.sisvitag2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class LoginViewModel : ViewModel() {


    private val _correoState = mutableStateOf("")
    val correoState: State<String> = _correoState

    private val _contraseniadState = mutableStateOf("")
    val contraseniaState: State<String> = _contraseniadState

    private val _rememberMeState = mutableStateOf(false)
    val rememberMeState: State<Boolean> = _rememberMeState

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun setEmail(email: String) {
        _correoState.value = email
    }

    fun setPassword(password: String) {
        _contraseniadState.value = password
    }

    fun setRememberMe(checked: Boolean) {
        _rememberMeState.value = checked
    }

    fun submitLoginForm() {

    }

}