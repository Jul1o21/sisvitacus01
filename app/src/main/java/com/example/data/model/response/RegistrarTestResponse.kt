package com.example.data.model.response

data class RegistrarTestResponse(
    val data: DiagnosticoAutomResponse,
    val message: String,
    val success: Boolean
)

data class DiagnosticoAutomResponse(
    val diagnostico_automatico: DiagnosticoAutomatico
)

data class DiagnosticoAutomatico(
    val descripcion: String,
    val id_diag: Int,
    val puntaje_total: Int,
    val rango: String,
    val recomendacion: String,
    val resultado: String,
)