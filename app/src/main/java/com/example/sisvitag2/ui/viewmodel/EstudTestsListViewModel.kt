package com.example.sisvitag2.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.compose.runtime.*
import com.example.data.model.response.TestResponse
import com.example.domain.RealizarTestUseCase

class EstudTestsListViewModel: ViewModel()  {
    private val realizarTestUseCase = RealizarTestUseCase()

    private val _tests = mutableStateOf<List<TestResponse>>(emptyList())
    val tests: State<List<TestResponse>> = _tests

    init {
        fetchTests()
    }

    private fun fetchTests() {
        println("SE obtienen los test")
        viewModelScope.launch {
            try {
                val response = realizarTestUseCase.getTodosTests()
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