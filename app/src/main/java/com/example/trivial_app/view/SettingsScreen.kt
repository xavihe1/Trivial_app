package com.example.trivial_app.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.trivial_app.R
import com.example.trivial_app.viewModel.SettingsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController, settingsViewModel: SettingsViewModel) {
    val dificultades = listOf("Easy", "Normal", "Hard")
    var selectedOption by remember { mutableIntStateOf(settingsViewModel.rondasTotales) }
    var modoOscuro by remember { mutableStateOf(settingsViewModel.modoOscuro) }

    Column(
        modifier = Modifier
            .paint(
                painterResource(id = R.drawable.fondo_pantalla),
                contentScale = ContentScale.FillBounds
            )
            .padding(top = 60.dp, start = 5.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Row(modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Difficulty: ")
        
            OutlinedTextField(
                value = settingsViewModel.selectedText,
                onValueChange = { settingsViewModel.selectedText = it },
                enabled = false,
                readOnly = true,
                modifier = Modifier
                    .clickable { settingsViewModel.expanded = true }
            )
            DropdownMenu(
                expanded = settingsViewModel.expanded,
                onDismissRequest = { settingsViewModel.expanded = false },
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                dificultades.forEach { dificultat ->
                    DropdownMenuItem(
                        text = { Text(text = dificultat) },
                        onClick = {
                            settingsViewModel.expanded = false
                            settingsViewModel.selectedText = dificultat
                            settingsViewModel.cambiarDificultat(dificultat)
                        })
                    }
                }
            }
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "Rounds: ", modifier = Modifier.padding(end = 75.dp))
                Column {
                    val numRondas = listOf("5", "10", "15")
                    numRondas.forEach { label ->
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton(
                                selected = selectedOption.toString() == label,
                                onClick = {
                                    selectedOption = label.toInt()
                                    settingsViewModel.cambiarDeRonda(selectedOption) },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = Color.Blue,
                                    unselectedColor = Color.Gray
                                )
                            )
                            Text(text = label)
                        }
                    }
                }
            }
        Row(modifier = Modifier.padding(35.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Time per round: ")

            Slider(
                value = settingsViewModel.sliderValue,
                onValueChange = { settingsViewModel.sliderValue = it },
                onValueChangeFinished = { settingsViewModel.finishValue = settingsViewModel.sliderValue.toString() },
                valueRange = 0f..15f,
                steps = 14,
                colors = SliderDefaults.colors(
                    activeTickColor = Color.Transparent,
                    inactiveTickColor = Color.Transparent)
            )

        }
        Row(modifier = Modifier.padding(35.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Dark mode: ", modifier = Modifier.padding(end = 130.dp))

            Switch(
                checked = settingsViewModel.modoOscuro,
                onCheckedChange = { modoOscuro = !settingsViewModel.modoOscuro },
                colors = SwitchDefaults.colors(
                    uncheckedThumbColor = Color.Gray,
                    checkedThumbColor = Color.Green
                )
            )
        }
        Row(modifier = Modifier.padding(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.navigate("MenuScreen") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.DarkGray
                )
            ) {
                Text(text = "Return to menu")
            }
        }
    }
}