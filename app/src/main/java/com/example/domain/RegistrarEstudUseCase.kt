package com.example.sisvita_cus1.domain

import com.example.data.model.request.LoginRequest
import com.example.data.model.request.RegisterRequest
import com.example.data.model.response.GeneralResponse
import com.example.data.model.response.registerResponse
import com.example.data.repository.EstudianteRepository

//Representa el CUS 13.0 "Registrar Estudiante"
class RegistrarEstudUseCase {

    private val estudianteRepository = EstudianteRepository()

    suspend fun registrar(RegisterRequest: RegisterRequest): registerResponse {
        return estudianteRepository.registrarEstudiante(RegisterRequest)
    }


}