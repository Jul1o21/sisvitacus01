package com.example.data.model

data class Question(
    val id: Int,
    val text: String,
    val options: List<String>,
    var selectedOption: Int = -1 // Valor inicial que indica ninguna opción seleccionada
)
