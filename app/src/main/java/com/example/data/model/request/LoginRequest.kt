package com.example.data.model.request

/*
* Es la estructura json que se deve enviar o solicitar (request)
* para realizar el Login
* */
data class LoginRequest(
    val email: String,
    val contra: String
)