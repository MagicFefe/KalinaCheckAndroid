package com.swaptech.kalinacheck.presentation.navigation

sealed class Root(val route: String) {

    object Landing: Root("landing")
    object Home: Root("home")
    object Attachments: Root("attachments")
    object Form: Root("form")
    object Camera: Root("camera")
}
