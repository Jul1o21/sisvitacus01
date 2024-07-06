package com.example.domain

import com.example.data.model.response.TestsEvaluableResponse
import com.example.data.model.response.UbigeosEstudResponse
import com.example.data.repository.EstudianteRepository
import com.example.sisvita_cus1.data.repository.TestRepository

//Caso de uso 3.2
class VisualizarMapaCalorUseCase {

    private val testRepository = TestRepository()

    suspend fun getTestsEvaluables(): TestsEvaluableResponse {
        return testRepository.getTestsEvaluables()
    }

    private val estudianteRepository = EstudianteRepository()

    suspend fun obtenerUbigeosEst(): UbigeosEstudResponse {
        return estudianteRepository.obtenerUbigeosEst()
    }

}