package com.cofa.urlshortening.domain.port.incoming

interface UrlService {
    fun shortenUrl(originalUrl: String): String
    fun getOriginalUrl(shortCode: String): String
}