package com.example.sisvitacus1.navigation

import android.net.Uri
import com.example.data.model.Especialista
import com.example.sisvita_cus1.data.model.Estudiante
import com.google.gson.Gson


sealed class AppScreen(val route: String) {

    // EspMainScreen - espmain
    object espMainScreen : AppScreen("espmain/{especialistaJson}") {
        fun createRoute(especialista: Especialista): String {
            val especialistaJson = Uri.encode(Gson().toJson(especialista))
            return "espmain/$especialistaJson"
        }
    }
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
    object estudTestScreen: AppScreen("test/{id_estudiante}/{id_test}") {
        fun createRoute(idEstudiante: Int, idTest: Int): String {
            return "test/$idEstudiante/$idTest"
        }
    }
    //LoginScreen - login
    object loginScreen: AppScreen("login")

    //MainScreen - inicio
    object mainScreen: AppScreen("inicio")

}
