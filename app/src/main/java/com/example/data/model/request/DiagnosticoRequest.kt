package com.example.data.model.request

data class DiagnosticoRequest(
    val id_usu: Int,
    val id_test_res: Int,
    val descripcion: String,
    val resultado: String,
    val recomendacion: String,
    val tratamiento: String,
    val notas: String
)