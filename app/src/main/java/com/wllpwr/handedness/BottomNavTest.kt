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
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.wllpwr.handedness.ui.theme.HandednessTheme
import androidx.compose.material.Icon as Icon1

class BottomNavTest : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DataObj.addData("BOTTOM NAV TEST")
        val iteration = intent.getIntExtra("iteration", 0)
        val hand = intent.getStringExtra("hand")
        val isFirstTest = intent.getBooleanExtra("isFirstTest", true)
        Timer.startTimer()

        setContent {
            HandednessTheme {
                val navController = rememberNavController()

                Surface(color = Color.White) {
                    // Scaffold Component
                    Scaffold(
                        // Bottom navigation
                        bottomBar = {
                            BottomNavigationBar(navController = navController)
                        }, content = { padding ->
                            // Navhost: where screens are placed
                            NavHostContainer(
                                navController = navController,
                                padding = padding,
                                iteration,
                                hand,
                                isFirstTest
                            )
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun HomeScreen() {

    // Column Composable,
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        // Parameters set to place the items in center
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon Composable
        Icon1(
            imageVector = Icons.Default.Home,
            contentDescription = "home",
            tint = Color(0xFF0F9D58)
        )
        // Text to Display the current Screen
        Text(text = "Home", color = Color.Black)
    }
}

@Composable
fun SearchScreen() {
    // Column Composable,
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        // parameters set to place the items in center
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon Composable
        Icon1(
            imageVector = Icons.Default.Search,
            contentDescription = "search",
            tint = Color(0xFF0F9D58)
        )
        // Text to Display the current Screen
        Text(text = "Search", color = Color.Black)
    }
}

@Composable
fun ProfileScreen(iteration: Int, hand: String?, isFirstTest: Boolean) {
    // Column Composable,
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        // parameters set to place the items in center
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Icon Composable
        Icon1(
            imageVector = Icons.Default.Person,
            contentDescription = "Profile",
            tint = Color(0xFF0F9D58)
        )
        // Text to Display the current Screen
        Text(text = "Profile", color = Color.Black)
    }
    TopBarNav(iteration, hand, isFirstTest)
}


data class BottomNavItem(
    val label: String,
    val icon: ImageVector,
    val route: String,
)

object Constants {
    val BottomNavItems = listOf(
        BottomNavItem(
            label = "Home",
            icon = Icons.Filled.Home,
            route = "home"
        ),
        BottomNavItem(
            label = "Search",
            icon = Icons.Filled.Search,
            route = "search"
        ),
        BottomNavItem(
            label = "Profile",
            icon = Icons.Filled.Person,
            route = "profile"
        )
    )
}

@Composable
fun NavHostContainer(
    navController: NavHostController,
    padding: PaddingValues,
    iteration: Int,
    hand: String?,
    isFirstTest: Boolean
) {

    NavHost(
        navController = navController,

        // set the start destination as home
        startDestination = "home",

        // Set the padding provided by scaffold
        modifier = Modifier.padding(paddingValues = padding),

        builder = {

            // route : Home
            composable("home") {
                HomeScreen()
            }

            // route : search
            composable("search") {
                SearchScreen()
            }

            // route : profile
            composable("profile") {
                ProfileScreen(iteration, hand, isFirstTest)
            }
        })


}

@Composable
fun BottomNavigationBar(navController: NavHostController) {

    BottomNavigation(

        // set background color
        backgroundColor = Color(255, 255, 255)
    ) {

        // observe the backstack
        val navBackStackEntry by navController.currentBackStackEntryAsState()

        // observe current route to change the icon
        // color,label color when navigated
        val currentRoute = navBackStackEntry?.destination?.route

        // Bottom nav items we declared
        Constants.BottomNavItems.forEach { navItem ->

            // Place the bottom nav items
            BottomNavigationItem(

                // it currentRoute is equal then its selected route
                selected = currentRoute == navItem.route,

                // navigate on click
                onClick = {
                    navController.navigate(navItem.route)
                },

                // Icon of navItem
                icon = {
                    Icon1(imageVector = navItem.icon, contentDescription = navItem.label)
                },

                // label
                label = {
                    Text(text = navItem.label)
                },
                alwaysShowLabel = false
            )
        }
    }

}

@Composable
fun TopBarNav(iteration: Int, hand: String?, isFirstTest: Boolean) {
    val mContext = LocalContext.current
    val nextIteration = Intent(
        mContext,
        BottomNavTest::class.java
    )
    var itCount = iteration
    // TopAppBar Composable
    TopAppBar(
        // Provide Title
        title = {
            Text(text = "Profile", color = Color.White)
        },
        // Provide the navigation Icon ( Icon on the left to toggle drawer)
        navigationIcon = {
            androidx.compose.material.Icon(
                imageVector = Icons.Default.Menu,
                contentDescription = "Menu",

                // When clicked trigger onClick
                // Callback to trigger drawer open
                modifier = Modifier
                    .clickable(onClick = {}),
                tint = Color.White
            )
        },
        actions = {
            androidx.compose.material.Icon(
                imageVector = Icons.Default.Settings,
                contentDescription = "Settings",

                // When clicked trigger onClick
                // Callback to trigger drawer open
                modifier = Modifier
                    .clickable(onClick = {
                        if (itCount != 4) {
                            itCount++
                            nextIteration.putExtra("iteration", itCount)
                            nextIteration.putExtra("hand", hand)
                            nextIteration.putExtra("isFirstTest", isFirstTest)

                            Toast.makeText(
                                mContext,
                                "Test complete!",
                                Toast.LENGTH_SHORT
                            ).show()

                            Timer.endTimer()
                            DataObj.addData("TIME")
                            DataObj.addData(Timer.getTime().toString())
                            DataObj.completeTest()
                            mContext.startActivity(nextIteration)
                        } else {
                            var nextPage = Intent(mContext, SwapDirections::class.java)
                            if (isFirstTest) {
                                nextPage.putExtra("hand", hand)
                                nextPage.putExtra("testNum", "test3")
                                mContext.startActivity(nextPage)
                            } else {
                                Toast
                                    .makeText(
                                        mContext,
                                        "Test fully completed!",
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()

                                nextPage = Intent(mContext, EndScreen::class.java)
                                mContext.startActivity(nextPage)
                            }
                        }

                    }
                    ),
                tint = Color.White
            )
        }
    )
}
