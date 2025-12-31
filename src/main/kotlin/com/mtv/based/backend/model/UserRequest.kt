package com.mtv.based.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class UserRequest(
    val name: String,
    val email: String,
    val age: Int
)

@Serializable
data class UserResponse(
    val id: String,
    val name: String,
    val email: String,
    val age: Int
)