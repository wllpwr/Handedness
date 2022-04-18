package com.wllpwr.handedness

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wllpwr.handedness.ui.theme.HandednessTheme

class MenuNavTest3 : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HandednessTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ScrollableColumnDemo3()
                }
            }
        }
    }
}

@Composable
@Preview
fun ScrollableColumnDemo3() {

    val mContext = LocalContext.current
    rememberScrollState()
    val intent = Intent(mContext, MainMenu::class.java)

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        val random = (1..10).random()
        for (i in 1..10) {
            if (random == i) {
                Text(
                    text = "Item $i",
                    fontSize = 16.sp,

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            mContext.startActivity(intent)
                            Toast
                                .makeText(
                                    mContext,
                                    "Test complete!",
                                    Toast.LENGTH_SHORT
                                )
                                .show()
                        }
                        .background(color = Color.Red),
                    textAlign = TextAlign.Center,
                )
            } else {
                Text(
                    text = "Item $i",
                    fontSize = 16.sp,

                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            Toast
                                .makeText(
                                    mContext,
                                    "Wrong item!",
                                    Toast.LENGTH_SHORT
                                )
                                .show()

                        },
                    textAlign = TextAlign.Center,
                )
            }
            Divider(color = Color.Gray, thickness = 0.5.dp)

        }


    }
}