package com.example.sisvita_cus1.data.repository

import com.example.data.model.request.TestRequest
import com.example.data.model.response.TestListResponse
import com.example.data.model.response.TestResponse
import com.example.sisvita_cus1.network.ApiInstance

class TestRepository {
    private val apiService = ApiInstance.apiInstance

    // Obetener todos los test de la BD
    suspend fun getTodosTests(): TestListResponse {
        return apiService.getTests()
    }

    suspend fun getTest(testRequest: TestRequest): TestResponse {
        return apiService.getTest(testRequest)
    }
    // Obetener las preguntas de un test con su id

}
