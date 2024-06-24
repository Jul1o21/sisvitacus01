package com.example.domain

import com.example.data.model.request.RegTestRequest
import com.example.data.model.response.RespondeGen
import com.example.data.repository.RegTestRepository

class RegTestUseCase {

    private val repository = RegTestRepository()

    suspend fun regTest(regTestRequest: RegTestRequest): RespondeGen {
        return repository.regTest(regTestRequest)
    }

}