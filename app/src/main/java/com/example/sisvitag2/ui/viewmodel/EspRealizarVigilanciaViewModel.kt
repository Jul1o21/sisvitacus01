package com.example.sisvitag2.ui.view.especialista

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.response.TestEvaluable
import com.example.domain.RealizarVigilanciaUseCase
import kotlinx.coroutines.launch

class EspRealizarVigilanciaViewModel : ViewModel() {
    private val realizarVigilanciaUseCase = RealizarVigilanciaUseCase()

    private val _testsEvaluables = MutableLiveData<List<TestEvaluable>>()
    val testsEvaluables: LiveData<List<TestEvaluable>> get() = _testsEvaluables

    private val _tipoTests = MutableLiveData<List<String>>()
    val tipoTests: LiveData<List<String>> get() = _tipoTests

    private val _nivelesGravedad = MutableLiveData<List<String>>()
    val nivelesGravedad: LiveData<List<String>> get() = _nivelesGravedad

    init {
        obtenerTestsEvaluables()
    }

    fun obtenerTestsEvaluables() {
        viewModelScope.launch {
            try {
                val response = realizarVigilanciaUseCase.getTestsEvaluables()
                if (response.success) {
                    val tests = response.data.tests
                    _testsEvaluables.value = tests
                    _tipoTests.value = tests.map { it.tipo }.distinct()
                    _nivelesGravedad.value = tests.map { it.nivel }.distinct()
                    Log.d("EspRealizarVigilanciaVM", "Tests evaluables obtenidos: $tests")
                } else {
                    Log.e("EspRealizarVigilanciaVM", "Error al obtener los tests: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("EspRealizarVigilanciaVM", "Excepción al obtener los tests: ${e.message}")
            }
        }
    }
}
