package com.example.sisvitag2.viewmodel

import androidx.lifecycle.*
import com.example.data.model.Question
import com.example.sisvita_cus1.network.ApiInstance
import kotlinx.coroutines.launch

class EstudTestViewModel : ViewModel() {

    // LiveData para mantener la lista de preguntas
    private val _questions = MutableLiveData<List<Question>>()
    val questions: LiveData<List<Question>> = _questions

    // Inicialización: obtiene las preguntas de la API al crear el ViewModel
    init {
        fetchQuestionsFromApi()
    }

    // Función para obtener las preguntas desde la API usando Retrofit
    private fun fetchQuestionsFromApi() {
        viewModelScope.launch {
            try {
                // Llamada a la API para obtener las preguntas
                val questionsFromApi = ApiInstance.apiInstance.getQuestions()
                _questions.value = questionsFromApi
            } catch (e: Exception) {
                // Manejo de errores, por ejemplo, mostrar un mensaje de error o registrar el error
                e.printStackTrace()
            }
        }
    }

    // Función para manejar la selección de una opción de respuesta
    fun onAnswerSelected(questionIndex: Int, selectedOption: Int) {
        _questions.value = _questions.value?.mapIndexed { index, question ->
            if (index == questionIndex) {
                // Crear una nueva instancia de Question con la opción seleccionada actualizada
                question.copy(selectedOption = selectedOption)
            } else {
                question // Devolver la pregunta sin cambios si no es la pregunta actual
            }
        }
    }
}

