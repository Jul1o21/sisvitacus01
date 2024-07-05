package com.example.data.model.android

data class Especialista(
    val id_pers: Int,
    val id_usu: Int,
    val materno: String,
    val nombre_completo: String,
    val paterno: String,
    val rol: String
)
{
    companion object {
        fun defaultEspecialista(): Especialista {
            return Especialista(
                id_pers = 20000,
                id_usu= 100,
                materno = "Lopez",
                nombre_completo = "Ana Maria Lopez",
                paterno = "Lopez",
                rol = "Doctor"
            )
        }
    }
}