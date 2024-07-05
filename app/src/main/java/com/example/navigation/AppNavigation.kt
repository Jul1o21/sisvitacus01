package com.example.sisvitacus1.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.data.model.android.Especialista
import com.example.data.model.android.Estudiante
import com.example.data.model.response.TestEvaluable
import com.example.sisvitag2.ui.view.especialista.*
import com.example.sisvitag2.ui.view.estudiante.*
import com.example.sisvitag2.ui.view.otro.*
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.google.gson.Gson

// AppNavigation.kt
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(startDestination: String) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(
            route = AppScreen.mainScreen.route
        ) {
            MainScreen(navController)
        }

        composable(
            route = AppScreen.estudRegisterScreen.route
        ) {
            EstudRegisterScreen(navController)
        }

        composable(
            route = AppScreen.loginScreen.route
        ) {
            LoginScreen(navController)
        }

        composable(
            route = AppScreen.estudMenuScreen.route,
            arguments = listOf(navArgument("estudianteJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val estudianteJson = backStackEntry.arguments?.getString("estudianteJson")
            val estudiante = Gson().fromJson(estudianteJson, Estudiante::class.java)
            EstudMenuScreen(navController, estudiante)
        }

        composable(
            route = AppScreen.estudTestsListScreen.route,
            arguments = listOf(navArgument("estudianteJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val estudianteJson = backStackEntry.arguments?.getString("estudianteJson")
            val estudiante = Gson().fromJson(estudianteJson, Estudiante::class.java)
            EstudTestsListScreen(navController, estudiante)
        }

        composable(
            route = AppScreen.espMenuScreen.route,
            arguments = listOf(navArgument("especialistaJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val especialistaJson = backStackEntry.arguments?.getString("especialistaJson")
            val especialista = Gson().fromJson(especialistaJson, Especialista::class.java)
            EspMenuScreen(navController, especialista)
        }

        composable(
            route = AppScreen.espCitaScreen.route,
            arguments = listOf(navArgument("especialistaJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val especialistaJson = backStackEntry.arguments?.getString("especialistaJson")
            val especialista = Gson().fromJson(especialistaJson, Especialista::class.java)
            EspCitaScreen(navController, especialista)
        }

        composable(
            route = AppScreen.estudRealizarTestScreen.route,
            arguments = listOf(
                navArgument("id_test") { type = NavType.IntType },
                navArgument("estudianteJson") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val idTest = backStackEntry.arguments?.getInt("id_test") ?: 0
            val estudianteJson = backStackEntry.arguments?.getString("estudianteJson")
            val estudiante = Gson().fromJson(estudianteJson, Estudiante::class.java)
            EstudRealizarTestScreen(navController, idTest, estudiante)
        }

        composable(
            route = AppScreen.realizarVigilanciaScreen.route,
            arguments = listOf(navArgument("especialistaJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val especialistaJson = backStackEntry.arguments?.getString("especialistaJson")
            val especialista = Gson().fromJson(especialistaJson, Especialista::class.java)
            EspRealizarVigilanciaScreen(navController, especialista)
        }

        composable(
            route = AppScreen.testResumenScreen.route,
            arguments = listOf(navArgument("testJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val testJson = backStackEntry.arguments?.getString("testJson")
            val test = Gson().fromJson(testJson, TestEvaluable::class.java)
            TestResumenScreen(navController, test.tipo, listOf(
                TestRespuesta("Pregunta 1", "Respuesta 1"),
                TestRespuesta("Pregunta 2", "Respuesta 2")
            ))
        }

        composable(
            route = AppScreen.espEvaluarResultadosTest.route,
            arguments = listOf(navArgument("testJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val testJson = backStackEntry.arguments?.getString("testJson")
            val test = Gson().fromJson(testJson, TestEvaluable::class.java)
            EspEvaluarResultadosTestScreen(navController, test, Especialista(
                10, 10, "Salazar", "Maria Salazar", "Gutierrez", "especialista"
            ))
        }

        composable(
            route = AppScreen.espVisualizarMapaScreen.route,
            arguments = listOf(navArgument("especialistaJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val especialistaJson = backStackEntry.arguments?.getString("especialistaJson")
            val especialista = Gson().fromJson(especialistaJson, Especialista::class.java)
            EspMapaScreen(navController, especialista)
        }
    }
}

