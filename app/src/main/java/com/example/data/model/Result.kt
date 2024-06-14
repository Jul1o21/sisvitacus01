package com.example.sisvita_cus1.data.model

data class Result<T>(
    val success: Boolean,
    val data: T?,
    val message: String?
)
