package com.example.sisvita_cus1.data.model

data class Test(
    val id: Int,
    val title: String,
    val questions: List<Question>
)

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>
)

data class TestResponse(
    val testId: Int,
    val userId: Int,
    val answers: List<Answer>
)

data class Answer(
    val questionId: Int,
    val selectedOption: String
)
