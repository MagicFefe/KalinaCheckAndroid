package com.swaptech.kalinacheck.presentation.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatListBulleted
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector
import com.swaptech.kalinacheck.R

sealed class Home(val route: String, val icon: ImageVector, @StringRes val destination: Int) {
    object Revisions: Home("revisions", Icons.Filled.FormatListBulleted, R.string.revisions)
    object Profile: Home("profile", Icons.Filled.Person, R.string.profile)
}
