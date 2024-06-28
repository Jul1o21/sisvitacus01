package com.example.data.repository

import com.example.data.model.android.Estudiante
import com.example.data.model.request.LoginRequest
import com.example.data.model.response.GeneralResponse
import com.example.data.model.response.LoginResponse
import com.example.sisvita_cus1.network.ApiInstance

class EstudianteRepository {
    private val apiService = ApiInstance.apiInstance

    //Registrar un estudiante en el sistema se debe enviar el Request por los parametros
    suspend fun registrarEstudiante(): GeneralResponse {
        return apiService.registrarEstudiante()
    }


}