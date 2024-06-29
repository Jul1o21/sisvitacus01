package com.example.data.model.response

data class LoginResponse(
    val message: String,
    val success: Boolean,
    val data: LoginDataResponse?
)

data class LoginDataResponse(
    val usuario: UsuarioResponse
)

data class UsuarioResponse(
    val contrasenia: String,
    val correo: String,
    val departamento: String?,
    val distrito: String?,
    val edad: Int,
    val id_estudiante: Int?,
    val id_especialista: Int?,
    val id_usuario: Int,
    val nombre_completo: String,
    val numero_celular: Int,
    val numero_documento: Int,
    val pais: String,
    val sexo: String,
    val tipo_documento: String,
    val tipo_usuario: String,
    val universidad: String?
)
