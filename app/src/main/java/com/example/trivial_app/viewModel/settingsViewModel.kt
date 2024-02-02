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
    var round by mutableIntStateOf(0)


    var difficulty: String by mutableStateOf("Easy")



    var rondas by mutableIntStateOf(0)
    fun numRondas(numero: Int) {
        rondas = numero
    }

    var tiempo by mutableIntStateOf(0)
    fun actualizarTiempo(reset: Int) {
        tiempo = reset
    }


    var modoOscuro by mutableStateOf(false)
    fun botonModoOscuro(encendido: Boolean) {
        modoOscuro = encendido
    }


    var totalDeRondas by mutableIntStateOf(0)
    fun cambiarDeRonda(nuevaRonda: Int) {
        totalDeRondas = nuevaRonda
    }


    fun cambiarDificultat(nuevaDificultat: String) {
        difficulty = nuevaDificultat
    }


    var preguntaActual by mutableStateOf(dificultatPreguntes()[round - 1])


    fun dificultatPreguntes(): List<DificultatPreguntes> {
        return when(difficulty) {
            "Easy" -> preguntesFacil
            "Normal" -> preguntesNormal
            "Hard" -> preguntesDificil
            else -> listOf()
        }
    }

    fun cambiarPregunta() {
        val listaPreguntas = dificultatPreguntes()
        preguntaActual = listaPreguntas[round - 1]
    }


}