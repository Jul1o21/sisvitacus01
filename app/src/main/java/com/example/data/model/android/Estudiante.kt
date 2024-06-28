package com.example.data.model.android

data class Estudiante(
    val id_estudiante: Int,
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
    val departamento: String,
    val distrito: String,
    val universidad: String,
    val tipo_usuario: String = "estudiante"
)
{
    companion object {
        fun defaultEstudiante(): Estudiante {
            return Estudiante(
                id_estudiante = 20000,
                id_usuario = 10000,
                nombre_completo = "Juan Perez Gomez",
                correo = "juan_perez@example.com",
                contrasenia = "juan_perez123",
                numero_documento = 12345678,
                tipo_documento = "DNI",
                sexo = "M",
                edad = 30,
                numero_celular = 987654321,
                pais = "Peru",
                departamento = "Lima",
                distrito = "Cercado de Lima",
                universidad = "UNMSM"
            )
        }
    }


}
