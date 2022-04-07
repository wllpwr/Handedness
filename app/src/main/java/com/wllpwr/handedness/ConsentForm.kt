package com.wllpwr.handedness

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.BulletSpan
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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

class ConsentForm() : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val mContext = LocalContext.current
            Scaffold(
                topBar = { TopAppBar(title = { Text("Handedness Test", color = Color.Black, fontWeight = FontWeight.Bold) }, backgroundColor = Purple80) },
                content = {
                    HandednessTheme {
                        // A surface container using the 'background' color from the theme
                        Column(
                            horizontalAlignment = CenterHorizontally,
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background)
                                .verticalScroll(enabled = true, state = rememberScrollState())
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ConsentHeader()
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ConsentInfoList(applicationContext)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                ConsentEndInfoAndSignature(applicationContext)
                            }
                            Button(
                                onClick = {
                                    mContext.startActivity(Intent(mContext, SurveyForm::class.java))
                                },
                                colors = ButtonDefaults.buttonColors(Purple40),
                                modifier = Modifier.padding(bottom = 20.dp)
                            ) {
                                Text(
                                    text = "Continue",
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .padding(vertical = 10.dp)
                                )
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun ConsentHeader() {
    HandednessTheme {
        Column {
            Text(
                text = "The Effects of Handedness in Mobile Devices:",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
            Text(
                text = "Informed Consent Agreement",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
            Text(
                text = "Please read this consent agreement carefully before you decide to participate in the study.",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ConsentInfoList(context: Context) {
    val builder = SpannableStringBuilder()
    val infoList = listOf(
        "The purpose of the study is to observe the effects of handedness in the context of mobile touchscreen interfaces.",
        "You will be guided through a series of tasks in two different major sections, one for each hand. The first section will be for the first hand, which is randomly selected, and the second section for the other. Each task will be comprised of a given menu system that you must navigate through. Once you complete the navigation of one menu, then you will move on to the next until it is time to use your other hand. We will only be collecting a few fields of information that are relevant to our study and during the study we will only be measuring the time taken for each task and how many errors were made. You may also choose to exit the test at any point if you do not feel comfortable continuing.",
        "The study will require about 15 minutes of your time. This study will be composed of two main parts and you should only need a few minutes for each.",
        "There are no anticipated risks in this study.",
        "There are no direct benefits to you for participating in this research study. The study may help us better understand how people with different dominant hands interact with mobile interfaces while using a given hand. Furthermore, this could help improve the design of future applications when taking these findings into consideration.",
        "The information that you give in the study will be confidential.  Your name will not be collected or linked to the data.",
        "Your participation in the study is completely voluntary.",
        "You have the right to withdraw from the study at any time without penalty. Should you withdraw from the study, any information that we have collected will be removed before any analyses are made.",
        "If you want to withdraw from the study, tell the researcher who is present and leave the room. There is no penalty for withdrawing.",
        "There is no necessary debriefing involved with this study.",
        "You will receive no payment for participating in the study."
    )
    infoList.forEach { bulletPoint ->
        builder.append(
            bulletPoint + "\n\n",
            BulletSpan(120),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    HandednessTheme {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = builder.toString(),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun ConsentEndInfoAndSignature(context: Context) {
    val builder = SpannableStringBuilder()
    val infoList = listOf(
        "If you have questions about the study, contact:",
        "Joshua Bogin",
        "ITS, 194 Saint Paul Street",
        "Champlain College, Burlington, VT 05401.",
        "Telephone: (609) 410-1434",
        "David Kopec",
        "ITS, 317 Maple St",
        "Champlain College, Burlington, VT 05401.",
        "Telephone: (802) 651-5952",
        "If you have questions about your rights in the study, contact:",
        "Chair, Institutional Review Board for Champlain College",
        "163 South Willard Street",
        "Champlain College, P.O. Box 670",
        "Burlington, VT 05402",
        "Email: irb@champlain.edu",
        "Website: http://www.champlain.edu/academic-affairs-provost/institutional-review-board.html",
        "\n",
        "I agree to participate in the research study described above.",
        "\n",
        "Signature: _______________",
        "Date:  _____________",
        "\n",
        "You will receive a copy of this form for your records."
    )
    infoList.forEach { bulletPoint ->
        builder.append(
            bulletPoint + "\n",
            BulletSpan(120),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
    HandednessTheme {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = builder.toString(),
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            )
        }
    }
}