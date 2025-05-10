package com.cofa.urlshortening.adapter.`in`.web.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern

data class ShortenUrlRequest(
    @field:NotBlank(message = "Original URL must not be blank")
    @field:Pattern(
        regexp = "^(https?://).+",
        message = "URL must start with http:// or https://"
    )
    val originalUrl: String
)