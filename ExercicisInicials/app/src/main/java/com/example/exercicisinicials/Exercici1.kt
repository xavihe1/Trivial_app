package com.example.exercicisinicials

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.exercicisinicials.ui.theme.ExercicisInicialsTheme

class Exercici1 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ExercicisInicialsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)
@Composable
fun Body() {
    var priceMenu by remember { mutableStateOf("") }
    var tip by remember { mutableStateOf("") }
    var show by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Tip calculator", fontSize = 28.sp, fontWeight = FontWeight.Bold)
        TextField(
            value = priceMenu,
            onValueChange = { priceMenu = it },
            label = { Text("Price menu") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        TextField(
            value = tip,
            onValueChange = { tip = it },
            label = { Text("Tip") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
        Button(onClick = {
            show = true
            result = calculateMenuFinalPrice(priceMenu, tip)
        }) {
            Text(text = "Calculate")
        }
        if (show) {
            Text(text = "The price menu is $result")
        }
    }
}

fun calculateMenuFinalPrice(priceMenu: String, tip: String): Int {
    val priceMenuInt = priceMenu.toInt()
    val tipInt = tip.toInt()
    val result = priceMenuInt + priceMenuInt * tipInt / 100
    return result
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ExercicisInicialsTheme {
        Body()
    }
}
