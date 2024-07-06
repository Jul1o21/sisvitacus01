package com.example.domain

import android.util.Log
import com.example.data.model.request.DiagnosticoRequest
import com.example.data.model.response.DiagnosticoResponse
import com.example.data.repository.DiagnosticoRepository

class EvaluarResultadosTestUseCase {
    private val diagnosticoRepository = DiagnosticoRepository()

    suspend fun registrarDiagnostico(diagnosticoRequest: DiagnosticoRequest): DiagnosticoResponse? {
        return try {
            diagnosticoRepository.registrarDiagnostico(diagnosticoRequest)
        } catch (e: Exception) {
            Log.e("EvaluarResultadosTestUC", "Error al registrar diagnóstico: ${e.message}")
            null
        }
    }
}
