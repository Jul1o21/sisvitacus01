package com.example.sisvita_cus1.domain

import com.example.data.model.TestResponse
import com.example.data.model.response.TestListResponse
import com.example.sisvita_cus1.data.model.Result
import com.example.sisvita_cus1.data.repository.TestRepository

class TestUseCase {
    private val repository = TestRepository()

    suspend fun getTests(): TestListResponse {
        return repository.getTests()
    }


}
