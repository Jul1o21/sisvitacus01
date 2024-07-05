package com.example.sisvitag2.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.request.DiagnosticoRequest
import com.example.domain.EvaluarResultadosTestUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EspEvaluarResultadosTestViewModel : ViewModel() {
    private val repository = EvaluarResultadosTestUseCase()

    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _rSuccess = mutableStateOf(false)
    val rSuccess: State<Boolean> = _rSuccess

    private val _showDialog = mutableStateOf(false)
    val showDialog: State<Boolean> = _showDialog

    fun registrarDiagnostico(
        idUsu: Int,
        idTestRes: Int,
        descripcion: String,
        resultado: String,
        tratamiento: String,
        recomendacion: String,
        notas: String
    ) {
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
                Log.d("ApiRequest", "Request: $request")
                val response = repository.registrarDiagnostico(request)
                _isLoading.value = false
                if (response != null && response.success) {
                    _rSuccess.value = true
                    _showDialog.value = true
                } else {
                    _isError.value = true
                }
            } catch (e: Exception) {
                _isError.value = true
                _isLoading.value = false
                Log.e("ViewModel", "Error al registrar diagnóstico: ${e.message}")
            }
        }
    }

    fun updateShowDialog(value: Boolean) {
        _showDialog.value = value
    }
}


