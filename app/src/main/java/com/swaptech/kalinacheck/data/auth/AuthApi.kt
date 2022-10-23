package com.swaptech.kalinacheck.data.auth

import com.swaptech.kalinacheck.domain.auth.SendCode
import com.swaptech.kalinacheck.domain.auth.SendName
import com.swaptech.kalinacheck.domain.auth.SendPhone
import com.swaptech.kalinacheck.presentation.utils.network_error_handling.NetworkResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthApi {

    @POST("authorize")
    suspend fun sendPhone(@Body sendPhoneModel: SendPhone): NetworkResponse<Int>

    @POST("authorize/confirm")
    suspend fun sendCode(@Body sendCode: SendCode): NetworkResponse<String>

    @POST("authorize/finish")
    suspend fun sendName(@Body sendName: SendName): NetworkResponse<String>
}