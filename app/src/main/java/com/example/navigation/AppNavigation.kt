package com.example.sisvitacus1.navigation

import com.example.sisvitag2.ui.view.especialista.EspEvaluarResultadosTestScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.data.model.android.Especialista
import com.example.data.model.android.Estudiante
import com.example.sisvitag2.ui.view.especialista.EspCitaScreen
import com.example.sisvitag2.ui.view.especialista.EspMapaScreen
import com.example.sisvitag2.ui.view.especialista.EspMapaScreenPreview
import com.example.sisvitag2.ui.view.especialista.EspMenuScreen
import com.example.sisvitag2.ui.view.especialista.EspRealizarVigilanciaScreen
import com.example.sisvitag2.ui.view.estudiante.EstudMenuScreen
import com.example.sisvitag2.ui.view.estudiante.EstudRealizarTestScreen
import com.example.sisvitag2.ui.view.estudiante.EstudRegisterScreen
import com.example.sisvitag2.ui.view.estudiante.EstudTestsListScreen
import com.example.sisvitag2.ui.view.otro.LoginScreen
import com.example.sisvitag2.ui.view.otro.MainScreen
import com.google.gson.Gson

@Composable
fun AppNavigation(startDestination: String) {
    // Se crea un NavController instance
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
            route = AppScreen.evaluarResultadosTestScreen.route,
            arguments = listOf(navArgument("especialistaJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val especialistaJson = backStackEntry.arguments?.getString("especialistaJson")
            val especialista = Gson().fromJson(especialistaJson, Especialista::class.java)
            EspEvaluarResultadosTestScreen(navController, especialista)
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
            route = AppScreen.espVisualizarMapaScreen.route,
            arguments = listOf(navArgument("especialistaJson") { type = NavType.StringType })
        ) { backStackEntry ->
            val especialistaJson = backStackEntry.arguments?.getString("especialistaJson")
            val especialista = Gson().fromJson(especialistaJson, Especialista::class.java)
            EspMapaScreen(navController, especialista)
        }

    }
}
