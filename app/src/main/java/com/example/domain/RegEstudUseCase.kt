package com.example.sisvita_cus1.domain


import com.example.sisvita_cus1.data.model.Estudiante
import com.example.sisvita_cus1.data.repository.RegEstudRepository

class RegEstudUseCase {

    private val repository = RegEstudRepository()

    suspend fun registrarEstudiante(estudiante: Estudiante): Estudiante {
        return repository.registrarEstudiante(estudiante)
    }
}