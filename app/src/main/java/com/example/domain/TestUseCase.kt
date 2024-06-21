package com.example.sisvita_cus1.domain

import com.example.data.model.response.TestListResponse
import com.example.sisvita_cus1.data.repository.TestRepository

class TestUseCase {
    private val repository = TestRepository()

    suspend fun getTodosTests(): TestListResponse {
        return repository.getTodosTests()
    }


}
