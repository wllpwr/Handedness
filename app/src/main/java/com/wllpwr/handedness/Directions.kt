package com.wllpwr.handedness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wllpwr.handedness.ui.theme.HandednessTheme

class Directions : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val direction = intent.getStringExtra("direction")
        setContent {
            HandednessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ShowDirection(direction)
                }
            }
        }
    }
}

@Composable
fun ShowDirection(direction: String?) {
    val mContext = LocalContext.current
    HandednessTheme {
        println(direction)
        when (direction) {
            "test1" -> {
                androidx.compose.material3.Text(
                    text = "In this test, you will open the hamburger menu, and tap the highlighted item.",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }
            "test2" -> {
                androidx.compose.material3.Text(
                    text = "In this test, you will traverse through a series of pages, and tap the highlighted item.",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }
            "test3" -> {
                androidx.compose.material3.Text(
                    text = "In this test, you will traverse the bottom navigation bar, and tap on the settings menu of the profile page.",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }

        }
    }
}