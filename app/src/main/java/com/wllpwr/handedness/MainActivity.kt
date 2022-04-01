package com.wllpwr.handedness

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
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
                ) { QuestionnaireForm(applicationContext) }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    HandednessTheme {
        Text(
            text = "Hi! This is a handedness testing app that will determine your ability to navigate menus when using different hands. Let's get some info from you before we start.",
            textAlign = TextAlign.Center
        )

        Text(text = "", textAlign = TextAlign.Center)
    }
}

@Composable
fun QuestionnaireForm(context: Context) {
    val radioAgeOptions = listOf("Yes", "No")
    val radioHandOptions = listOf("Left-Handed", "Right-Handed", "Ambidextrous")

    var numHours by remember { mutableStateOf("") }
    var hoursHasError by remember { mutableStateOf(false) }
    var numHoursLabel by remember { mutableStateOf("Enter the number of hours") }

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
                    .align(CenterHorizontally)
                    .padding(10.dp),
                onClick = {
                    Toast.makeText(
                        context,
                        "Form: input submitted!",
                        Toast.LENGTH_SHORT
                    ).show()
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
        horizontalAlignment = CenterHorizontally,
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
                        .align(CenterHorizontally)
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        modifier = Modifier
                            .padding(all = Dp(value = 5F))
                            .fillMaxHeight()
                            .align(CenterVertically),
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
                            .align(CenterVertically)
                    )
                }
            }
        }
    }
}