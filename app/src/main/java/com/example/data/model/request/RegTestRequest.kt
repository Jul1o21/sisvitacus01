package com.example.data.model.request

data class RegTestRequest (
    val id_est: Int,
    val preguntas: List<PregRespuestaRequest>
)

data class PregRespuestaRequest (
    val id_preg: Int,
    val puntaje: Int
)