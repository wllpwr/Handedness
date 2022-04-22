package com.wllpwr.handedness.ui.theme

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wllpwr.handedness.MainActivity

class EndScreen : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            Scaffold(
                topBar = { TopAppBar(title = { Text("Handedness Test", color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold) }, backgroundColor = Purple80) },
                content = { GoodbyePrompt() }
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GoodbyePrompt() {
    val mContext = LocalContext.current

    HandednessTheme {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "You Have completed the test! Please let the one of the Host know. " +
                        "You may now put the phone down and walk away",
                fontSize = 32.sp,
                lineHeight = 45.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp)
            )

            Button(
                onClick = {
                    mContext.startActivity(Intent(mContext, MainActivity::class.java))
                },
                colors = ButtonDefaults.buttonColors(Purple40),
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(
                    text = "Begin!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }
        }
    }
}