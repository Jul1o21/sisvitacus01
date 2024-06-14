package com.example.data.model

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>
)