package com.swaptech.kalinacheck.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.swaptech.kalinacheck.KalinaCheckApplication
import com.swaptech.kalinacheck.di.presentation.view_model.ViewModelFactory
import com.swaptech.kalinacheck.presentation.screens.root.RootScreen
import com.swaptech.kalinacheck.presentation.theme.KalinaCheckTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (application as KalinaCheckApplication).androidInjector().inject(this)

        setContent {
            KalinaCheckTheme {
                RootScreen(
                    viewModelFactory = viewModelFactory
                )
            }
        }
    }
}
