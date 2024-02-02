package com.example.dados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
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
import com.example.dados.ui.theme.DadosTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DadosTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Dados()
                }
            }
        }
    }
}


@Composable
fun Dados() {
    var show by remember { mutableStateOf(false) }
    var dado1 by remember { mutableStateOf("1") }
    var dado2 by remember { mutableStateOf("1") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.tapestry),
                contentScale = ContentScale.FillBounds
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.title),
            contentDescription = "Título",
            modifier = Modifier.size(250.dp),
        )
        Button(
            onClick = { show = true },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red
            ),
        ) {
            Text(text = "ROLL THE DICE")
        }

        Row {

            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        if (!show) {
                            dado1 = RollDice()
                        }
                    }

            ) {
                Image(
                    painter = painterResource(id = R.drawable.dice_1),
                    contentDescription = "Dado 1"
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clickable {
                        if (!show) {
                            dado2 = RollDice()
                        }
                    }

            ) {
                Image(
                    painter = painterResource(id = R.drawable.dice_1),
                    contentDescription = "Dado 2"
                )
            }
        }
    }
}

@Composable
fun DadoClickable(value: String, onClick: () -> Unit, show: Boolean) {
    Box(modifier = Modifier

        .fillMaxWidth()
        .padding(16.dp)
        .clickable {
            if (!show) {
                onClick()
            }
        }
    ) {
        Image(
            painter = painterResource(id = diceImage(value)),
            contentDescription = "Dado $value"
        )
    }
}

fun RollDice(): String {
    return (Random.nextInt(1, 7)).toString()
}

fun diceImage(value: String): Int {
    return when (value) {
        "1" -> R.drawable.dice_1
        "2" -> R.drawable.dice_2
        "3" -> R.drawable.dice_3
        "4" -> R.drawable.dice_4
        "5" -> R.drawable.dice_5
        "6" -> R.drawable.dice_6
        else -> R.drawable.dice_1
    }
}

@Composable
fun showToast(message: String) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DadosTheme {
        Dados()
    }
}