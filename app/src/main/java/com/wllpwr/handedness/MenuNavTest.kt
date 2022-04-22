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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wllpwr.handedness.ui.theme.HandednessTheme

class MenuNavTest : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataObj.addData("MENU NAV TEST")
        val iteration = intent.getIntExtra("iteration", 0)
        Timer.startTimer()
        setContent {
            HandednessTheme {
                Surface(color = MaterialTheme.colors.background) {
                    ScrollableColumnDemo(iteration)
                }
            }
        }
    }
}

@Composable
fun ScrollableColumnDemo(iteration: Int) {

    val mContext = LocalContext.current
    rememberScrollState()
    val intent = Intent(mContext, MenuNavTest::class.java)
    var itCount = iteration
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
    ) {
        val random = (1..10).random()
        for (i in 1..10) {
            if (random == i) {
                if (itCount != 9) {
                    Text(
                        text = "Item $i",
                        fontSize = 16.sp,

                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                itCount++
                                intent.putExtra("iteration", itCount)

                                Toast
                                    .makeText(
                                        mContext,
                                        "Test $itCount",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                                Timer.endTimer()
                                DataObj.addData("TIME")
                                DataObj.addData(
                                    Timer
                                        .getTime()
                                        .toString()
                                )
                                DataObj.addData("ERR")
                                DataObj.addData(
                                    DataObj
                                        .getErrorCount()
                                        .toString()
                                )
                                DataObj.completeTest()
                                mContext.startActivity(intent)
                            }
                            .background(color = Color.Red),
                        textAlign = TextAlign.Center,
                    )
                } else {
                    mContext.startActivity(Intent(mContext, MainMenu::class.java))
                    Toast
                        .makeText(
                            mContext,
                            "Test fully completed!",
                            Toast.LENGTH_SHORT
                        )
                        .show()
                }
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
                            DataObj.errorHit()
                        },
                    textAlign = TextAlign.Center,
                )
            }
            Divider(color = Color.Gray, thickness = 0.5.dp)

        }


    }
}