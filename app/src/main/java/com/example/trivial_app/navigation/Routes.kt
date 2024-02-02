package com.example.trivial_app.navigation

sealed class Routes(val route: String) {
    object Pantalla1: Routes("LaunchScreen")
    object Pantalla2: Routes("MenuScreen")
    object Pantalla3: Routes("GameScreen")
    object Pantalla4: Routes("ResultScreen")
    object Pantalla5: Routes("SettingsScreen")
}
