package com.example.sisvitacus1.navigation

import android.net.Uri
import com.example.sisvita_cus1.data.model.Estudiante
import com.google.gson.Gson


sealed class AppScreen(val route: String) {
    // EstudMainScreen - estudmain
    object estudMainScreen : AppScreen("estudmain/{estudianteJson}") {
        fun createRoute(estudiante: Estudiante): String {
            val estudianteJson = Uri.encode(Gson().toJson(estudiante))
            return "estudmain/$estudianteJson"
        }
    }

    //EstudRegisterScreen - register
    object estudRegisterScreen: AppScreen("register")

    //EstudTestScreen - test
    object estudTestScreen: AppScreen("test")

    //LoginScreen - login
    object loginScreen: AppScreen("login")

    //MainScreen - inicio
    object mainScreen: AppScreen("inicio")

}
