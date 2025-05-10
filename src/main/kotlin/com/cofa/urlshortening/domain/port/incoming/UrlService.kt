package com.cofa.urlshortening.domain.port.incoming

/**
 * Handles URL shortening and resolution logic.
 */
interface UrlService {
    /**
     * Shortens the given original URL.
     * @param originalUrl The original long URL to shorten.
     * @return A short code representing the URL.
     */
    fun shortenUrl(originalUrl: String): String

    /**
     * Resolves a short code to the original URL.
     * @throws UrlNotFoundException if not found.
     */
    fun getOriginalUrl(shortCode: String): String
}