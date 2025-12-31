package com.mtv.based.backend.model

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class CheckResponse(
    val lastCheckin: Date
)