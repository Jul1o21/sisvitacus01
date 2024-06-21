package com.example.data.repository

import com.example.data.model.request.LoginRequest
import com.example.data.model.response.LoginResponse
import com.example.sisvita_cus1.network.ApiInstance

class LoginRepository {
    private val apiService = ApiInstance.apiInstance

    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return apiService.login(loginRequest)
    }
}