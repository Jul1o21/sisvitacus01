package com.example.domain

import com.example.data.model.response.TestsResultResponse
import com.example.sisvita_cus1.data.repository.TestRepository


//Caso de uso 3.1
class EvaluarResultadosTestUseCase {
    private val testRepository = TestRepository()

    //Obetener los datos y preguntas de un solo test por medio de su ID
    suspend fun getTestResueltos(): TestsResultResponse {
        return testRepository.getTodosTestsResultados()
    }
}
