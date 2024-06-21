package com.example.domain

import com.example.data.model.request.LoginRequest
import com.example.data.model.response.LoginResponse
import com.example.data.repository.LoginRepository

class LoginUseCase {

    private val repository = LoginRepository()

    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return repository.login(loginRequest)
    }
}