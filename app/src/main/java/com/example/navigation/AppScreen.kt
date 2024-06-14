package com.example.sisvitacus1.navigation


sealed class AppScreen(val route: String){

    //AlumMainScreen - mainalumno
    object alumMainScreen: AppScreen("mainalumno")

    //AlumRegisterScreen - register
    object alumRegisterScreen: AppScreen("register")

    //AlumTestScreen - test
    object alumTestScreen: AppScreen("test")

    //LoginScreen - login
    object loginScreen: AppScreen("login")

    //MainScreen - inicio
    object mainScreen: AppScreen("inicio")

}
