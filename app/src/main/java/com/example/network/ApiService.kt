package com.example.sisvita_cus1.network

import com.example.data.model.request.LoginRequest
import com.example.data.model.request.RegTestRequest
import com.example.data.model.request.RegisterRequest
import com.example.data.model.request.TestRequest
import com.example.data.model.response.GeneralResponse
import com.example.data.model.response.LoginResponse
import com.example.data.model.response.TestsEvaluableResponse
import com.example.data.model.response.TestSingleResponse
import com.example.data.model.response.TestsAllResponse
import com.example.data.model.response.TestsResultResponse
import com.example.data.model.response.registerResponse
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Body

interface ApiService {

    @POST("/regEstudiante")
    suspend fun registrarEstudiante(@Body registerRequest: RegisterRequest): registerResponse

    @POST("/login")
    suspend fun login(@Body loginRequest: LoginRequest): LoginResponse

    @GET("/tests")
    suspend fun getTests(): TestsAllResponse

    @POST("/obtenerTest")
    suspend fun getTest(@Body testRequest: TestRequest): TestSingleResponse

    @POST("/registerTest")
    suspend fun registrarTest(@Body regTestRequest: RegTestRequest): GeneralResponse

    @GET("/obtenerTodosTest")
    suspend fun getTodosTests(): TestsResultResponse

    @GET("/obtenerTestsEvaluables")
    suspend fun obtenerTestEvaluables(): TestsEvaluableResponse
}
