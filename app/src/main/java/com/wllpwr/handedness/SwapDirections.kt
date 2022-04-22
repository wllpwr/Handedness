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
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wllpwr.handedness.ui.theme.HandednessTheme
import com.wllpwr.handedness.ui.theme.Purple40
import com.wllpwr.handedness.ui.theme.Purple80

class SwapDirections : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val direction = intent.getStringExtra("testNum")
        val hand = intent.getStringExtra("hand")

        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(
                            "Handedness Test",
                            color = Color.Black,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }, backgroundColor = Purple80)
                },
                content =
                {
                    HandednessTheme {
                        // A surface container using the 'background' color from the theme
                        Surface(
                            modifier = Modifier.fillMaxSize(),
                            color = MaterialTheme.colors.background
                        ) {
                            ShowSwapDirection(direction, hand)
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun ShowSwapDirection(direction: String?, hand: String?) {
    val mContext = LocalContext.current
    val testActivityArray = arrayOf(
        Intent(mContext, BurgerTest::class.java),
        Intent(mContext, MenuNavTest::class.java),
        Intent(mContext, BottomNavTest::class.java)
    )
    lateinit var currentTest: Intent
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
            print(hand)
            val swapHand = if (hand == "LEFT")
                "RIGHT"
            else
                "LEFT"

            Text(
                text = "Great! Now swap to your $swapHand hand.",
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(25.dp)

            )

            when (direction) {
                "test1" -> currentTest = testActivityArray[0]
                "test2" -> currentTest = testActivityArray[1]
                "test3" -> currentTest = testActivityArray[2]
                else -> {
                    println("ERROR: Incorrect Test")
                }
            }

            Button(
                modifier = Modifier
                    .padding(10.dp),
                colors = ButtonDefaults.buttonColors(Purple40),
                onClick = {
                    DataObj.addData("hand")
                    DataObj.addData(swapHand)

                    currentTest.putExtra("hand", swapHand)
                    currentTest.putExtra("isFirstTest", false)
                    mContext.startActivity(currentTest)

                }) {
                Text(
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