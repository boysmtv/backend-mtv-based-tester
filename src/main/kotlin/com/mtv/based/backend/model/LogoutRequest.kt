package com.mtv.based.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class LogoutRequest(
    val username: String
)