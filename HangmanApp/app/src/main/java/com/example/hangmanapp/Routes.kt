package com.example.hangmanapp

sealed class Routes(val route: String) {
    object Pantalla1: Routes("LaunchScreen")
    object Pantalla2: Routes("MenuScreen")
    object Pantalla3: Routes("GameScreen")
    object Pantalla4: Routes("ResultScreen")
}
