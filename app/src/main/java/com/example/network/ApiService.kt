package com.example.sisvita_cus1.network


import com.example.data.model.LoginRequest
import com.example.data.model.LoginResponse
import com.example.data.model.Question
import com.example.sisvita_cus1.data.model.Estudiante
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/regEstudiante")
    suspend fun registrarEstudiante(@Body estudiante: Estudiante): Estudiante

    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("questions")
    suspend fun getQuestions(): List<Question>
}


