package com.example.pruebas

sealed class Routes(val route: String) {
    object Pantalla1: Routes("MenuScreen")
    object Pantalla2: Routes("GameScreen")
}
