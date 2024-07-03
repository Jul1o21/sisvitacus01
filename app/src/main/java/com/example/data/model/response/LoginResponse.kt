package com.example.data.model.response

/*
Esta es toda la estructura json que se recibe, tiene el mensaje,
el success del json y la data
que representa los datos de interes del json

*/
data class LoginResponse(
    val message: String,
    val success: Boolean,
    val data: LoginDataResponse?
)

data class LoginDataResponse(
    val usuario: UsuarioResponse
)

data class UsuarioResponse(
    val id_pers: Int?,
    val id_usu: Int?,
    val materno: String,
    val nombres: String,
    val paterno: String,
    val rol: String
)
