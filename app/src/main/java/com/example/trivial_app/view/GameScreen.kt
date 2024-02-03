package com.example.trivial_app.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trivial_app.R
import com.example.trivial_app.navigation.Routes
import com.example.trivial_app.viewModel.SettingsViewModel
import kotlinx.coroutines.delay

@Composable
fun GameScreen(navController: NavController, settingsViewModel: SettingsViewModel) {
    var tiempoRestante by remember { mutableIntStateOf(15) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.fondo_pantalla),
                contentScale = ContentScale.FillBounds
            )
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Round ${settingsViewModel.rondaActual}/${settingsViewModel.rondasTotales}", modifier = Modifier.padding(60.dp))

        Text(
            text = settingsViewModel.preguntaActual.preguntes,
            modifier = Modifier
                .padding(top = 80.dp)
        )

        Row(modifier = Modifier.padding(top = 90.dp)) {
            Button(
                onClick = {
                    settingsViewModel.puntuacion(
                        settingsViewModel.preguntaActual.opcioA,
                        settingsViewModel.preguntaActual.respostaCorrecta
                    )
                    settingsViewModel.cambiarDeRonda(settingsViewModel.round + 1)
                    settingsViewModel.cambiarPregunta()
                    settingsViewModel.rondaActual = settingsViewModel.rondaActual + 1
                    tiempoRestante = 15
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue),
                modifier = Modifier.padding(end = 5.dp)
            ) {
                Text(text = settingsViewModel.preguntaActual.opcioA)
            }
            Button(
                onClick = {
                    settingsViewModel.puntuacion(
                        settingsViewModel.preguntaActual.opcioB,
                        settingsViewModel.preguntaActual.respostaCorrecta
                    )
                    settingsViewModel.rondaActual = settingsViewModel.rondaActual + 1
                    settingsViewModel.cambiarDeRonda(settingsViewModel.round + 1)
                    settingsViewModel.cambiarPregunta()
                    tiempoRestante = 15
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue),
                modifier = Modifier.padding(start = 5.dp)
            ) {
                Text(text = settingsViewModel.preguntaActual.opcioB)
            }
        }

        Row(modifier = Modifier.padding(top = 20.dp)) {
            Button(
                onClick = {
                    settingsViewModel.puntuacion(
                        settingsViewModel.preguntaActual.opcioC,
                        settingsViewModel.preguntaActual.respostaCorrecta
                    )
                    settingsViewModel.rondaActual = settingsViewModel.rondaActual + 1
                    settingsViewModel.cambiarDeRonda(settingsViewModel.round + 1)
                    settingsViewModel.cambiarPregunta()
                    tiempoRestante = 15
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue),
                modifier = Modifier.padding(end = 5.dp)
            ) {
                Text(text = settingsViewModel.preguntaActual.opcioC)
            }
            Button(
                onClick = {
                    settingsViewModel.puntuacion(
                        settingsViewModel.preguntaActual.opcioD,
                        settingsViewModel.preguntaActual.respostaCorrecta
                    )
                    settingsViewModel.rondaActual = settingsViewModel.rondaActual + 1
                    settingsViewModel.cambiarDeRonda(settingsViewModel.round + 1)
                    settingsViewModel.cambiarPregunta()
                    tiempoRestante = 15
                },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Blue),
                modifier = Modifier.padding(start = 5.dp)
            ) {
                Text(text = settingsViewModel.preguntaActual.opcioD)
            }
        }
        Row(
            modifier = Modifier
                .padding(top = 70.dp)) {
            CountDownTimer()
        }
    }
    FinJuego(navController, settingsViewModel, tiempoRestante)
}

@Composable
fun CountDownTimer() {
    var timeLeft by rememberSaveable { mutableIntStateOf(15) }
    LaunchedEffect(timeLeft) {
        while (timeLeft > 0) {
            delay(1000L)
            timeLeft--
        }
    }
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { timeLeft = 15 }) {
                Text(text = "Restart")
            }
        }
        Text(text = "Time left: $timeLeft")
        LinearProgressIndicator(progress = timeLeft.toFloat() / 15f)
    }
}

fun FinJuego(navController: NavController, settingsViewModel: SettingsViewModel, tiempoRestante: Int) {
    if (tiempoRestante == 0) {
        settingsViewModel.rondaActual = settingsViewModel.rondasTotales
        navController.navigate(Routes.Pantalla4.route)
    } else if (settingsViewModel.rondaActual == settingsViewModel.rondasTotales) {
        navController.navigate(Routes.Pantalla4.route)
    }
}
