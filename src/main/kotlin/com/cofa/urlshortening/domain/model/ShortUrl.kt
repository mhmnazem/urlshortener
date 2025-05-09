package com.cofa.urlshortening.domain.model

data class ShortUrl(
    val id: Long? = null,
    val originalUrl: String,
    val shortCode: String? = null
)
