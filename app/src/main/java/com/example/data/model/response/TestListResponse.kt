package com.example.data.model.response

data class TestListResponse(
    val data: List<Test>,
    val message: String,
    val success: Boolean
)