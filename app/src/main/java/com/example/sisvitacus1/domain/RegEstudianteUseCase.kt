package com.example.sisvita_cus1.domain


import com.example.sisvita_cus1.data.model.Estudiante
import com.example.sisvita_cus1.data.repository.RegEstudianteRepository

class RegEstudianteUseCase {

    private val repository = RegEstudianteRepository()

    suspend fun registrarEstudiante(estudiante: Estudiante): Estudiante {
        return repository.registrarEstudiante(estudiante)
    }
}