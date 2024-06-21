package com.example.data.model

data class Cita(
    val id_usuario: Int,
    val fecha_cita: String,
    val observaciones: String?,
    val tratamiento: String?,
    val estado: String
)