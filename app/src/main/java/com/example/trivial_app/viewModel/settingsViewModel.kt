package com.example.trivial_app.viewModel

import androidx.compose.runtime.getValue

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.trivial_app.model.DificultatPreguntes
import com.example.trivial_app.model.preguntesDificil
import com.example.trivial_app.model.preguntesFacil
import com.example.trivial_app.model.preguntesNormal

class SettingsViewModel: ViewModel() {
    var selectedText by mutableStateOf("")
    var expanded by mutableStateOf(false)
    var sliderValue by mutableFloatStateOf(0f)
    var finishValue by mutableStateOf("")


    var rondaActual by mutableIntStateOf(0)
    var rondasTotales by mutableIntStateOf(5)
    var round by mutableIntStateOf(1)
    fun cambiarDeRonda(nuevaRonda: Int) {
        round = nuevaRonda
    }


    var difficulty: String by mutableStateOf("Easy")
    fun cambiarDificultat(nuevaDificultat: String) {
        difficulty = nuevaDificultat
    }

    fun dificultatPreguntes(): List<DificultatPreguntes> {
        return when(difficulty) {
            "Easy" -> preguntesFacil
            "Normal" -> preguntesNormal
            "Hard" -> preguntesDificil
            else -> listOf()
        }
    }

    var preguntaActual by mutableStateOf(dificultatPreguntes()[round - 1])

    fun cambiarPregunta() {
        val listaPreguntas = dificultatPreguntes()
        preguntaActual = listaPreguntas[round - 1]
    }


    var modoOscuro by mutableStateOf(false)
    fun botonModoOscuro() {
        modoOscuro = !modoOscuro
    }


    var puntos by mutableStateOf(0)
    fun sumarPunto(sumar: Int) {
        puntos += sumar
    }

    fun resetearPuntos() {
        puntos = 0
    }

    fun puntuacion(botonSeleccionado: String, botonCorrecto: String) {
        if (botonSeleccionado[0] == botonCorrecto.first()) {
            sumarPunto(1)
        } else {
            sumarPunto(-1)
        }
    }
}