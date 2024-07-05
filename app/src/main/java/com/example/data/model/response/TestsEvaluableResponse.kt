package com.example.data.model.response

data class TestsEvaluableResponse(
    val data: TestData,
    val message: String,
    val success: Boolean
)

data class TestData(
    val tests: List<TestEvaluable>
)

data class TestEvaluable(
    val descripcion: String,
    val estudiante: String,
    val fecha: String,
    val id_test_res: Int,
    val nivel: String,
    val puntaje_total: Int,
    val recomend: String,
    val resultado: String,
    val tipo: String
)
