package com.example.data.model.response

data class TestsResultResponse(
    val data: List<TestResult>,
    val message: String,
    val success: Boolean
)

data class TestResult(
    val desc_test: String,
    val id_test_res: Int,
    val puntaje_total: Int,
    val tipo_test: String,
)
