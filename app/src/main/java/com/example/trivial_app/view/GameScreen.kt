package com.example.trivial_app.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trivial_app.R
import com.example.trivial_app.navigation.Routes
import com.example.trivial_app.viewModel.SettingsViewModel
import kotlinx.coroutines.delay

@Composable
fun GameScreen(navController: NavController, settingsViewModel: SettingsViewModel) {
    var tiempoRestante by remember { mutableIntStateOf(settingsViewModel.tiempoRonda) }
    var juegoTerminado by remember { mutableStateOf(false) }
    var tiempoAgotado by remember { mutableStateOf(false) }
    val textPrimaryColor = colorResource(id = R.color.text_primary)
    val textSecondaryColor = colorResource(id = R.color.text_secondary)
    val buttonColors = listOf(
        colorResource(id = R.color.verde),
        colorResource(id = R.color.azul),
        colorResource(id = R.color.amarillo),
        colorResource(id = R.color.rojo)
    )

    LaunchedEffect(key1 = tiempoRestante, key2 = settingsViewModel.rondaActual) {
        if (tiempoRestante > 0 && settingsViewModel.rondaActual <= settingsViewModel.rondasTotales) {
            delay(1000L)
            tiempoRestante--
        } else if (tiempoRestante == 0) {
            tiempoAgotado = true
        }
    }

    LaunchedEffect(tiempoAgotado) {
        if (tiempoAgotado) {
            if (settingsViewModel.rondaActual < settingsViewModel.rondasTotales) {
                settingsViewModel.cambiarDeRonda()
                settingsViewModel.cambiarPregunta()
                tiempoRestante = settingsViewModel.tiempoRonda
            } else {
                juegoTerminado = true
            }
            tiempoAgotado = false
            settingsViewModel.sumarPunto(0)
        }
    }

    if (juegoTerminado || settingsViewModel.rondaActual > settingsViewModel.rondasTotales) {
        val victoria = settingsViewModel.rondaActual > settingsViewModel.rondasTotales
        LaunchedEffect(Unit) {
            navController.navigate(Routes.Pantalla4.createRoute(victoria))
            settingsViewModel.resetRonda()
            settingsViewModel.resetearPuntos()
        }
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Round ${settingsViewModel.rondaActual}/${settingsViewModel.rondasTotales}",
                modifier = Modifier
                    .padding(vertical = 32.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = textPrimaryColor
            )

            Text(
                text = settingsViewModel.preguntaActual.preguntes,
                modifier = Modifier
                    .padding(top = 48.dp, bottom = 32.dp)
                    .fillMaxWidth(0.9f),
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium,
                textAlign = TextAlign.Center,
                color = textPrimaryColor
            )

            Row(
                modifier = Modifier
                    .padding(top = 24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        settingsViewModel.puntuacion(
                            settingsViewModel.preguntaActual.opcioA,
                            settingsViewModel.preguntaActual.respostaCorrecta
                        )
                        settingsViewModel.cambiarDeRonda()
                        settingsViewModel.cambiarPregunta()
                        tiempoRestante = settingsViewModel.tiempoRonda
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColors[0],
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 8.dp
                    )
                ) {
                    Text(
                        text = settingsViewModel.preguntaActual.opcioA,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Button(
                    onClick = {
                        settingsViewModel.puntuacion(
                            settingsViewModel.preguntaActual.opcioB,
                            settingsViewModel.preguntaActual.respostaCorrecta
                        )
                        settingsViewModel.cambiarDeRonda()
                        settingsViewModel.cambiarPregunta()
                        tiempoRestante = settingsViewModel.tiempoRonda
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColors[0],
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 8.dp
                    )
                ) {
                    Text(
                        text = settingsViewModel.preguntaActual.opcioB,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Row(
                modifier = Modifier
                    .padding(top = 16.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        settingsViewModel.puntuacion(
                            settingsViewModel.preguntaActual.opcioC,
                            settingsViewModel.preguntaActual.respostaCorrecta
                        )
                        settingsViewModel.cambiarDeRonda()
                        settingsViewModel.cambiarPregunta()
                        tiempoRestante = settingsViewModel.tiempoRonda
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColors[0],
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 8.dp
                    )
                ) {
                    Text(
                        text = settingsViewModel.preguntaActual.opcioC,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                        )
                }
                Button(
                    onClick = {
                        settingsViewModel.puntuacion(
                            settingsViewModel.preguntaActual.opcioD,
                            settingsViewModel.preguntaActual.respostaCorrecta
                        )
                        settingsViewModel.cambiarDeRonda()
                        settingsViewModel.cambiarPregunta()
                        tiempoRestante = settingsViewModel.tiempoRonda
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = buttonColors[0],
                        contentColor = Color.White
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp)
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    elevation = ButtonDefaults.buttonElevation(
                        defaultElevation = 4.dp,
                        pressedElevation = 8.dp
                    )
                ) {
                    Text(
                        text = settingsViewModel.preguntaActual.opcioD,
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold
                        )
                }
            }
            Row(
                modifier = Modifier
                    .padding(top = 70.dp)
            ) {
                CountDownTimer(tiempoRestante, settingsViewModel, progressColor = Color.Green, textColor = Color.Black)
            }
        }
    }
}

@Composable
fun CountDownTimer(tiempoRestante: Int, settingsViewModel: SettingsViewModel, progressColor: Color, textColor: Color) {
    var timeLeft by rememberSaveable { mutableIntStateOf(tiempoRestante) }

    LaunchedEffect(key1 = tiempoRestante) {
        timeLeft = tiempoRestante
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
        Text(
            text = "Time left: $timeLeft",
            color = textColor,
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(bottom = 8.dp)
            )
        LinearProgressIndicator(
            progress = timeLeft.toFloat() / settingsViewModel.tiempoRonda.toFloat(),
            color = progressColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp),
            trackColor = Color.LightGray,
            )
    }
}