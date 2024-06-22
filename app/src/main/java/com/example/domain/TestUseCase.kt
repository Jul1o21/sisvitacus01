package com.example.sisvita_cus1.domain

import com.example.data.model.request.LoginRequest
import com.example.data.model.request.TestRequest
import com.example.data.model.response.LoginResponse
import com.example.data.model.response.TestListResponse
import com.example.data.model.response.TestResponse
import com.example.sisvita_cus1.data.repository.TestRepository

class TestUseCase {
    private val repository = TestRepository()

    suspend fun getTodosTests(): TestListResponse {
        return repository.getTodosTests()
    }

    suspend fun getTest(testRequest: TestRequest): TestResponse {
        return repository.getTest(testRequest)
    }

}
