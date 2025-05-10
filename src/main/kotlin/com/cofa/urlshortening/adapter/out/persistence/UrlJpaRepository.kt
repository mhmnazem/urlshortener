package com.cofa.urlshortening.adapter.out.persistence

import org.springframework.data.jpa.repository.JpaRepository


/**
 * Spring Data JPA repository for accessing [UrlEntity] data.
 * Provides custom query methods for lookup by short code and original URL.
 */
interface UrlJpaRepository: JpaRepository<UrlEntity, Long> {

    /**
     * Finds a [UrlEntity] by its short code.
     *
     * @param shortCode The short code to look up.
     * @return The matching [UrlEntity], or `null` if not found.
     */
    fun findByShortCode(shortCode: String): UrlEntity?

    /**
     * Finds a [UrlEntity] by its original full URL.
     *
     * @param originalUrl The original URL to look up.
     * @return The matching [UrlEntity], or `null` if not found.
     */
    fun findByOriginalUrl(originalUrl: String): UrlEntity?
}