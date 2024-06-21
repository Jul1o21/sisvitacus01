package com.example.data.model.response

/*
Esta es toda la estructura json que se recive, tiene el mensaje,
el success del json y la data
que representa los datos de interes del json

*/
data class LoginResponse(
    val message: String,
    val success: Boolean,
    val data: LoginDataResponse?
)