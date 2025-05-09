package com.cofa.urlshortening.application

import com.cofa.urlshortening.adapter.out.hashing.IdEncoder
import com.cofa.urlshortening.domain.model.Url
import com.cofa.urlshortening.domain.port.incoming.UrlService
import com.cofa.urlshortening.domain.port.outgoing.UrlRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UrlServiceImpl(
    private val urlRepository: UrlRepository,
    private val idEncoder: IdEncoder
) : UrlService {

    @Transactional
    override fun shortenUrl(originalUrl: String): String {
        // Return existing short code if URL already exists
        urlRepository.findByOriginalUrl(originalUrl)?.let {
            return it.shortCode ?: throw IllegalStateException("Short code cannot be null")
        }

        // Step 1: Save the original URL to get the generated ID
        val saved = urlRepository.save(Url(id = null, originalUrl = originalUrl, shortCode = ""))

        // Step 2: Generate short code from ID
        val code = idEncoder.encode(saved.id!!)

        // Step 3: Update the entity with the short code
        val updated = saved.copy(shortCode = code)
        urlRepository.save(updated)
        return code

    }

    override fun getOriginalUrl(shortCode: String): String =
        urlRepository.findByShortCode(shortCode)?.originalUrl
            ?: throw NoSuchElementException("URL not found")
}