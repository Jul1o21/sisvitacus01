package com.example.sisvitag2.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.example.data.model.android.Especialista
import com.example.data.model.android.Estudiante

class EspMenuViewModel {
    private val _especialista = mutableStateOf<Especialista?>(null)
    val especialista: State<Especialista?> = _especialista


    fun setEspecialista(especialista: Especialista?) {
        _especialista.value = especialista
    }

    fun getEspecialista(): Especialista? {
        return _especialista.value
    }

    fun clearEspecialista() {
        _especialista.value = null
    }
}