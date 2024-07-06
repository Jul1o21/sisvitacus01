package com.example.data.repository

import com.example.data.model.request.RegisterRequest
import com.example.data.model.response.GeneralResponse
import com.example.data.model.response.UbigeosEstudResponse
import com.example.data.model.response.registerResponse
import com.example.sisvita_cus1.network.ApiInstance

class EstudianteRepository {
    private val apiService = ApiInstance.apiInstance

    //Registrar un estudiante en el sistema se debe enviar el Request por los parametros
    suspend fun registrarEstudiante(registerRequest: RegisterRequest): registerResponse {
        return apiService.registrarEstudiante(registerRequest)
    }

    //Obetener los ubigeos de los estudiantes que estan en la app
    suspend fun obtenerUbigeosEst(): UbigeosEstudResponse {
        return apiService.obtenerUbigeosEst()
    }

}