package com.example.sisvitag2.ui.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.data.model.android.Especialista
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel


class EspMenuViewModel: ViewModel()  {

    private val _especialista = mutableStateOf<Especialista?>(null)
    val especialista: State<Especialista?> get() = _especialista

    fun setEspecialista(especialista: Especialista?) {
        _especialista.value = especialista
    }

    fun getEspecialista(): Especialista? {
        return _especialista.value
    }

    fun clearEstudiante() {
        _especialista.value = null
    }
}