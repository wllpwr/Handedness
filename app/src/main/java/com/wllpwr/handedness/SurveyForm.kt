package com.wllpwr.handedness

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.RadioButton
import androidx.compose.material.TextField
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.wllpwr.handedness.ui.theme.HandednessTheme
import com.wllpwr.handedness.ui.theme.Purple80

class SurveyForm() : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @RequiresApi(Build.VERSION_CODES.P)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = {
                    TopAppBar(title = {
                        Text(
                            "Handedness Test",
                            color = Color.Black,
                            fontWeight = FontWeight.Bold
                        )
                    }, backgroundColor = Purple80)
                },
                content = {
                    HandednessTheme {
                        // A surface container using the 'background' color from the theme
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.background)
                                .verticalScroll(enabled = true, state = rememberScrollState())
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                QuestionnaireForm(applicationContext)
                            }
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun QuestionnaireForm(context: Context) {
    val radioAgeOptions = listOf("Yes", "No")
    val radioHandOptions = listOf("Left-Handed", "Right-Handed", "Ambidextrous")

    var numHours by remember { mutableStateOf("") }
    var hoursHasError by remember { mutableStateOf(false) }
    var numHoursLabel by remember { mutableStateOf("Enter the number of hours") }

    val mContext = LocalContext.current

    HandednessTheme {
        Column {
            Text(
                text = "Hi! This is a handedness testing app that will determine your ability to navigate menus when using different hands. Let's get some info from you before we start.",
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(10.dp)
            )

            Box(Modifier.height(10.dp)) {}

            Text(
                text = "Are you 18 years or older?",
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(10.dp)
            )
            CreateRadioAgeGroup(context, radioAgeOptions)

            Text(
                text = "Are you left-handed, right-handed or ambidextrous?",
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(10.dp)
            )
            CreateRadioAgeGroup(context, radioHandOptions)

            Box(Modifier.height(10.dp)) {}

            Text(
                text = "On average, how much time do you spend using a phone daily?",
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(10.dp)
            )
            TextField(
                value = numHours,
                isError = hoursHasError,
                label = { Text(text = numHoursLabel) },
                modifier = Modifier.padding(10.dp),
                onValueChange = { value -> numHours = value },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Button(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(10.dp),
                onClick = {
                    Toast.makeText(
                        context,
                        "Form: input submitted!",
                        Toast.LENGTH_SHORT
                    ).show()
                    mContext.startActivity(Intent(mContext, MainMenu::class.java))
                }) {
                Text("Submit")
            }
        }
    }
}

// Help From: https://www.geeksforgeeks.org/radiobuttons-in-android-using-jetpack-compose/
@Composable
fun CreateRadioAgeGroup(context: Context, radioOptions: List<String>) {
    val (selectedOption, onOptionSelected) = remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 130.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column {
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(height = 36.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = { onOptionSelected(text) }
                        )
                        .padding(horizontal = 10.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        modifier = Modifier
                            .padding(all = Dp(value = 5F))
                            .fillMaxHeight()
                            .align(Alignment.CenterVertically),
                        onClick = {
                            onOptionSelected(text)
                            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
                        }
                    )
                    Text(
                        text = text,
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .fillMaxHeight()
                            .align(Alignment.CenterVertically)
                    )
                }
            }
        }
    }
}