package com.example.trivial_app.navigation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.trivial_app.ui.theme.Trivial_appTheme
import com.example.trivial_app.view.GameScreen
import com.example.trivial_app.view.LaunchScreen
import com.example.trivial_app.view.MenuScreen
import com.example.trivial_app.view.ResultScreen
import com.example.trivial_app.view.SettingsScreen
import com.example.trivial_app.viewModel.SettingsViewModel

val settingsViewModel = SettingsViewModel()

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Trivial_appTheme(darkTheme = settingsViewModel.modoOscuro) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navigationController = rememberNavController()
                    NavHost(
                        navController = navigationController,
                        startDestination = Routes.Pantalla1.route
                    ) {
                        composable(Routes.Pantalla1.route) { LaunchScreen(navigationController) }
                        composable(Routes.Pantalla2.route) { MenuScreen(navigationController) }
                        composable(Routes.Pantalla3.route) { GameScreen(navigationController, settingsViewModel) }
                        composable(
                            Routes.Pantalla4.route,
                            arguments = listOf(navArgument("victoria") { type = NavType.BoolType})
                        ) { backStackEntry ->
                            ResultScreen(
                                navigationController,
                                settingsViewModel,
                                backStackEntry.arguments?.getBoolean("victoria") ?: false
                            )
                        }
                        composable(Routes.Pantalla5.route) { SettingsScreen(navigationController, settingsViewModel) }
                    }
                }
            }
        }
    }
}

