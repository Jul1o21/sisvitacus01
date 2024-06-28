package com.example.domain

import com.example.data.model.request.LoginRequest
import com.example.data.model.response.LoginResponse
import com.example.data.repository.EstudianteRepository
import com.example.data.repository.UsuarioRepository

//Representa el CUS 1.0 "Iniciar Sesion"
class IniciarSesionUseCase {

    private val usuarioRepository = UsuarioRepository()

    //Verificar si un estudiante o especialista esta registrado en el sistema
    suspend fun login(loginRequest: LoginRequest): LoginResponse {
        return usuarioRepository.login(loginRequest)
    }

}