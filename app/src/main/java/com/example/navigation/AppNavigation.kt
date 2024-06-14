package com.example.sisvitacus1.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.sisvitag2.ui.view.LoginScreen
import com.example.sisvitag2.ui.view.MainScreen

@Composable
fun AppNavigation(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreen.mainScreen.route){

        composable(route = AppScreen.mainScreen.route){
            MainScreen(navController)
        }
        composable(route = AppScreen.loginScreen.route ){
            LoginScreen(navController)
        }
        composable(route = AppScreen.registerScreen.route){
            LoginScreen(navController)
        }

    }
}