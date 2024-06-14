package com.example.sisvita_cus1.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl("https://dsm-pre1.onrender.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiInstance by lazy{
        retrofit.create(ApiService::class.java)
    }


}

