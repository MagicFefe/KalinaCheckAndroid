package com.swaptech.kalinacheck.domain.task

import com.google.gson.annotations.SerializedName

data class Task(
    @SerializedName("task_content")
    val taskContent: String,
    @SerializedName("task_type")
    val taskType: TaskType,
    val answer: List<String>
)
