package com.example.data.model.response

/*
* Es la data que devuelve el json del Login, es parte de la clase LoginResponse
* en este caso, la data esta representada por la clase UsuarioResponse
* */
data class LoginDataResponse(
    val usuario: UsuarioResponse
)