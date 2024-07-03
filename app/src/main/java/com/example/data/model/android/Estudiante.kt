package com.example.data.model.android

data class Estudiante(
    val id_pers: Int,
    val id_estudiante: Int,
    val materno: String,
    val nombre_completo: String,
    val paterno: String,
    val rol: String
)
{
    companion object {
        fun defaultEstudiante(): Estudiante {
            return Estudiante(
                id_pers = 20000,
                id_estudiante = 10000,
                materno = "Perez",
                nombre_completo = "Juan",
                paterno = "Gomez",
                rol = "Estudiante"
            )
        }
    }


}
