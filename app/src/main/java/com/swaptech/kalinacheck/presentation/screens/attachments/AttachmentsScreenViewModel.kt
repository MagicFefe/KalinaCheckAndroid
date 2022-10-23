package com.swaptech.kalinacheck.presentation.screens.attachments

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class AttachmentsScreenViewModel @Inject constructor(

): ViewModel() {

    private val _uiState = MutableStateFlow(AttachmentsScreenUiState())
    val uiState: StateFlow<AttachmentsScreenUiState> get() = _uiState

    fun showCamera() {
        _uiState.value = _uiState.value.copy(
            showCamera = true
        )
    }

    fun hideCamera() {
        _uiState.value = _uiState.value.copy(
            showCamera = false
        )
    }
}
