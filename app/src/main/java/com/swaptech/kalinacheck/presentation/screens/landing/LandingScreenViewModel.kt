package com.swaptech.kalinacheck.presentation.screens.landing

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swaptech.kalinacheck.domain.auth.AuthInteractor
import com.swaptech.kalinacheck.domain.auth.SendCode
import com.swaptech.kalinacheck.domain.auth.SendName
import com.swaptech.kalinacheck.domain.auth.SendPhone
import com.swaptech.kalinacheck.presentation.utils.MAX_PHONE_NUMBER_LENGTH
import com.swaptech.kalinacheck.presentation.utils.RETRY_WAITING_TIME_SECONDS
import com.swaptech.kalinacheck.presentation.utils.network_error_handling.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LandingScreenViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
) : ViewModel() {

    private val _uiState = MutableStateFlow(LandingScreenUiState())
    val uiState: StateFlow<LandingScreenUiState> get() = _uiState

    fun onBottomSheetTextFieldTextChange(input: String) {
        if (input.length <= MAX_PHONE_NUMBER_LENGTH) {
            _uiState.value = _uiState.value.copy(
                bottomSheetTextFieldText = input
            )
        }
    }

    fun startTimer() {
        viewModelScope.launch {
            while (_uiState.value.retryWaitingTimeRemainingSeconds > 0) {
                delay(1000)
                _uiState.value = _uiState.value.copy(
                    retryWaitingTimeRemainingSeconds = _uiState.value.retryWaitingTimeRemainingSeconds - 1
                )
            }
        }
    }

    fun dropTimer() {
        _uiState.value = _uiState.value.copy(
            retryWaitingTimeRemainingSeconds = RETRY_WAITING_TIME_SECONDS
        )
    }

    fun showEnterSMSCodeSheet() {
        _uiState.value = _uiState.value.copy(
            enterPhone = false,
            enterSMSCode = true,
            enterName = false
        )
    }

    fun showEnterName() {
        _uiState.value = _uiState.value.copy(
            enterPhone = false,
            enterSMSCode = false,
            enterName = true
        )
    }

    fun showEnterPhoneSheet() {
        _uiState.value = _uiState.value.copy(
            enterPhone = false,
            enterSMSCode = true
        )
    }

    fun clearBottomSheetText() {
        _uiState.value = _uiState.value.copy(
            bottomSheetTextFieldText = ""
        )
    }

    fun setBottomSheetText(text: String) {
        _uiState.value = _uiState.value.copy(
            bottomSheetTextFieldText = text
        )
    }

    fun sendPhone(
        sendPhone: SendPhone,
        onSuccess: (Int) -> Unit,
        onFail: (Int, String) -> Unit
    ) {
        viewModelScope.launch {
            authInteractor.sendPhone(sendPhone)
                .onSuccess {
                    onSuccess(it)
                }
                .onFail(onFail)
        }
    }

    fun sendCode(
        sendCode: SendCode,
        onSuccess: (String) -> Unit,
        onFail: (Int, String) -> Unit
    ) {
        viewModelScope.launch {
            authInteractor.sendCode(sendCode)
                .onSuccess(onSuccess)
                .onFail(onFail)
        }
    }

    fun sendName(
        sendName: SendName,
        onSuccess: (String) -> Unit,
        onFail: (Int, String) -> Unit
    ) {
        viewModelScope.launch {
            authInteractor.sendName(sendName)
                .onSuccess(onSuccess)
                .onFail(onFail)
        }
    }
}
