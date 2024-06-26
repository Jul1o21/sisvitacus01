package com.example.sisvita_cus1.network

import com.example.data.model.request.LoginRequest
import com.example.data.model.request.RegTestRequest
import com.example.data.model.response.LoginResponse
import com.example.data.model.request.TestRequest
import com.example.data.model.response.RespondeGen
import com.example.data.model.response.TestListResponse
import com.example.data.model.response.TestResponse
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
    suspend fun getTests(): TestListResponse

    @POST("/obtenerTest")
    suspend fun getTest(@Body testRequest: TestRequest): TestResponse

    @POST("/registerTest")
    suspend fun registrarTest(@Body regTestRequest: RegTestRequest): RespondeGen


}
