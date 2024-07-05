package com.example.sisvitag2.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.request.DiagnosticoRequest
import com.example.data.model.response.DiagnosticoResponse
import com.example.domain.EvaluarResultadosTestUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class EspEvaluarResultadosTestViewModel : ViewModel() {
    private val repository = EvaluarResultadosTestUseCase()

    private val _descripcionState = mutableStateOf("")
    val descripcionState: State<String> = _descripcionState

    private val _resultadoState = mutableStateOf("")
    val resultadoState: State<String> = _resultadoState

    private val _tratamientoState = mutableStateOf("")
    val tratamientoState: State<String> = _tratamientoState

    private val _recomendacionState = mutableStateOf("")
    val recomendacionState: State<String> = _recomendacionState

    private val _notasState = mutableStateOf("")
    val notasState: State<String> = _notasState

    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _rSuccess = mutableStateOf(false)
    val rSuccess: State<Boolean> = _rSuccess

    fun setDescripcion(descripcion: String) {
        _descripcionState.value = descripcion
    }

    fun setResultado(resultado: String) {
        _resultadoState.value = resultado
    }

    fun setTratamiento(tratamiento: String) {
        _tratamientoState.value = tratamiento
    }

    fun setRecomendacion(recomendacion: String) {
        _recomendacionState.value = recomendacion
    }

    fun setNotas(notas: String) {
        _notasState.value = notas
    }

    fun registrarDiagnostico(idUsu: Int, idTestRes: Int, descripcion: String, resultado: String, tratamiento: String, recomendacion: String, notas: String) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val request = DiagnosticoRequest(
                    id_usu = idUsu,
                    id_test_res = idTestRes,
                    descripcion = descripcion,
                    resultado = resultado,
                    tratamiento = tratamiento,
                    recomendacion = recomendacion,
                    notas = notas
                )
                val response = repository.registrarDiagnostico(request)
                _isLoading.value = false
                if (response != null && response.success) {
                    _rSuccess.value = true
                } else {
                    _isError.value = true
                }
            } catch (e: Exception) {
                Log.e("EspEvalResultadosVM", "Error al registrar diagnóstico: ${e.message}")
                _isError.value = true
                _isLoading.value = false
            }
        }
    }

    fun updateIsError(value: Boolean) {
        _isError.value = value
    }

    fun updateRSuccess(value: Boolean) {
        _rSuccess.value = value
    }
}
