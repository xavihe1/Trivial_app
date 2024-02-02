package com.example.navigation

sealed class Routes(val route:String) {
    object Pantalla1:Routes("pantalla1")
    object Pantalla2:Routes("pantalla2")
    object Pantalla3:Routes("pantalla3/{nom}/{anys}/{resposta}") {
        fun createRoute(nom : String, anys : String, resposta : String) = "pantalla3/$nom/$anys/$resposta"
    }
}