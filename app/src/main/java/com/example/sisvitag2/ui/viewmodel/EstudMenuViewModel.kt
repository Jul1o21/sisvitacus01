package com.example.sisvitag2.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import com.example.data.model.android.Estudiante
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel

class EstudMenuViewModel: ViewModel()  {

    private val _estudiante = mutableStateOf<Estudiante?>(null)
    val estudiante: State<Estudiante?> = _estudiante

    fun setEstudiante(estudiante: Estudiante?) {
        _estudiante.value = estudiante
    }

    fun getEstudiante(): Estudiante? {
        return _estudiante.value
    }

    fun clearEstudiante() {
        _estudiante.value = null
    }


}