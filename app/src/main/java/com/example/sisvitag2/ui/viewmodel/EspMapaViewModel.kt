package com.example.sisvitag2.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.response.TestEvaluable
import com.example.domain.VisualizarMapaCalorUseCase
import kotlinx.coroutines.launch

class EspMapaViewModel : ViewModel() {

    private val visualizarMapaCalorUseCase = VisualizarMapaCalorUseCase()

    private val _startPeriodo = mutableStateOf("")
    val startPeriodo: State<String> = _startPeriodo

    private val _endPeriodo = mutableStateOf("")
    val endPeriodo: State<String> = _endPeriodo



    private val _testsEvaluables = MutableLiveData<List<TestEvaluable>>()
    val testsEvaluables: LiveData<List<TestEvaluable>> get() = _testsEvaluables

    private val _tiposTestsLista = MutableLiveData<List<String>>()
    val tiposTestsLista: LiveData<List<String>> get() = _tiposTestsLista

    private val _gravedadLista = MutableLiveData<List<String>>()
    val gravedadLista: LiveData<List<String>> get() = _gravedadLista

    init {
        fetchTodosTests()
    }
    private fun fetchTodosTests() {

        println("Se llama a fetchTodosTests")
        viewModelScope.launch {
            try {
                val response = visualizarMapaCalorUseCase.getTestsEvaluables()

                if (response.success) {
                    val tests = response.data.tests
                    _testsEvaluables.value = tests
                    _tiposTestsLista.value = tests.map { it.tipo }.distinct()
                    _gravedadLista.value = tests.map { it.nivel }.distinct()

                    println("Los tipos test obtenidos son $_tiposTestsLista.value")
                    Log.d("EspRealizarVigilanciaVM", "Tests evaluables obtenidos: $tests")
                } else {
                    Log.e("EspRealizarVigilanciaVM", "Error al obtener los tests: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("EspRealizarVigilanciaVM", "Excepción al obtener los tests: ${e.message}")
            }
        }
    }


    fun setStartPeriodo(value: String) {
        _startPeriodo.value = value
    }

    fun setEndPeriodo(value: String) {
        _endPeriodo.value = value
    }

    fun clearPeriodos() {
        _startPeriodo.value = ""
        _endPeriodo.value = ""
    }
}