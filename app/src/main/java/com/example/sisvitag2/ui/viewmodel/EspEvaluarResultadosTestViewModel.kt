package com.example.sisvitag2.ui.viewmodel

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

    private val _showDialog = mutableStateOf(false)
    val showDialog: State<Boolean> = _showDialog

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
                    descripcion = _descripcionState.value,
                    resultado = _resultadoState.value,
                    tratamiento = _tratamientoState.value,
                    recomendacion = _recomendacionState.value,
                    notas = _notasState.value
                )
                val response = repository.registrarDiagnostico(request)
                _isLoading.value = false
                if (response != null && response.success) {
                    _rSuccess.value = true
                    _showDialog.value = true  // Actualiza el estado showDialog
                } else {
                    _isError.value = true
                }
            } catch (e: Exception) {
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

    fun updateShowDialog(value: Boolean) {
        _showDialog.value = value
    }
}

