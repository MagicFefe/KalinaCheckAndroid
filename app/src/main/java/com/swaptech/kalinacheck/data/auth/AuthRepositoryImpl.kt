package com.swaptech.kalinacheck.data.auth

import android.content.SharedPreferences
import com.swaptech.kalinacheck.domain.auth.AuthRepository
import com.swaptech.kalinacheck.domain.auth.SendCode
import com.swaptech.kalinacheck.domain.auth.SendName
import com.swaptech.kalinacheck.domain.auth.SendPhone
import com.swaptech.kalinacheck.presentation.utils.AUTH_TOKEN_KEY
import com.swaptech.kalinacheck.presentation.utils.network_error_handling.NetworkResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi,
    private val sharedPrefs: SharedPreferences
): AuthRepository {

    override suspend fun sendPhone(sendPhone: SendPhone): NetworkResponse<Int> =
        withContext(Dispatchers.IO) {
            authApi.sendPhone(sendPhone)
        }

    override suspend fun sendCode(sendCode: SendCode): NetworkResponse<String> =
        withContext(Dispatchers.IO) {
            authApi.sendCode(sendCode)
        }

    override suspend fun sendName(sendName: SendName): NetworkResponse<String> =
        withContext(Dispatchers.IO) {
            authApi.sendName(sendName)
        }

    override suspend fun saveUserToken(token: String) {
        withContext(Dispatchers.IO) {
            sharedPrefs.edit().putString(AUTH_TOKEN_KEY, token).commit()
        }
    }

    override suspend fun getUserToken(): String =
        withContext(Dispatchers.IO) {
            sharedPrefs.getString(AUTH_TOKEN_KEY, "") ?: ""
        }
}