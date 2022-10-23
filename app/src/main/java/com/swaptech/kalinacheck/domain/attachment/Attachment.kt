package com.swaptech.kalinacheck.domain.attachment

import com.google.gson.annotations.SerializedName

data class Attachment(
    @SerializedName("media_type")
    val mediaType: MediaType,
    @SerializedName("attachment_url")
    val attachmentUrl: String
)
