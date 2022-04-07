package com.wllpwr.handedness

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Screen metadata for Rally.
 */
enum class RallyScreen(
    val icon: ImageVector,
) {
    Home(
        icon = Icons.Filled.Done,
    ),
    Consent(
        icon = Icons.Filled.Done,
    ),
    Survey(
        icon = Icons.Filled.Done,
    );

    companion object {
        fun fromRoute(route: String?): RallyScreen =
            when (route?.substringBefore("/")) {
                Home.name -> Home
                Consent.name -> Consent
                Survey.name -> Survey
                null -> Home
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }
}
