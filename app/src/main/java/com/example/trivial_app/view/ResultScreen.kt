package com.example.trivial_app.view

import android.content.Intent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavController
import com.example.trivial_app.R
import com.example.trivial_app.viewModel.SettingsViewModel

@Composable
fun ResultScreen(navController: NavController, settingsViewModel: SettingsViewModel, victoria: Boolean) {
    val buttonColor = colorResource(id = R.color.azul)
    val buttonTextColor = colorResource(id = R.color.white)
    val puntos = settingsViewModel.puntos
    Column(
        modifier = Modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
            Text(
                text = "Your score is $puntos",
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.SansSerif
            )
        Spacer(modifier = Modifier.height(26.dp))

        Row(modifier = Modifier.padding(top = 50.dp)) {
            Share(text = "Share")
        }
        
        Button(
            modifier = Modifier
                .width(200.dp)
                .height(56.dp)
                .padding(vertical = 8.dp),
            onClick = { navController.navigate("MenuScreen") },
            colors = ButtonDefaults.buttonColors(
                containerColor = buttonColor,
                contentColor = buttonTextColor
            ),
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


@Composable
fun Share(text: String) {
    val buttonShare = colorResource(id = R.color.teal_700)
    val shareColor = colorResource(id = R.color.white)
    val context = LocalContext.current
    val sendIntent = Intent(Intent.ACTION_SEND).apply {
        putExtra(Intent.EXTRA_TEXT, text)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    Button(
        onClick = { startActivity(context, shareIntent, null) },
        colors = ButtonDefaults.buttonColors(containerColor = buttonShare, contentColor = shareColor),
        modifier = Modifier.width(200.dp).height(56.dp).padding(vertical = 8.dp),
        shape = RoundedCornerShape(12.dp),
        elevation = ButtonDefaults.buttonElevation(
            defaultElevation = 4.dp,
            pressedElevation = 8.dp
        )
    ) {
        Icon(imageVector = Icons.Default.Share, contentDescription = "Share")
        Text(
            text = "Share",
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold
            )
    }
}
