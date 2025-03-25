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
    var sliderValue by mutableFloatStateOf(10f)
    var finishValue by mutableStateOf("10")
    var rondaActual by mutableIntStateOf(1)
    var rondasTotales by mutableIntStateOf(5)


    //Selector de dificultats
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


    //Numero de rondes
    var preguntaActual by mutableStateOf(dificultatPreguntes()[rondaActual - 1])

    fun cambiarDeRonda() {
        rondaActual++
    }

    fun resetRonda() {
        rondaActual = 1
    }

    fun cambiarPregunta() {
        val listaPreguntas = dificultatPreguntes()
        if (rondaActual <= rondasTotales) {
            preguntaActual = listaPreguntas[rondaActual - 1]
        }
    }


    //Slider temps per ronda
    var tiempoRonda by mutableIntStateOf(10)
    fun cambiarTiempoRonda(nuevoTiempo: Int) {
        tiempoRonda = nuevoTiempo
    }


    //Puntuació joc
    var puntos by mutableStateOf(0)
    fun sumarPunto(sumar: Int) {
        puntos += sumar
        if (puntos < 0) {
            puntos = 0
        }
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


    //Modo oscuro
    var modoOscuro by mutableStateOf(false)
    fun botonModoOscuro() {
        modoOscuro = !modoOscuro
    }
}