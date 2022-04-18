package com.wllpwr.handedness

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wllpwr.handedness.ui.theme.HandednessTheme
import com.wllpwr.handedness.ui.theme.Purple40

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
    val hand = arrayOf("LEFT", "RIGHT").random()
    val testActivityArray = arrayOf(
        Intent(mContext, BurgerTest::class.java),
        Intent(mContext, MenuNavTest::class.java),
        Intent(mContext, BottomNavTest::class.java)
    )
    lateinit var chosenTest: Intent
    HandednessTheme {
        println(direction)
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize()
                .background(androidx.compose.material3.MaterialTheme.colorScheme.background)
                .verticalScroll(enabled = true, state = rememberScrollState())
        ) {
            when (direction) {
                "test1" -> {
                    androidx.compose.material3.Text(
                        text = "In this test, you will open the hamburger menu, and tap the highlighted item.",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(25.dp)

                    )
                    chosenTest = testActivityArray[0]
                }
                "test2" -> {
                    androidx.compose.material3.Text(
                        text = "In this test, you will traverse through a series of pages, and tap the highlighted item.",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(25.dp)
                    )
                    chosenTest = testActivityArray[1]
                }
                "test3" -> {
                    androidx.compose.material3.Text(
                        text = "In this test, you will traverse the bottom navigation bar, and tap on the settings menu of the profile page.",
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .padding(25.dp)

                    )
                    chosenTest = testActivityArray[2]
                }
                else -> {
                    println("How?")
                }

            }
            androidx.compose.material3.Text(
                text = "Please ensure that you only use your $hand hand for this test.",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(25.dp)
                    .padding(bottom = 50.dp)
            )
            androidx.compose.material3.Button(
                modifier = Modifier
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(Purple40),
                onClick = {
                    DataObj.addData("hand")
                    DataObj.addData(hand)
                    mContext.startActivity(chosenTest)

                }) {
                androidx.compose.material3.Text(
                    text = "Proceed",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }
        }
    }
}