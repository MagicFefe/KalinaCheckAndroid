package com.swaptech.kalinacheck.presentation.screens.root

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.swaptech.kalinacheck.di.presentation.view_model.ViewModelFactory
import com.swaptech.kalinacheck.presentation.navigation.Root
import com.swaptech.kalinacheck.presentation.screens.home.HomeScreen
import com.swaptech.kalinacheck.presentation.screens.landing.LandingScreen
import com.swaptech.kalinacheck.presentation.screens.landing.LandingScreenViewModel
import com.swaptech.kalinacheck.presentation.utils.ext.replaceTo
import com.swaptech.kalinacheck.presentation.view_model.AuthViewModel

@Composable
fun RootScreen(
    viewModelFactory: ViewModelFactory,
    authViewModel: AuthViewModel = viewModel(
        modelClass = AuthViewModel::class.java,
        factory = viewModelFactory
    )
) {
    val token by authViewModel.token.collectAsState()
    val navController = rememberNavController()
    Scaffold { contentPadding ->
        NavHost(
            modifier = Modifier.padding(contentPadding),
            navController = navController,
            startDestination = if(token.isEmpty()) Root.Landing.route else Root.Home.route
        ) {
            composable(
                route = Root.Landing.route
            ) {
                LandingScreen(
                    viewModel = viewModel(
                        modelClass = LandingScreenViewModel::class.java,
                        factory = viewModelFactory
                    ),
                    authViewModel = viewModel(
                        modelClass = AuthViewModel::class.java,
                        factory = viewModelFactory
                    ),
                    onNavigateToHome = {
                        navController.replaceTo(Root.Home.route)
                    }
                )
            }

            composable(
                route = Root.Home.route
            ) {
                HomeScreen(
                    viewModelFactory = viewModelFactory
                )
            }
        }
    }
}
