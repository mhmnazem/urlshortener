package com.cofa.urlshortening.domain.port.outgoing

import com.cofa.urlshortening.domain.model.Url

/**
 * Port for persisting and retrieving shortened URLs.
 * Abstracts the persistence mechanism (e.g. database, cache).
 */
interface UrlRepository {


    /**
     * Saves a [Url] object and returns the persisted version.
     * If the URL has an ID, it updates; otherwise, it inserts.
     *
     * @param url The [Url] domain object to persist.
     * @return The saved [Url] with generated ID and short code.
     */
    fun save(url: Url): Url

    /**
     * Finds a [Url] by its short code.
     *
     * @param shortCode The short code to search for.
     * @return The matching [Url], or `null` if not found.
     */
    fun findByShortCode(shortCode: String): Url?

    /**
     * Finds a [Url] by its original full URL.
     *
     * @param originalUrl The original long URL.
     * @return The matching [Url], or `null` if not found.
     */
    fun findByOriginalUrl(originalUrl: String): Url?
}