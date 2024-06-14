package com.example.sisvitacus1.navigation


sealed class AppScreen(val route: String){
    object mainScreen: AppScreen("main")
    object loginScreen: AppScreen("login")
    object registerScreen: AppScreen("register")
}