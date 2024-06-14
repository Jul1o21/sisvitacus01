package com.example.data.model


data class TestResponse(
    val testId: Int,
    val userId: Int,
    val answers: List<Answer>
)