package com.swaptech.kalinacheck.domain.auth

import com.swaptech.kalinacheck.presentation.utils.network_error_handling.NetworkResponse

interface AuthRepository {

    suspend fun sendPhone(sendPhone: SendPhone): NetworkResponse<Int>

    suspend fun sendCode(sendCode: SendCode): NetworkResponse<String>

    suspend fun sendName(sendName: SendName): NetworkResponse<String>

    suspend fun saveUserToken(token: String)

    suspend fun getUserToken(): String
}
