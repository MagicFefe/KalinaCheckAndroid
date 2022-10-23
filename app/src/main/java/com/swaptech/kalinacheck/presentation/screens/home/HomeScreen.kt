package com.swaptech.kalinacheck.presentation.screens.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.swaptech.kalinacheck.R
import com.swaptech.kalinacheck.di.presentation.view_model.ViewModelFactory
import com.swaptech.kalinacheck.presentation.navigation.Home
import com.swaptech.kalinacheck.presentation.navigation.Root
import com.swaptech.kalinacheck.presentation.screens.attachments.AttachmentsScreen
import com.swaptech.kalinacheck.presentation.screens.attachments.AttachmentsScreenViewModel
import com.swaptech.kalinacheck.presentation.screens.camera.CameraScreen
import com.swaptech.kalinacheck.presentation.screens.form.FormScreen
import com.swaptech.kalinacheck.presentation.screens.profile.ProfileScreen
import com.swaptech.kalinacheck.presentation.screens.revisions.RevisionsScreen
import com.swaptech.kalinacheck.presentation.utils.KalinaCheckTopAppBar

@Composable
fun HomeScreen(
    viewModelFactory: ViewModelFactory
) {
    val navController = rememberNavController()
    val items = listOf<Home>(
        Home.Revisions,
        Home.Profile
    )
    var currentDestinationName by rememberSaveable {
        mutableStateOf(Home.Revisions.destination)
    }
    Scaffold(
        topBar = {
            KalinaCheckTopAppBar(
                title = stringResource(currentDestinationName)
            )
        },
        bottomBar = {
            BottomNavigation(
                backgroundColor = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.primary
            ) {
                items.forEach { screen ->
                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination
                    BottomNavigationItem(
                        icon = { Icon(screen.icon, contentDescription = null) },
                        label = { Text(stringResource(screen.destination)) },
                        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                            currentDestinationName = screen.destination
                        }
                    )
                }
            }
        }
    ) { contentPadding ->
        NavHost(
            modifier = Modifier.padding(contentPadding),
            navController = navController,
            startDestination = Home.Revisions.route,
        ) {
            composable(
                route = Home.Revisions.route
            ) {
                RevisionsScreen(
                    viewModel = viewModel(
                        modelClass = AttachmentsScreenViewModel::class.java,
                        factory = viewModelFactory
                    )
                )
            }

            composable(
                route = Home.Profile.route
            ) {
                ProfileScreen(
                    onSeeAttachmentsClick = {
                        navController.navigate(Root.Attachments.route)
                        currentDestinationName = R.string.attachments
                    },
                    onFormButtonClick = {
                        navController.navigate(Root.Form.route)
                        currentDestinationName = R.string.form
                    }
                )
            }

            composable(
                route = Root.Attachments.route
            ) {
                AttachmentsScreen(
                    viewModel = viewModel(
                        modelClass = AttachmentsScreenViewModel::class.java,
                        factory = viewModelFactory
                    ),
                    onTakePhotoButtonClick = {
                        navController.navigate(Root.Camera.route)
                    },
                    onBack = {
                        currentDestinationName = Home.Profile.destination
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Root.Form.route
            ) {
                FormScreen(
                    tasks = listOf(),
                    onBack = {
                        currentDestinationName = Home.Profile.destination
                        navController.navigateUp()
                    }
                )
            }

            composable(
                route = Root.Camera.route
            ) {
                CameraScreen(
                    onImageCaptured = {

                    }
                )
            }
        }
    }
}
