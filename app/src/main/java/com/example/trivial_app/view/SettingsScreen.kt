package com.example.trivial_app.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trivial_app.R
import com.example.trivial_app.viewModel.SettingsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(navController: NavController, settingsViewModel: SettingsViewModel) {
    val menuButton = colorResource(id = R.color.azul)
    val buttonTextColor = colorResource(id = R.color.white)
    val radioSelected = colorResource(id = R.color.azul)
    val switchTrackChecked = Color(0xFF81C784)
    val sliderActive = colorResource(id = R.color.azul)
    val sliderInactive = Color.LightGray

    var selectedOption = settingsViewModel.rondasTotales
    var encendido = settingsViewModel.modoOscuro
    var dificultatSeleccionada by remember { mutableStateOf(settingsViewModel.difficulty) }

    Column(
        modifier = Modifier
            .padding(horizontal = 24.dp, vertical = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            val dificultades = listOf("Easy", "Normal", "Hard")
            Text(text = "Difficulty: ")

            OutlinedTextField(
                value = dificultatSeleccionada,
                onValueChange = { settingsViewModel.selectedText = it },
                enabled = false,
                readOnly = true,
                modifier = Modifier
                    .clickable { settingsViewModel.expanded = true }
                    .weight(1f),
                shape = RoundedCornerShape(12.dp)
            )
            DropdownMenu(
                expanded = settingsViewModel.expanded,
                onDismissRequest = { settingsViewModel.expanded = false },
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                dificultades.forEach { label ->
                    DropdownMenuItem(
                        text = { Text(text = label, fontSize = 18.sp, fontWeight = FontWeight.SemiBold) },
                        onClick = {
                            dificultatSeleccionada = label
                            settingsViewModel.expanded = false
                            settingsViewModel.cambiarDificultat(dificultatSeleccionada)
                            settingsViewModel.cambiarPregunta()
                        })
                    }
                }
            }
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(text = "Rounds: ",
                    modifier = Modifier
                        .padding(end = 75.dp))
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    val numRondas = listOf("5", "10", "15")
                    numRondas.forEach { label ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            RadioButton(
                                selected = selectedOption.toString() == label,
                                onClick = {
                                    settingsViewModel.rondasTotales = label.toInt() },
                                colors = RadioButtonDefaults.colors(
                                    selectedColor = radioSelected,
                                    unselectedColor = Color.Gray
                                )
                            )
                            Text(
                                text = label,
                                modifier = Modifier.padding(start = 8.dp)
                                )
                        }
                    }
                }
            }
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Time per round: ",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
                )

            Slider(
                value = settingsViewModel.sliderValue,
                onValueChange = {
                    settingsViewModel.sliderValue = it
                    settingsViewModel.cambiarTiempoRonda(it.toInt())
                                },
                onValueChangeFinished = { settingsViewModel.finishValue = settingsViewModel.sliderValue.toString() },
                valueRange = 5f..15f,
                steps = 9,
                colors = SliderDefaults.colors(
                    thumbColor = sliderActive,
                    activeTrackColor = sliderActive,
                    inactiveTrackColor = sliderInactive,
                    activeTickColor = Color.Transparent,
                    inactiveTickColor = Color.Transparent
                ),
                modifier = Modifier.fillMaxWidth()
            )

        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Dark mode: ",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
            )

            Switch(
                checked = encendido,
                onCheckedChange = {
                    encendido = it
                    settingsViewModel.botonModoOscuro()
                                  },
                colors = SwitchDefaults.colors(
                    checkedThumbColor = Color.White,
                    checkedTrackColor = switchTrackChecked,
                    uncheckedThumbColor = Color.White,
                    uncheckedTrackColor = Color.Gray
                )
            )
        }
        Row(modifier = Modifier.padding(50.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(
                onClick = { navController.navigate("MenuScreen") },
                colors = ButtonDefaults.buttonColors(
                    containerColor = menuButton,
                    contentColor = buttonTextColor
                ),
                modifier = Modifier
                    .width(200.dp)
                    .height(56.dp)
                    .padding(vertical = 8.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(
                    defaultElevation = 4.dp,
                    pressedElevation = 8.dp
                )
            ) {
                Text(
                    text = "Return to menu",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                    )
            }
        }
    }
}