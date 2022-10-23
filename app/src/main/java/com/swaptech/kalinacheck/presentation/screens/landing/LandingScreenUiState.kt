package com.swaptech.kalinacheck.presentation.screens.landing

import com.swaptech.kalinacheck.presentation.utils.RETRY_WAITING_TIME_SECONDS

data class LandingScreenUiState(
    val bottomSheetTextFieldText: String = "",
    val retryWaitingTimeRemainingSeconds: Int = RETRY_WAITING_TIME_SECONDS,
    val enterPhone: Boolean = true,
    val enterSMSCode: Boolean = false,
    val enterName: Boolean = false
)
