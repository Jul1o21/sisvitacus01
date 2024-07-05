package com.example.sisvita_cus1.data.repository

import com.example.data.model.request.RegTestRequest
import com.example.data.model.request.TestRequest
import com.example.data.model.response.GeneralResponse
import com.example.data.model.response.TestsAllResponse
import com.example.data.model.response.TestSingleResponse
import com.example.data.model.response.TestsEvaluableResponse
import com.example.data.model.response.TestsResultResponse
import com.example.sisvita_cus1.network.ApiInstance

class TestRepository {
    private val apiService = ApiInstance.apiInstance

    // Obtener todos los test de la BD
    suspend fun getTodosTests(): TestsAllResponse {
        return apiService.getTests()
    }

    // Obtener las preguntas de un solo test con su id
    suspend fun getTest(testRequest: TestRequest): TestSingleResponse {
        return apiService.getTest(testRequest)
    }

    //Registrar el test dado por el estudiante en la BD
    suspend fun regTest(regTestRequest: RegTestRequest): GeneralResponse {
        return apiService.registrarTest(regTestRequest)
    }

    suspend fun getTodosTestsResultados(): TestsResultResponse {
        return apiService.getTodosTests()
    }

    suspend fun getTestsEvaluables(): TestsEvaluableResponse {
        return apiService.obtenerTestEvaluables()
    }
}
