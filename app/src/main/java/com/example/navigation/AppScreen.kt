package com.example.sisvitacus1.navigation

import android.net.Uri
import com.example.data.model.android.Especialista
import com.example.data.model.android.Estudiante
import com.google.gson.Gson

sealed class AppScreen(val route: String) {

    object mainScreen: AppScreen("inicio")

    object estudRegisterScreen: AppScreen("register")

    object loginScreen: AppScreen("login")

    object menuScreen : AppScreen("menu/{estudianteJson}") {
        fun createRoute(estudiante: Estudiante): String {
            val estudianteJson = Uri.encode(Gson().toJson(estudiante))
            return "menu/$estudianteJson"
        }
    }

    object estudMainScreen : AppScreen("estudmain/{estudianteJson}") {
        fun createRoute(estudiante: Estudiante): String {
            val estudianteJson = Uri.encode(Gson().toJson(estudiante))
            return "estudmain/$estudianteJson"
        }
    }

    object espMainScreen : AppScreen("espmain/{especialistaJson}") {
        fun createRoute(especialista: Especialista): String {
            val especialistaJson = Uri.encode(Gson().toJson(especialista))
            return "espmain/$especialistaJson"
        }
    }

    object espCitaScreen : AppScreen("espcita/{especialistaJson}") {
        fun createRoute(especialista: Especialista): String {
            val especialistaJson = Uri.encode(Gson().toJson(especialista))
            return "espcita/$especialistaJson"
        }
    }

    object estudTestScreen: AppScreen("test/{id_estudiante}/{id_test}/{estudianteJson}") {
        fun createRoute(idEstudiante: Int, idTest: Int, estudiante: Estudiante): String {
            val estudianteJson = Uri.encode(Gson().toJson(estudiante))
            return "test/$idEstudiante/$idTest/$estudianteJson"
        }
    }
    object evaluarResultadosTestScreen : AppScreen("evaluar_resultados_test/{especialistaJson}") {
        fun createRoute(especialista: Especialista): String {
            val especialistaJson = Uri.encode(Gson().toJson(especialista))
            return "evaluar_resultados_test/$especialistaJson"
        }
    }
}
