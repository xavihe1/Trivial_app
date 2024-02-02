package com.example.navegaciopantalles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Screen2(){
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.Green)) {
        Text(text = "Pantalla 1", modifier = Modifier.align(Alignment.Center))
    }
}



