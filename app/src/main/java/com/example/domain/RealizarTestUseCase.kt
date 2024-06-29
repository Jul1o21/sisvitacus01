package com.example.domain

import com.example.data.model.request.RegTestRequest
import com.example.data.model.request.TestRequest
import com.example.data.model.response.GeneralResponse
import com.example.data.model.response.TestSingleResponse
import com.example.data.model.response.TestsAllResponse
import com.example.data.model.response.TestsResult
import com.example.sisvita_cus1.data.repository.TestRepository

//Representa el CUS 2.0 "Realizar Test"
class RealizarTestUseCase {

    private val testRepository = TestRepository()

    //Registrar el test dado por el estudiante en la BD
    suspend fun regTest(regTestRequest: RegTestRequest): GeneralResponse {
        return testRepository.regTest(regTestRequest)
    }

    //Obetener todos los tests que existen en el sistema
    suspend fun getTodosTests(): TestsAllResponse {
        return testRepository.getTodosTests()
    }

    //Obetener los datos y preguntas de un solo test por medio de su ID
    suspend fun getTest(testRequest: TestRequest): TestSingleResponse {
        return testRepository.getTest(testRequest)
    }

    suspend fun getTodosTestsResultados(): TestsResult {
        return testRepository.getTodosTestsResultados()
    }

}