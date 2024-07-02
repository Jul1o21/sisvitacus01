package com.example.data.model.response

data class registerResponse(
    val message: String,
    val success: Boolean,
    val data: registerDataResponse?
)

data class registerDataResponse(
    val usuario: UsuariorResponse
)

data class UsuariorResponse(
    val tipo: Int,
    val contrasenia: String,
    val correo: String,
    val nombre: String,
    val aPaterno: String,
    val aMaterno: String,
)