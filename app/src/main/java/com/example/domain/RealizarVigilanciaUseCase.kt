package com.example.domain

import com.example.data.model.response.TestsEvaluableResponse
import com.example.sisvita_cus1.data.repository.TestRepository

class RealizarVigilanciaUseCase {
    private val testRepository = TestRepository()

    suspend fun getTestsEvaluables(): TestsEvaluableResponse {
        return testRepository.getTestsEvaluables()
    }
}
