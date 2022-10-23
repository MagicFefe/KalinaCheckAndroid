package com.swaptech.kalinacheck.presentation.screens.recorder

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class RecorderScreenViewModel @Inject constructor(): ViewModel() {

    private val _uiState = MutableStateFlow(RecorderScreenUiState())
    val uiState: StateFlow<RecorderScreenUiState> get() = _uiState

    fun startRecording() {

    }
}