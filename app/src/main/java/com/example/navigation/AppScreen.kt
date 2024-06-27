package com.example.sisvitacus1.navigation

import android.net.Uri
import com.example.data.model.Especialista
import com.example.sisvita_cus1.data.model.Estudiante
import com.google.gson.Gson

sealed class AppScreen(val route: String) {

    // Pantalla de inicio (MainScreen)
    object mainScreen: AppScreen("inicio")

    // Pantalla de registro (EstudRegisterScreen)
    object estudRegisterScreen: AppScreen("register")

    // Pantala de login (LoginScreen)
    object loginScreen: AppScreen("login")

    // Pantalla de Menú del estudiante (MenuScreen)
    object menuScreen : AppScreen("menu/{estudianteJson}") {
        fun createRoute(estudiante: Estudiante): String {
            val estudianteJson = Uri.encode(Gson().toJson(estudiante))
            return "menu/$estudianteJson"
        }
    }

    // Pantalla principal del estudiante (EstudMainScreen)
    object estudMainScreen : AppScreen("estudmain/{estudianteJson}") {
        fun createRoute(estudiante: Estudiante): String {
            val estudianteJson = Uri.encode(Gson().toJson(estudiante))
            return "estudmain/$estudianteJson"
        }
    }

    // EspMainScreen - espmain

    object espCitaScreen : AppScreen("espcita/{especialistaJson}") {
        fun createRoute(especialista: Especialista): String {
            val especialistaJson = Uri.encode(Gson().toJson(especialista))
            return "espmain/$especialistaJson"
        }
    }

    object espMainScreen : AppScreen("espmain/{especialistaJson}") {
        fun createRoute(especialista: Especialista): String {
            val especialistaJson = Uri.encode(Gson().toJson(especialista))
            return "espmain/$especialistaJson"
        }
    }

    //EstudTestScreen - test
    object estudTestScreen: AppScreen("test/{id_estudiante}/{id_test}/{estudianteJson}") {
        fun createRoute(idEstudiante: Int, idTest: Int, estudiante: Estudiante): String {
            val estudianteJson = Uri.encode(Gson().toJson(estudiante))
            return "test/$idEstudiante/$idTest/$estudianteJson"
        }
    }
}
