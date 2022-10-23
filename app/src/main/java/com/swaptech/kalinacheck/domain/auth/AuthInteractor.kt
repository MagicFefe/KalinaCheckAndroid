package com.swaptech.kalinacheck.domain.auth

import javax.inject.Inject

class AuthInteractor @Inject constructor(
    private val authRepository: AuthRepository
) {

    suspend fun sendPhone(sendPhone: SendPhone) =
        authRepository.sendPhone(sendPhone)

    suspend fun sendCode(sendCode: SendCode) =
        authRepository.sendCode(sendCode)

    suspend fun sendName(sendName: SendName) =
        authRepository.sendName(sendName)

    suspend fun saveUserToken(token: String) =
        authRepository.saveUserToken(token)

    suspend fun getUserToken() =
        authRepository.getUserToken()
}