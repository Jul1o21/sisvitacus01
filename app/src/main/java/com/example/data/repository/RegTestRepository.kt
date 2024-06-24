package com.example.data.repository

import com.example.data.model.request.LoginRequest
import com.example.data.model.request.RegTestRequest
import com.example.data.model.response.LoginResponse
import com.example.data.model.response.RespondeGen
import com.example.sisvita_cus1.network.ApiInstance

class RegTestRepository {
    private val apiService = ApiInstance.apiInstance

    suspend fun regTest(regTestRequest: RegTestRequest): RespondeGen {
        return apiService.registrarTest(regTestRequest)
    }
}