package com.example.sisvitag2.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sisvita_cus1.data.model.Estudiante
import com.example.sisvita_cus1.data.repository.RegEstudRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EstudRegisterViewModel: ViewModel()  {
    private val repository = RegEstudRepository()

    private val _isError = mutableStateOf(false)
    val isError: State<Boolean> = _isError

    private val _isLoading = mutableStateOf(false)
    val isLoading: State<Boolean> = _isLoading

    fun registrarEstudiante(estudiante: Estudiante) {
        _isLoading.value = true
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.registrarEstudiante(estudiante)
                _isLoading.value = false
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