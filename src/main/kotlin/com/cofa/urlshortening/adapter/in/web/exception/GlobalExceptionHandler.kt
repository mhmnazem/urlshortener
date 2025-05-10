package com.cofa.urlshortening.adapter.`in`.web.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    @ExceptionHandler(UrlNotFoundException::class)
    fun handleNotFound(ex: UrlNotFoundException): ResponseEntity<ApiErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ApiErrorResponse(
                status = 404,
                error = "Not Found",
                message = ex.message,
            )
        )
    }

    @ExceptionHandler(InvalidUrlException::class)
    fun handleInvalidUrl(ex: InvalidUrlException): ResponseEntity<ApiErrorResponse> {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ApiErrorResponse(
                status = 400,
                error = "Bad Request",
                message = ex.message,
            )
        )
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleValidationErrors(ex: MethodArgumentNotValidException): ResponseEntity<ApiErrorResponse> {
        val firstError = ex.bindingResult.fieldErrors.firstOrNull()?.defaultMessage
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
            ApiErrorResponse(
                status = 400,
                error = "Validation Error",
                message = firstError,
            )
        )
    }
}
