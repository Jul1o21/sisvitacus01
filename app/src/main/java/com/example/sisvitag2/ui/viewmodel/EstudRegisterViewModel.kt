package com.example.sisvitag2.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.model.request.RegisterRequest
import com.example.sisvita_cus1.domain.RegistrarEstudUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EstudRegisterViewModel: ViewModel()  {
    private val repository = RegistrarEstudUseCase()

    val tipo: String = "estudiante"

    private val _nombresState = mutableStateOf("")
    val nombresState: State<String> = _nombresState

    private val _paternoState = mutableStateOf("")
    val paternoState: State<String> = _paternoState

    private val _maternoState = mutableStateOf("")
    val maternoState: State<String> = _maternoState

    private val _tipoState = mutableStateOf("")
    val tipoState: State<String> = _tipoState

    private val _correoState = mutableStateOf("")
    val correoState: State<String> = _correoState

    private val _contraState = mutableStateOf("")
    val contraState: State<String> = _contraState

    fun setNombres(nombres: String) {
        _nombresState.value = nombres
    }

    fun setPaterno(paterno: String) {
        _paternoState.value = paterno
    }

    fun setMaterno(materno: String) {
        _maternoState.value = materno
    }

    fun setTipo(tipo: String) {
        _tipoState.value = tipo
    }

    fun setCorreo(correo: String) {
        _correoState.value = correo
    }

    fun setContra(contra: String) {
        _contraState.value = contra
    }



    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    private val _rSuccess = mutableStateOf(false)
    val rSuccess: State<Boolean> = _rSuccess

    fun registrarEstudiante() {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val RegisterRequesti = RegisterRequest(3000,
                    _nombresState.value,
                    _paternoState.value,
                    _maternoState.value,
                    _correoState.value,
                    _contraState.value)
                val response = repository.registrar(RegisterRequesti)
                _isLoading.value = false
                if (response.success && response.data != null){
                    println("JSON recibido con éxito: $response") // Mensaje para la consola de Tomcat
                    _rSuccess.value=true
                }
            } catch (e: Exception) {
                _isError.value = true
                _isLoading.value = false
                // Manejar el error según sea necesario
            }
        }
    }

    fun updateIsError(value: Boolean) {
        _isError.value = value
    }
}