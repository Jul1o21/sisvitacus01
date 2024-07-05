package com.example.sisvitag2.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class EspMapaViewModel : ViewModel() {

    private val _startPeriodo = mutableStateOf("")
    val startPeriodo: State<String> = _startPeriodo

    private val _endPeriodo = mutableStateOf("")
    val endPeriodo: State<String> = _endPeriodo

    fun setStartPeriodo(value: String) {
        _startPeriodo.value = value
    }

    fun setEndPeriodo(value: String) {
        _endPeriodo.value = value
    }

    fun clearPeriodos() {
        _startPeriodo.value = ""
        _endPeriodo.value = ""
    }
}