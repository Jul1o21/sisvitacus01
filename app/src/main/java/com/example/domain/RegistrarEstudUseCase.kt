package com.example.sisvita_cus1.domain


import com.example.data.model.android.Estudiante
import com.example.data.model.response.GeneralResponse
import com.example.data.repository.EstudianteRepository

//Representa el CUS 13.0 "Registrar Estudiante"
class RegistrarEstudUseCase {

    private val estudianteRepository = EstudianteRepository()

    suspend fun registrarEstudiante(): GeneralResponse {
        return estudianteRepository.registrarEstudiante()
    }


}