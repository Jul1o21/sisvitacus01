package com.example.sisvita_cus1.network

import com.example.data.model.LoginRequest
import com.example.data.model.LoginResponse
import com.example.sisvita_cus1.data.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiService {
    @POST("/regEstudiante")
    suspend fun registrarEstudiante(@Body estudiante: Estudiante): Estudiante

    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("/tests")
    suspend fun getTests(): List<Test>

    @POST("/submitTest")
    suspend fun submitTest(@Body testResponse: TestResponse): Result<TestResponse>
}
