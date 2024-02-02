package com.example.hangmanapp

import androidx.compose.runtime.Composable
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun GameScreen(navController: NavController, selectedText: String) {

    var botones = listOf(
        listOf("A", "B", "C", "D", "E", "F"),
        listOf("G", "H", "I", "J", "K", "L"),
        listOf("M", "N", "Ñ", "O", "P", "Q"),
        listOf("R", "S", "T", "U", "V", "W"),
        listOf("X", "Y", "Z")
    )
    val palabrasEasy = listOf("PEZ", "PERRO", "ELEFANTE", "LIBRO", "RIO", "MANDARINA")
    val palabrasHard = listOf("METACRILATO", "ACETONA", "VENTRILOQUIA", "NITROGLICERINA", "RESTRICCION", "ESPARADRAPO")
    var intentos by remember { mutableStateOf("") }
    var numImagen by remember { mutableStateOf(0) }
    val dificultad by remember{ mutableStateOf(selectedText) }
    val palabrasJuego = when(dificultad) {
        "Easy"-> palabrasEasy
        else -> palabrasHard
    }
    val palabraRandom by remember { mutableIntStateOf((palabrasJuego.indices).random()) }
    val palabraEscogida by remember {mutableStateOf(palabrasJuego[palabraRandom])}
    var palabraEscondida by remember { mutableStateOf("_".repeat(palabraEscogida.length)) }
    var victoria = false

    val imagenHangman = when (numImagen) {
        0 -> R.drawable.fallo0
        1 -> R.drawable.fallo1
        2 -> R.drawable.fallo2
        3 -> R.drawable.fallo3
        4 -> R.drawable.fallo4
        5 -> R.drawable.fallo5
        else -> R.drawable.fallo6
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        botones.forEach { fila ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                fila.forEach { boton ->
                    Button(
                        onClick = { /* acciones a realizar */ },
                        modifier = Modifier.padding(2.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color.Blue
                        )
                    ) {
                        Text(boton)
                    }
                }
            }
            var inicioJuego = 0
            var finalJuego = 5
            val palabraEscondidaNueva = palabraEscondida.toCharArray()
            var palabraEscogidaNueva = palabraEscogida.uppercase().toCharArray()

            repeat(6) {
                Row {
                    for (i in inicioJuego..finalJuego) {
                        var correcto = false
                        val letter = botones[i]
                        var colorDeLasTeclas by remember { mutableStateOf(Color.Blue) }

                        Box(
                            modifier = Modifier
                                .padding(2.dp)
                                .width(40.dp)
                                .height(40.dp)
                                .clickable {
                                    for (letra in palabraEscogida.indices) {
                                        if (letter == palabraEscogida[letra]) {
                                            correcto = true
                                            palabraEscondidaNueva[letra] = letter
                                        }
                                    }
                                    palabraEscondida = String(palabraEscondidaNueva)
                                    if (!correcto) {
                                        colorDeLasTeclas = Color.Red
                                        numImagen++
                                    } else {
                                        colorDeLasTeclas = Color.Green
                                    }
                                }) {
                            Text(
                                text = "$letter",
                                modifier = Modifier
                                    .align(Alignment.Center))
                        }
                    }
                    inicioJuego = finalJuego + 1
                    if (finalJuego + 5 < botones.lastIndex) {
                        finalJuego += 6
                    } else {
                        finalJuego = botones.lastIndex
                    }
                }
            }
        }
        if (palabraEscondida == palabraEscondida) {
            victoria = true
            navController.navigate("ResultScreen")
        } else if (imagenHangman == R.drawable.fallo6) {
            navController.navigate("ResultScreen")
        }
    }
}
