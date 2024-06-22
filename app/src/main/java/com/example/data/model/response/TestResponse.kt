package com.example.data.model.response

data class TestResponse(
    val data: TestData,
    val message: String,
    val success: Boolean
)

data class TestData(
    val preguntas: List<Pregunta>,
    val test: Test
)

data class Pregunta(
    val descripcion: String,
    val id_preg: Int,
    val puntaje_maximo: Int,
    val puntaje_minimo: Int
)