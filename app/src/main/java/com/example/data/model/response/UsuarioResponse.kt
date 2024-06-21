package com.example.data.model.response

/*
* Es la data de interes que representa al usuario que hace login
* en la app, combina los atributos posibles tanto de una especialista como de un
* estudiante.
* */
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