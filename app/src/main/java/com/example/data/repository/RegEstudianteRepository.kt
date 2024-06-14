package com.example.sisvita_cus1.data.repository

import com.example.sisvita_cus1.data.model.Estudiante
import com.example.sisvita_cus1.network.ApiInstance

class RegEstudianteRepository {

    private val apiService = ApiInstance.apiInstance

    suspend fun registrarEstudiante(estudiante: Estudiante): Estudiante {
        return apiService.registrarEstudiante(estudiante)
    }
}