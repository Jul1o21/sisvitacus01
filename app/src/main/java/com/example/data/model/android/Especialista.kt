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
{
    companion object {
        fun defaultEspecialista(): Especialista {
            return Especialista(
                id_especialista = 100, // Ajusta el ID según tus necesidades
                id_usuario = 200, // Ajusta el ID según tus necesidades
                nombre_completo = "Ana Maria Lopez",
                correo = "ana_lopez@example.com",
                contrasenia = "ana_lopez123",
                numero_documento = 45678912,
                tipo_documento = "DNI",
                sexo = "F",
                edad = 35,
                numero_celular = 987654321,
                pais = "Peru"
            )
        }
    }
}