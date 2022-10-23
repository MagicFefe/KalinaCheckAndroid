package com.swaptech.kalinacheck.domain.revision

import java.util.UUID

data class Revision(
    val id: UUID,
    val shopAddress: String,
    val name: String,
    val expireDate: String,
    val active: Boolean
)
