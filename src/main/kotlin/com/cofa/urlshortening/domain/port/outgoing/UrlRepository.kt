package com.cofa.urlshortening.domain.port.outgoing

import com.cofa.urlshortening.domain.model.Url

interface UrlRepository {
    fun save(url: Url): Url
    fun findByShortCode(shortCode: String): Url?
    fun findByOriginalUrl(originalUrl: String): Url?
}