package com.example.sisvitacus1.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.sisvitag2.ui.view.EstudMainScreen
import com.example.sisvitag2.ui.view.EstudRegisterScreen
import com.example.sisvitag2.ui.view.EstudTestScreen
import com.example.sisvitag2.ui.view.LoginScreen
import com.example.sisvitag2.ui.view.MainScreen

@Composable
fun AppNavigation(startDestination: String){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination){


        composable(
            route = AppScreen.estudMainScreen.route,
            arguments = listOf(navArgument("id_usu") { type = NavType.IntType })
        ) { backStackEntry ->
            val id_usu = backStackEntry.arguments?.getInt("id_usu") ?: 0
            EstudMainScreen(navController, id_usu)
        }

        composable(route = AppScreen.estudRegisterScreen.route){
            EstudRegisterScreen(navController)
        }

        composable(route = AppScreen.estudTestScreen.route){
            EstudTestScreen(navController)
        }

        composable(route = AppScreen.loginScreen.route ){
            LoginScreen(navController)
        }

        composable(route = AppScreen.mainScreen.route){
            MainScreen(navController)
        }
    }
}