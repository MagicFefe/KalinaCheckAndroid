package com.swaptech.kalinacheck.domain.revision

import com.swaptech.kalinacheck.domain.attachment.Attachment
import com.swaptech.kalinacheck.domain.form.Form
import java.util.UUID

data class Revision(
    val form: Form,
    val attachments: List<Attachment>,
    val id: UUID,
    val shopAddress: String,
    val name: String,
    val expireDate: String,
    val active: Boolean
)
