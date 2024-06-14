package com.example.sisvitag2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.viewModelScope
import com.example.data.model.LoginRequest
import com.example.data.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val repository = LoginRepository()

    private val _correoState = mutableStateOf("")
    val correoState: State<String> = _correoState

    private val _contraseniaState = mutableStateOf("")
    val contraseniaState: State<String> = _contraseniaState

    private val _rememberMeState = mutableStateOf(false)
    val rememberMeState: State<Boolean> = _rememberMeState

    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _loginSuccess = mutableStateOf(false)
    val loginSuccess: State<Boolean> = _loginSuccess

    fun setEmail(email: String) {
        _correoState.value = email
    }

    fun setPassword(password: String) {
        _contraseniaState.value = password
    }

    fun setRememberMe(checked: Boolean) {
        _rememberMeState.value = checked
    }

    fun submitLoginForm() {

    }

    fun login() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val loginRequest = LoginRequest(_correoState.value, _contraseniaState.value)
                val response = repository.login(loginRequest)
                if (response.success && response.data != null) {
                    _loginSuccess.value = true
                } else {
                    _isError.value = true
                }
                _isLoading.value = false
            } catch (e: Exception) {
                _isError.value = true
                _isLoading.value = false
            }
        }
    }

}