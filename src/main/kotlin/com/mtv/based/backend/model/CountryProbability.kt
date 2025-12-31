package com.mtv.based.backend.model

import kotlinx.serialization.Serializable

@Serializable
data class ErrorDetail(
    val field: String? = null,
    val message: String
)

@Serializable
data class CountryProbability(
    val country_id: String,
    val probability: Double
)

@Serializable
data class NameData(
    val count: Int,
    val name: String,
    val country: List<CountryProbability>
)
