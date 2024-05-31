package com.example.sisvita_cus1.network


import com.example.sisvita_cus1.data.model.Estudiante
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/regEstudiante")
    suspend fun registrarEstudiante(@Body estudiante: Estudiante): Estudiante
}


