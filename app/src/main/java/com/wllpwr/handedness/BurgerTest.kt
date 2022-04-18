package com.wllpwr.handedness

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wllpwr.handedness.ui.theme.Purple40
import com.wllpwr.handedness.ui.theme.Purple80
import com.wllpwr.handedness.ui.theme.PurpleGrey40
import kotlinx.coroutines.launch

class BurgerTest : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Scaffold(
                topBar = { TopAppBar(title = { Text("Handedness Test", color = Color.Black, fontWeight = FontWeight.Bold) }, backgroundColor = Purple80) },
                content = {
                    Surface(color = Color.White) {
                        // Scaffold we created
                        ScaffoldExample()
                    }
                }
            )
        }
    }
}

@Composable
fun ScaffoldExample() {

    // create a scaffold state, set it to close by default
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    // Create a coroutine scope. Opening of Drawer
    // and snackbar should happen in background
    // thread without blocking main thread
    val coroutineScope = rememberCoroutineScope()

    // Scaffold Composable
    Scaffold(
        drawerElevation = 5.dp,

        // pass the scaffold state
        scaffoldState = scaffoldState,

        // pass the topbar we created
        topBar = {
            TopBar(
                // When menu is clicked open the
                // drawer in coroutine scope
                onMenuClicked = {
                    coroutineScope.launch {
                        // to close use -> scaffoldState.drawerState.close()
                        scaffoldState.drawerState.open()
                    }
                })
        },

        // pass the bottomBar we created

        // Pass the body in
        // content parameter
        content = {
            Body()
        },

        // pass the drawer
        drawerContent = {
            Drawer()
        }

    )
}

// A function which will receive a
// callback to trigger to opening the drawer
@Composable
fun TopBar(onMenuClicked: () -> Unit) {
    // TopAppBar Composable
    TopAppBar(
        // Provide Title
        title = {
            Text(text = "Scaffold Test", color = Color.White)
        },
        // Provide the navigation Icon ( Icon on the left to toggle drawer)
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",

                // When clicked trigger onClick
                // Callback to trigger drawer open
                modifier = Modifier.clickable(onClick = onMenuClicked),
                tint = Color.White
            )
        },
        // background color of topAppBar
        backgroundColor = Purple40
    )
}

@Composable
fun Body() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(text = "MAIN CONTENT", color = PurpleGrey40)
    }
}


@Composable
fun Drawer() {
    // Column Composable
    val mContext = LocalContext.current
    Column(
        Modifier
            .background(Color.White)
            .fillMaxSize()
    ) {
        val random = (1..10).random()
        for (i in 1..10) {

            val intent = Intent(
                mContext,
                MainMenu::class.java
            )
            if (i == random) {
                Text(
                    text = "Item $i",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
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
                    textAlign = TextAlign.Left
                )
            } else {
                Text(
                    text = "Item $i",
                    fontSize = 15.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .clickable {
                            Toast
                                .makeText(
                                    mContext,
                                    "Wrong item!",
                                    Toast.LENGTH_SHORT
                                )
                                .show()

                        },
                    textAlign = TextAlign.Left
                )
            }
            Divider(color = Color.Gray, thickness = 0.5.dp)
        }
    }
    }
