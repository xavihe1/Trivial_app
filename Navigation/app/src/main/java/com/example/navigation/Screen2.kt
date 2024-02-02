package com.example.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun Screen2(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        var resposta by rememberSaveable { mutableStateOf("") }
        var status1 by rememberSaveable { mutableStateOf(false) }
        var status2 by rememberSaveable { mutableStateOf(false) }
        Row(Modifier.wrapContentSize()) {
            RadioButton(
                selected = status1,
                onClick = {
                    status1 = !status1
                    if (status1) {
                        status2 = false
                    }
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Blue,
                    unselectedColor = Color.Red
                )
            )
            Text("Hola", Modifier.align(CenterVertically))
            resposta = "Hola"
        }
        Row(Modifier.wrapContentSize()) {
            RadioButton(
                selected = status2,
                onClick = {
                    status2 = !status2
                    if (status2) {
                        status1 = false
                    }
                },
                colors = RadioButtonDefaults.colors(
                    selectedColor = Color.Blue,
                    unselectedColor = Color.Red
                )
            )
            Text("Adéu", Modifier.align(CenterVertically))
            resposta = "Adéu"
        }
        var sliderValue by remember { mutableStateOf(0f) }
        var finishValue by remember { mutableStateOf("") }
        Slider(
            value = sliderValue,
            onValueChange = { sliderValue = it },
            onValueChangeFinished = { finishValue = sliderValue.toInt().toString() },
            valueRange = 18f..45f,
            steps = 27
        )
        Text(text = finishValue)
        Button(onClick = {
            navController.navigate(Routes.Pantalla3.createRoute("juan", finishValue, resposta))
        }) {
            Text("GO")
        }
    }
}
