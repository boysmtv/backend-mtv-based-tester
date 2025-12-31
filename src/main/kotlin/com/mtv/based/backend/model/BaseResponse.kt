package com.mtv.based.backend.model

import com.fasterxml.jackson.annotation.JsonInclude
import kotlinx.serialization.EncodeDefault
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable

@JsonInclude(JsonInclude.Include.NON_NULL)
@OptIn(ExperimentalSerializationApi::class)
@Serializable
data class BaseResponse<T>(
    val code: String,
    val message: String,
    val data: T? = null,
    val errors: List<ErrorDetail>? = null
) {
    companion object {

        fun <T> success(data: T): BaseResponse<T> {
            return BaseResponse(
                code = "00",
                message = "Success",
                data = data
            )
        }

        fun error(errors: List<ErrorDetail>): BaseResponse<List<ErrorDetail>> {
            return BaseResponse(
                code = "01",
                message = "Validation failed",
                errors = errors
            )
        }
    }
}
