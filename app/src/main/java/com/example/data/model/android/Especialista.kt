package com.example.data.model.android

data class Especialista(
    val id_especialista: Int,
    val id_usuario: Int,
    val nombre_completo: String,
    val correo: String,
    val contrasenia: String,
    val numero_documento: Int,
    val tipo_documento: String,
    val sexo: String,
    val edad: Int,
    val numero_celular: Int,
    val pais: String,
    val tipo_usuario: String = "especialista"
)