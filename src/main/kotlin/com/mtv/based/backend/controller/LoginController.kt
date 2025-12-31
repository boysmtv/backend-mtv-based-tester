package com.mtv.based.backend.controller

import com.mtv.based.backend.model.BaseResponse
import com.mtv.based.backend.model.CheckRequest
import com.mtv.based.backend.model.CheckResponse
import com.mtv.based.backend.model.ErrorDetail
import com.mtv.based.backend.model.LoginRequest
import com.mtv.based.backend.model.LoginResponse
import com.mtv.based.backend.model.LogoutRequest
import com.mtv.based.backend.model.LogoutResponse
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.Date

@RestController
@RequestMapping("/auth")
class LoginController {

    @PostMapping("/check")
    fun check(@Valid @RequestBody checkRequest: CheckRequest): ResponseEntity<out BaseResponse<*>> {

        return if (checkRequest.username == "Boys") {
            val response = CheckResponse(
                lastCheckin = Date(),
            )
            ResponseEntity.ok(
                BaseResponse.success(response)
            )
        } else {
            ResponseEntity.badRequest().body(
                BaseResponse.error(
                    listOf(
                        ErrorDetail("error", "Username or password invalid")
                    )
                )
            )
        }

    }

    @PostMapping("/login")
    fun login(@Valid @RequestBody loginRequest: LoginRequest): ResponseEntity<out BaseResponse<*>> {

        return if (loginRequest.password == "Mtv") {
            val response = LoginResponse(
                firstname = "Boys",
                lastname = "Mtv",
                lastLogin = Date()
            )
            ResponseEntity.ok(
                BaseResponse.success(response)
            )
        } else {
            ResponseEntity.badRequest().body(
                BaseResponse.error(
                    listOf(
                        ErrorDetail("error", "Username or password invalid")
                    )
                )
            )
        }

    }

    @PostMapping("/logout")
    fun logout(@Valid @RequestBody logoutRequest: LogoutRequest): ResponseEntity<out BaseResponse<*>> {
        return if (logoutRequest.username == "Boys") {
            val response = LogoutResponse(
                lastLogout = Date()
            )
            ResponseEntity.ok(
                BaseResponse.success(response)
            )
        } else {
            ResponseEntity.badRequest().body(
                BaseResponse.error(
                    listOf(
                        ErrorDetail("error", "Username or password invalid")
                    )
                )
            )
        }
    }

}