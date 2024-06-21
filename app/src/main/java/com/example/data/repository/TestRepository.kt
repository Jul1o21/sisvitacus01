package com.example.sisvita_cus1.data.repository

import com.example.data.model.TestResponse
import com.example.data.model.response.TestListResponse
import com.example.sisvita_cus1.data.model.*
import com.example.sisvita_cus1.network.ApiInstance

class TestRepository {
    private val apiService = ApiInstance.apiInstance

    suspend fun getTests(): TestListResponse {
        return apiService.getTests()
    }

}
