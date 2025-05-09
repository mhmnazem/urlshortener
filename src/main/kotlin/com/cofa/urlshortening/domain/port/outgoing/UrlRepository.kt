package com.cofa.urlshortening.domain.port.outgoing

import com.cofa.urlshortening.domain.model.ShortUrl

interface UrlRepository {
    fun save(url: ShortUrl): ShortUrl
    fun findByShortCode(shortCode: String): ShortUrl?
    fun findByOriginalUrl(originalUrl: String): ShortUrl?
}