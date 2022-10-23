package com.swaptech.kalinacheck.presentation.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.swaptech.kalinacheck.domain.auth.AuthInteractor
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authInteractor: AuthInteractor
) : ViewModel() {

    val token = flow {
        emit(authInteractor.getUserToken())
    }.stateIn(viewModelScope, SharingStarted.Lazily, "")

    fun saveUserToken(
        token: String
    ) {
        viewModelScope.launch {
            authInteractor.saveUserToken(token)
        }
    }
}