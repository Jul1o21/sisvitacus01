package com.example.data.repository

import com.example.data.model.request.LoginRequest
import com.example.data.model.response.GeneralResponse
import com.example.data.model.response.LoginResponse
import com.example.sisvita_cus1.network.ApiInstance

class UsuarioRepository {
    private val apiService = ApiInstance.apiInstance


    //Verificar si un estudiante o especialista esta registrado en el sistema
    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return apiService.login(loginRequest)
    }


}