package com.mtv.based.backend.model

import kotlinx.serialization.Serializable
import java.util.Date

@Serializable
data class LoginResponse(
    val firstname: String,
    val lastname: String,
    val lastLogin: Date,
)