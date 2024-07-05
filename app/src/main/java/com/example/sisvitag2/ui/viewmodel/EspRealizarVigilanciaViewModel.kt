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

    init {
        obtenerTestsEvaluables()
    }

    fun obtenerTestsEvaluables() {
        viewModelScope.launch {
            try {
                val response = realizarVigilanciaUseCase.getTestsEvaluables()
                if (response.success) {
                    _testsEvaluables.value = response.data.tests
                    Log.d("EspRealizarVigilanciaVM", "Tests evaluables obtenidos: ${response.data.tests}")
                } else {
                    Log.e("EspRealizarVigilanciaVM", "Error al obtener los tests: ${response.message}")
                }
            } catch (e: Exception) {
                Log.e("EspRealizarVigilanciaVM", "Excepción al obtener los tests: ${e.message}")
            }
        }
    }
}
