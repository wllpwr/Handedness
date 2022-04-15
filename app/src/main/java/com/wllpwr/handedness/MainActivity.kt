package com.wllpwr.handedness

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.TopAppBar
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.wllpwr.handedness.ui.theme.HandednessTheme
import com.wllpwr.handedness.ui.theme.Purple40
import com.wllpwr.handedness.ui.theme.Purple80
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.net.URL


data class Data(val consent: String){

}



class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataObj = Data("True")
        var gson = Gson()
        val postData = gson.toJson(dataObj)

        // posting data
        val policy = ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        val url = URL("http://141.126.225.241:4444/")

        val conn = url.openConnection()
        conn.doOutput = true
        conn.setRequestProperty("Content-Type", "application/json")
        conn.setRequestProperty("Content-Length", postData.length.toString())

        DataOutputStream(conn.getOutputStream()).use { it.writeBytes(postData) }
        BufferedReader(InputStreamReader(conn.getInputStream())).use { bf ->
            var line: String?
            while (bf.readLine().also { line = it } != null) {
                println(line)
            }
        }
        
        // end posting data

        setContent {
            Scaffold(
                topBar = { TopAppBar(title = { Text("Handedness Test", color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold) }, backgroundColor = Purple80) },
                content = { DefaultPreview() }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    val mContext = LocalContext.current

    HandednessTheme {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = "The Effects of Handedness in Mobile Devices",
                fontSize = 32.sp,
                lineHeight = 45.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 10.dp)
            )

            Button(
                onClick = {
                    mContext.startActivity(Intent(mContext, ConsentForm::class.java))
                },
                colors = ButtonDefaults.buttonColors(Purple40),
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Text(
                    text = "Begin!",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                )
            }
        }
    }
}

//    @Composable
//    fun ScreenMain() {
//        val allScreens = RallyScreen.values().toList()
//        val navController = rememberNavController()
//        val backstackEntry = navController.currentBackStackEntryAsState()
//        val currentScreen = RallyScreen.fromRoute(backstackEntry.value?.destination?.route)
//
//        NavHost(navController, "consentForm") {
//
//            // First route : Home
//            composable("consentForm") {
//
//                // Lay down the Home Composable
//                // and pass the navController
//                ConsentHeader(navController = navController)
//            }
//
//            // Another Route : Profile
//            composable("surveyForm") {
//                // Profile Screen
//                SurveyForm(navController = navController)
//            }
//        }
//    }