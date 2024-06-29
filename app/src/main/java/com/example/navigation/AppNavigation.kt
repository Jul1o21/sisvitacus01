package com.example.sisvitacus1.navigation

import EvaluarResultadosTestScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.data.model.android.Especialista
import com.example.data.model.android.Estudiante
import com.example.sisvitag2.ui.view.*
import com.google.gson.Gson

@Composable
fun AppNavigation(startDestination: String) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = startDestination) {
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
            route = AppScreen.menuScreen.route,
            arguments = listOf(navArgument("estudianteJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val estudianteJson = backStackEntry.arguments?.getString("estudianteJson")
            val estudiante = Gson().fromJson(estudianteJson, Estudiante::class.java)
            MenuScreen(navController, estudiante)
        }
        composable(
            route = AppScreen.estudMainScreen.route,
            arguments = listOf(navArgument("estudianteJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val estudianteJson = backStackEntry.arguments?.getString("estudianteJson")
            val estudiante = Gson().fromJson(estudianteJson, Estudiante::class.java)
            val estudianteState = remember { mutableStateOf(estudiante) }
            EstudMainScreen(navController, estudianteState)
        }
        composable(
            route = AppScreen.espMainScreen.route,
            arguments = listOf(navArgument("especialistaJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val especialistaJson = backStackEntry.arguments?.getString("especialistaJson")
            val especialista = Gson().fromJson(especialistaJson, Especialista::class.java)
            val especialistaState = remember { mutableStateOf(especialista) }
            EspMainScreen(navController, especialistaState)
        }
        composable(
            route = AppScreen.espCitaScreen.route,
            arguments = listOf(navArgument("especialistaJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val especialistaJson = backStackEntry.arguments?.getString("especialistaJson")
            val especialista = Gson().fromJson(especialistaJson, Especialista::class.java)
            val especialistaState = remember { mutableStateOf(especialista) }
            EspCitaScreen(navController, especialistaState)
        }
        composable(
            route = AppScreen.estudTestScreen.route,
            arguments = listOf(
                navArgument("id_estudiante") { type = NavType.IntType },
                navArgument("id_test") { type = NavType.IntType },
                navArgument("estudianteJson") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val idEstudiante = backStackEntry.arguments?.getInt("id_estudiante") ?: 0
            val idTest = backStackEntry.arguments?.getInt("id_test") ?: 0
            val estudianteJson = backStackEntry.arguments?.getString("estudianteJson")
            val estudiante = Gson().fromJson(estudianteJson, Estudiante::class.java)
            EstudTestScreen(navController, idEstudiante, idTest, estudiante)
        }
        composable(
            route = AppScreen.evaluarResultadosTestScreen.route,
            arguments = listOf(navArgument("especialistaJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val especialistaJson = backStackEntry.arguments?.getString("especialistaJson")
            val especialista = Gson().fromJson(especialistaJson, Especialista::class.java)
            EvaluarResultadosTestScreen(navController)
        }
        composable(
            route = AppScreen.realizarVigilanciaScreen.route,
            arguments = listOf(navArgument("especialistaJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val especialistaJson = backStackEntry.arguments?.getString("especialistaJson")
            val especialista = Gson().fromJson(especialistaJson, Especialista::class.java)
            RealizarVigilanciaScreen(navController, especialista)
        }
    }
}
