package com.example.sisvitacus1.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sisvitag2.ui.view.AlumMainScreen
import com.example.sisvitag2.ui.view.AlumRegisterScreen
import com.example.sisvitag2.ui.view.AlumTestScreen
import com.example.sisvitag2.ui.view.LoginScreen
import com.example.sisvitag2.ui.view.MainScreen

@Composable
fun AppNavigation(startDestination: String){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination){


        composable(route = AppScreen.alumMainScreen.route){
            AlumMainScreen(navController)
        }

        composable(route = AppScreen.alumRegisterScreen.route){
            AlumRegisterScreen(navController)
        }

        composable(route = AppScreen.alumTestScreen.route){
            AlumTestScreen(navController)
        }

        composable(route = AppScreen.loginScreen.route ){
            LoginScreen(navController)
        }

        composable(route = AppScreen.mainScreen.route){
            MainScreen(navController)
        }
    }
}