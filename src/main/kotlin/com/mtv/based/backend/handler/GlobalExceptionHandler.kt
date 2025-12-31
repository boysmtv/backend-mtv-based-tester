package com.mtv.based.backend.handler

import com.mtv.based.backend.model.BaseResponse
import com.mtv.based.backend.model.ErrorDetail
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.NoHandlerFoundException

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidation(ex: MethodArgumentNotValidException): ResponseEntity<BaseResponse<Nothing>> {

        val errors = ex.bindingResult.fieldErrors.map {
            ErrorDetail(it.field, it.defaultMessage ?: "Invalid")
        }

        return ResponseEntity.badRequest().body(
            BaseResponse(
                code = "01",
                message = "Validation failed",
                errors = errors
            )
        )
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleBodyNull(): ResponseEntity<BaseResponse<Nothing>> =
        ResponseEntity.badRequest().body(
            BaseResponse(
                code = "01",
                message = "Request body is required",
                errors = listOf(ErrorDetail("body", "Request body is missing"))
            )
        )

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handle404(ex: NoHandlerFoundException): ResponseEntity<BaseResponse<Nothing>> {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(
                BaseResponse(
                    code = "404",
                    message = "Endpoint not found",
                    errors = listOf(
                        ErrorDetail("path", ex.requestURL)
                    )
                )
            )
    }
}
