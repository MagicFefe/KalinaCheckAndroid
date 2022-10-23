package com.swaptech.kalinacheck.data.interceptor

import android.content.SharedPreferences
import com.swaptech.kalinacheck.presentation.utils.AUTH_TOKEN_KEY
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(
    private val sharedPreferences: SharedPreferences
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val token = sharedPreferences.getString(AUTH_TOKEN_KEY, "")
        val originalRequest = chain.request()
        val newRequest = originalRequest.newBuilder()
            .addHeader("Authorization", token ?: "")
            .build()
        return chain.proceed(newRequest)
    }
}