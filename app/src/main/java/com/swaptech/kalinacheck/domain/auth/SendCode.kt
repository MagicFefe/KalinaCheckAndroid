package com.swaptech.kalinacheck.domain.auth

data class SendCode(
    val phone: String,
    val code: Int
)
