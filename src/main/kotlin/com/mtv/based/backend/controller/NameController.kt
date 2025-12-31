package com.mtv.based.backend.controller

import com.mtv.based.backend.model.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class NameController {

    private fun buildNameData() = NameData(
        count = 12345,
        name = "alex",
        country = listOf(
            CountryProbability("US", 0.42),
            CountryProbability("GB", 0.21),
            CountryProbability("ID", 0.12)
        )
    )

    private fun errorResponse(errors: List<ErrorDetail>): ResponseEntity<String> {
        val response = BaseResponse<NameData>(
            code = "01",
            message = "Validation failed",
            errors = errors
        )
        return ResponseEntity.badRequest().body(Json.encodeToString(response))
    }

    @GetMapping("/name")
    fun getName(): ResponseEntity<BaseResponse<NameData>> =
        ResponseEntity.ok(BaseResponse("00", "Success", buildNameData()))

    @GetMapping("/name/error")
    fun getNameError(): ResponseEntity<String> =
        errorResponse(
            listOf(
                ErrorDetail("name", "Name is required"),
                ErrorDetail("country", "At least one country must be selected")
            )
        )

    @PostMapping("/user")
    fun createUser(@RequestBody request: UserRequest): ResponseEntity<BaseResponse<NameData>> =
        ResponseEntity.ok(BaseResponse("00", "Success", buildNameData()))

    @PostMapping("/user/error")
    fun createUserError(@RequestBody request: UserRequest): ResponseEntity<String> =
        errorResponse(
            listOf(
                ErrorDetail("name", "Name is required"),
                ErrorDetail("country", "At least one country must be selected")
            )
        )
}

