package com.cofa.urlshortening.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository

interface UrlJpaRepository: JpaRepository<UrlEntity, Long> {
    fun findByShortCode(shortCode: String): UrlEntity?
    fun findByOriginalUrl(originalUrl: String): UrlEntity?
}