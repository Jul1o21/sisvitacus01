package com.example.sisvitacus1.navigation


sealed class AppScreen(val route: String){

    //EstudMainScreen - estudmain
    object estudMainScreen: AppScreen("estudmain")

    //EstudRegisterScreen - register
    object estudRegisterScreen: AppScreen("register")

    //EstudTestScreen - test
    object estudTestScreen: AppScreen("test")

    //LoginScreen - login
    object loginScreen: AppScreen("login")

    //MainScreen - inicio
    object mainScreen: AppScreen("inicio")

}
