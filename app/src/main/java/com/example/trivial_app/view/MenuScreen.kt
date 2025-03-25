package com.example.trivial_app.view

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.trivial_app.R
import com.example.trivial_app.navigation.Routes
import com.example.trivial_app.navigation.settingsViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuScreen(navController: NavController) {
    var selectedText by remember { mutableStateOf("Easy") }
    var expanded by remember { mutableStateOf(false) }
    val difficulty = listOf("Easy", "Normal", "Hard")
    val playButtonColor = colorResource(id = R.color.play_button)
    val settingsButtonColor = colorResource(id = R.color.settings_button)
    val buttonTextColor = colorResource(id = R.color.white)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.menu_image),
            contentDescription = null,
            modifier = Modifier
                .padding(70.dp)
                .size(300.dp)
        )

        Button(
            onClick = {
                navController.navigate(Routes.Pantalla3.route)
                settingsViewModel.resetRonda()
                      },
            colors = ButtonDefaults.buttonColors(
                containerColor = playButtonColor,
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
                text = "PLAY",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold
                )
        }

        Button(
            onClick = { navController.navigate(Routes.Pantalla5.route) },
            colors = ButtonDefaults.buttonColors(
                containerColor = settingsButtonColor,
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
                text = "SETTINGS",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
                )
        }
    }
}