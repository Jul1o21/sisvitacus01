package com.example.data.model.response

data class TestsAllResponse(
    val data: List<TestResponse>,
    val message: String,
    val success: Boolean
)

data class TestResponse(
    val descripcion: String,
    val id_test: Int,
    val recomendacion: String,
    val tipo: String,
)
