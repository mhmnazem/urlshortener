package com.cofa.urlshortening.domain.model

data class Url(
    val id: Long? = null,
    val originalUrl: String,
    val shortCode: String? = null
)
