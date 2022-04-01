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
    var name by remember { mutableStateOf("") }
    var nameHasError by remember { mutableStateOf(false) }
    var nameLabel by remember { mutableStateOf("Enter your name") }

    var ageChoice by remember { mutableStateOf("") }
    var option1ASeclected by remember { mutableStateOf(false) }

    var numHours by remember { mutableStateOf("") }
    var hoursHasError by remember { mutableStateOf(false) }
    var numHoursLabel by remember { mutableStateOf("Enter the number of hours") }

    HandednessTheme {
        Column {
            Text(
                text = "Hi! This is a handedness testing app that will determine your ability to navigate menus when using different hands. Let's get some info from you before we start.",
                textAlign = TextAlign.Center
            )

            Text(
                text = "Are you left-handed, right-handed or ambidextrous?",
                textAlign = TextAlign.Left
            )
            TextField(
                value = name,
                isError = nameHasError,
                label = { Text(text = nameLabel) },
                modifier = Modifier.padding(10.dp),
                onValueChange = { value -> name = value }
            )

            Text(text = "Are you 18 years or older?", textAlign = TextAlign.Left)
            CreateRadioAgeGroup(context)
//            RadioButton(
//                selected = option1ASeclected,
//                modifier = Modifier.padding(10.dp),
//                onClick = { (() -> ageChoice = "yes") },
//            )

            Text(
                text = "On average, how much time do you spend using a phone daily?",
                textAlign = TextAlign.Left
            )
            TextField(
                value = numHours,
                isError = hoursHasError,
                label = { Text(text = numHoursLabel) },
                modifier = Modifier.padding(10.dp),
                onValueChange = { value -> numHours = value },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            )
            Button(onClick = {
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

// Maybe can just make this function work for both radio groups and pass the options
@Composable
fun CreateRadioAgeGroup(context: Context) {
    val radioAgeOptions = listOf("Yes", "No")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioAgeOptions[1]) }
    Column(
// we are using column to align our
// imageview to center of the screen.
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 16.dp),

// below line is used for
// specifying vertical arrangement.
        verticalArrangement = Arrangement.Center,

// below line is used for
// specifying horizontal arrangement.
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // we are displaying all our
        // radio buttons in column.
        Column {
            // below line is use to set data to
            // each radio button in columns.
            radioAgeOptions.forEach { text ->
                Row(
                    Modifier
                        // using modifier to add max
                        // width to our radio button.
                        .fillMaxWidth()
                        // below method is use to add
                        // selectable to our radio button.
                        .selectable(
                            // this method is called when
                            // radio button is selected.
                            selected = (text == selectedOption),
                            // below method is called on
                            // clicking of radio button.
                            onClick = { onOptionSelected(text) }
                        )
                        // below line is use to add
                        // padding to radio button.
                        .padding(horizontal = 16.dp)
                ) {
                    // below line is use to
                    // generate radio button
                    RadioButton(
                        // inside this method we are
                        // adding selected with a option.
                        selected = (text == selectedOption),
                        modifier = Modifier.padding(all = Dp(value = 8F)),
                        onClick = {
                            // inide on click method we are setting a
                            // selected option of our radio buttons.
                            onOptionSelected(text)

                            // after clicking a radio button
                            // we are displaying a toast message.
                            Toast.makeText(context, text, Toast.LENGTH_LONG).show()
                        }
                    )
                    // below line is use to add
                    // text to our radio buttons.
                    Text(
                        text = text,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}