package com.example.sisvita_cus1.data.repository

import com.example.data.model.response.TestListResponse
import com.example.sisvita_cus1.network.ApiInstance

class TestRepository {
    private val apiService = ApiInstance.apiInstance

    suspend fun getTodosTests(): TestListResponse {
        return apiService.getTests()
    }

}
