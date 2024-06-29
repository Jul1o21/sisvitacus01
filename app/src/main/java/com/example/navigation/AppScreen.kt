package com.example.sisvitacus1.navigation

import android.net.Uri
import com.example.data.model.android.Especialista
import com.example.data.model.android.Estudiante
import com.google.gson.Gson

sealed class AppScreen(val route: String) {

    object mainScreen: AppScreen("inicio")
    object estudRegisterScreen: AppScreen("register")
    object loginScreen: AppScreen("login")


    object estudMenuScreen : AppScreen("menu/{estudianteJson}") {
        fun createRoute(estudiante: Estudiante): String {
            val estudianteJson = Uri.encode(Gson().toJson(estudiante))
            return "menu/$estudianteJson"
        }
    }
    object estudTestsListScreen : AppScreen("tests_list/{estudianteJson}") {
        fun createRoute(estudiante: Estudiante): String {
            val estudianteJson = Uri.encode(Gson().toJson(estudiante))
            return "tests_list/$estudianteJson"
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
    object estudRealizarTestScreen: AppScreen("realizar_test/{id_estudiante}/{id_test}/{estudianteJson}") {
        fun createRoute(idEstudiante: Int, idTest: Int, estudiante: Estudiante): String {
            val estudianteJson = Uri.encode(Gson().toJson(estudiante))
            return "realizar_test/$idEstudiante/$idTest/$estudianteJson"
        }
    }
    object evaluarResultadosTestScreen : AppScreen("evaluar_resultados_test/{especialistaJson}") {
        fun createRoute(especialista: Especialista): String {
            val especialistaJson = Uri.encode(Gson().toJson(especialista))
            return "evaluar_resultados_test/$especialistaJson"
        }
    }
    object realizarVigilanciaScreen : AppScreen("realizar_vigilancia/{especialistaJson}") {
        fun createRoute(especialista: Especialista): String {
            val especialistaJson = Uri.encode(Gson().toJson(especialista))
            return "realizar_vigilancia/$especialistaJson"
        }
    }
}
