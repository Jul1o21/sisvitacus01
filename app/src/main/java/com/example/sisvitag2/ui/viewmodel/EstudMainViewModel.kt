package com.example.sisvitag2.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.response.Test
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import com.example.sisvita_cus1.domain.TestUseCase

class EstudMainViewModel: ViewModel()  {
    private val testUseCase = TestUseCase()

    private val _tests = mutableStateOf<List<Test>>(emptyList())
    val tests: State<List<Test>> = _tests

    init {
        fetchTests()
    }

    private fun fetchTests() {
        println("SE obtienen los test")
        viewModelScope.launch {
            try {
                val response = testUseCase.getTodosTests()
                if (response.success) {
                    _tests.value = response.data
                    println("Los test obtenidos son: $_tests.value")
                }
            } catch (e: Exception) {
                // Manejar el error
            }
        }
    }
}