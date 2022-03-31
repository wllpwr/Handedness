package com.wllpwr.handedness

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.wllpwr.handedness.ui.theme.HandednessTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HandednessTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) { DefaultPreview() }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HandednessTheme {
        Text(text = "Hi! This is a handedness testing app that will determine your ability to navigate menus when using different hands. Let's get some info from you before we start.", textAlign = TextAlign.Center)

        Text(text = "", textAlign = TextAlign.Center)
    }
}

