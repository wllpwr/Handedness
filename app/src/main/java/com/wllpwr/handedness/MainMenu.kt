package com.wllpwr.handedness

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wllpwr.handedness.ui.theme.HandednessTheme
import com.wllpwr.handedness.ui.theme.Purple40

class MainMenu : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HandednessTheme {
                // A surface container using the 'background' color from the theme
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceEvenly,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    DefaultPreview2()
                }
            }
        }
    }
}



@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    val mContext = LocalContext.current
    val intent = Intent(mContext, Directions::class.java)
    HandednessTheme {

    }

    androidx.compose.material3.Button(
        modifier = Modifier
            .padding(10.dp),
        colors = ButtonDefaults.buttonColors(Purple40),
        onClick = {
            intent.putExtra("direction", "test1")
            mContext.startActivity(intent)
        }) {
        androidx.compose.material3.Text(
            text = "Burger Menu Navigation",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(vertical = 10.dp)
        )
    }
    androidx.compose.material3.Button(
        modifier = Modifier
            .padding(10.dp),
        colors = ButtonDefaults.buttonColors(Purple40),
        onClick = {
            intent.putExtra("direction", "test2")
            mContext.startActivity(intent)
        }) {
        androidx.compose.material3.Text(
            text = "Basic Menu Navigation",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(vertical = 10.dp)
        )
    }
    androidx.compose.material3.Button(
        modifier = Modifier
            .padding(10.dp),
        colors = ButtonDefaults.buttonColors(Purple40),
        onClick = {
            intent.putExtra("direction", "test3")
            mContext.startActivity(intent)
        }) {
        androidx.compose.material3.Text(
            text = "Bottom Navigation Bar",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            modifier = Modifier
                .padding(vertical = 10.dp)
        )
    }
}