package com.example.data.model.response

data class TestsResult(
    val data: List<TestResponseResult>,
    val message: String,
    val success: Boolean
)

data class TestResponseResult(
    val desc_test: String,
    val id_test_res: Int,
    val puntaje_total: Int,
    val tipo_test: String
)