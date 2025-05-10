package com.cofa.urlshortening.adapter.`in`.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class ShortenUrlRequest(
    @field:NotBlank(message = "Original URL must not be blank")
    @field:Pattern(
        regexp = "^(https?://).+",
        message = "URL must start with http:// or https://"
    )
    @field:Schema(
        description = "The original URL to shorten",
        example = "https://example.com"
    )
    val originalUrl: String
)