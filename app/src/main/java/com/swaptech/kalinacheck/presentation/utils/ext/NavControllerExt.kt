package com.swaptech.kalinacheck.presentation.utils.ext

import androidx.navigation.NavController
import androidx.navigation.NavOptionsBuilder

fun NavController.replaceTo(route: String, builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route) {
        launchSingleTop = true
        popBackStack()
        builder()
    }
}

fun NavController.navigateSingle(route: String, builder: NavOptionsBuilder.() -> Unit = {}) {
    navigate(route) {
        launchSingleTop = true
        builder()
    }
}
