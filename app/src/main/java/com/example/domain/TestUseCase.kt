package com.example.sisvita_cus1.domain

import com.example.sisvita_cus1.data.model.Test
import com.example.sisvita_cus1.data.model.TestResponse
import com.example.sisvita_cus1.data.model.Result
import com.example.sisvita_cus1.data.repository.TestRepository

class TestUseCase {
    private val repository = TestRepository()

    suspend fun getTests(): List<Test> {
        return repository.getTests()
    }

    suspend fun submitTest(testResponse: TestResponse): Result<TestResponse> {
        return repository.submitTest(testResponse)
    }
}