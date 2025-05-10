package com.cofa.urlshortening.adapter.`in`.web.exception

data class ApiErrorResponse(
    val status: Int,
    val error: String,
    val message: String?,
)
