package com.example.sisvitacus1.navigation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sisvita_cus1.data.model.Estudiante
import com.example.sisvitag2.ui.view.EstudMainScreen
import com.example.sisvitag2.ui.view.EstudRegisterScreen
import com.example.sisvitag2.ui.view.EstudTestScreen
import com.example.sisvitag2.ui.view.LoginScreen
import com.example.sisvitag2.ui.view.MainScreen
import com.google.gson.Gson

@Composable
fun AppNavigation(startDestination: String) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {

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
            route = AppScreen.estudRegisterScreen.route
        ){
            EstudRegisterScreen(navController)
        }

        composable(
            route = AppScreen.estudTestScreen.route
        ){
            EstudTestScreen(navController)
        }

        composable(
            route = AppScreen.loginScreen.route
        ){
            LoginScreen(navController)
        }

        composable(
            route = AppScreen.mainScreen.route
        ){
            MainScreen(navController)
        }
    }
}