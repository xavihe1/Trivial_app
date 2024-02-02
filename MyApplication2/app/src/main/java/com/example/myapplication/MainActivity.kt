package com.example.myapplication

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MyApplicationTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyCalculator()
                }
            }
        }
    }
}

@Composable
fun MyCalculator() {
    var operator1 = remember { mutableStateOf("0") }
    var operator2 = remember { mutableStateOf("0") }
    var selectedOperation by remember { mutableStateOf("Suma") }
    var show by remember { mutableStateOf(false) }
    var showToast by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("The greatest calculator", fontSize = 24.sp)
        TextField(value = operator1, onValueChange = { operator1 = it})
        TextField(value = operator2, onValueChange = { operator2 = it})
        MyDropdownMenu(selectedOperation, { selectedOperation = it }) {

        }
        Button(onClick = {
            if (selectedOperation == "Divisió" && operator2 == "0") {
                showToast = true
            } else {
                show = true
                result = makeOperation(operator1, operator2, selectedOperation)
            }
        }) {
            Text(text = "Calcular")
        }
        if (show) {
            Text(text = "El resultat és $result")
        }
        if (showToast) {
            Toast.makeText(LocalContext.current, "No pots dividir entre 0", Toast.LENGTH_SHORT).show()
        }
    }
}

fun makeOperation(operator1: String, operator2: String, operator: String): Int {
    return when (operator) {
        "Suma" -> operator1.toInt() + operator2.toInt()
        "Resta" -> operator1.toInt() - operator2.toInt()
        "Multiplicació" -> operator1.toInt() * operator2.toInt()
        else -> operator1.toInt() / operator2.toInt()
    }
}

@Composable
fun MyDropdownMenu(selectedOperation: String, OnClick: (String) -> Unit) {
    var expanded by remember { mutableStateOf(false) }
    val operations = listOf("Suma", "Resta", "Multiplicació", "Divisió")
    OutlinedTextField(
        value = selectedOperation,
        onValueChange = { OnClick(it) },
        enabled = false,
        readOnly = true,
        modifier = Modifier
            .clickable { expanded = true }
    )
    DropdownMenu(
        expanded = expanded,
        onDismissRequest = { expanded = false },
    ) {
        operations.forEach { operation ->
            DropdownMenuItem(text = { Text(text = operation) }, onClick = {
                expanded = false
                selectedOperation = operation
            })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MyApplicationTheme {
        MyCalculator()
    }
}