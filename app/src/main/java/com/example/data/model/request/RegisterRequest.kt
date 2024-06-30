package com.example.data.model.request

data class RegisterRequest (
    val tipo_usuario: String,
    val nombre: String,
    val aPaterno: String,
    val aMaterno: String,
    val correo: String,
    val contrasenia: String
)
