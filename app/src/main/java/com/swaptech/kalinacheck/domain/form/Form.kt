package com.swaptech.kalinacheck.domain.form

import com.google.gson.annotations.SerializedName
import com.swaptech.kalinacheck.domain.task.Task
import java.util.UUID

data class Form(
    val name: String,
    @SerializedName("revision_id")
    val revisionId: UUID,
    val tasks: List<Task>,
    @SerializedName("is_template")
    val isTemplate: Boolean
)
