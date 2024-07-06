package com.example.data.repository

import android.util.Log
import com.example.data.model.request.DiagnosticoRequest
import com.example.data.model.response.DiagnosticoResponse
import com.example.sisvita_cus1.network.ApiInstance

class DiagnosticoRepository {
    private val apiService = ApiInstance.apiInstance

    suspend fun registrarDiagnostico(diagnosticoRequest: DiagnosticoRequest): DiagnosticoResponse {
        Log.d("ApiRequest", "Request: $diagnosticoRequest")
        val response = apiService.registerDiagnostico(diagnosticoRequest)
        Log.d("ApiResponse", "Response: $response")
        return response
    }
}
