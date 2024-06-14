package com.example.data.model

data class LoginResponse(
    val message: String,
    val success: Boolean,
    val data: LoginData?
)