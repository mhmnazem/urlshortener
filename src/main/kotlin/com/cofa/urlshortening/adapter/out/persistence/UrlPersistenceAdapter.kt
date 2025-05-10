package com.cofa.urlshortening.adapter.out.persistence

import com.cofa.urlshortening.domain.model.Url
import com.cofa.urlshortening.domain.port.outgoing.UrlRepository
import org.springframework.stereotype.Component
import org.slf4j.LoggerFactory


@Component
class UrlPersistenceAdapter(private val urlJpaRepository: UrlJpaRepository) : UrlRepository {
    private val logger = LoggerFactory.getLogger(UrlPersistenceAdapter::class.java)


    override fun save(url: Url): Url {
        val entity = url.toEntity()
        val saved = urlJpaRepository.save(entity)
        logger.info("Saving URL: $url")
        return saved.toDomain()
    }


    override fun findByShortCode(shortCode: String): Url? =
        urlJpaRepository.findByShortCode(shortCode)?.let {
            Url(it.id, it.originalUrl, it.shortCode)
        }

    override fun findByOriginalUrl(originalUrl: String): Url? {
        return urlJpaRepository.findByOriginalUrl(originalUrl)?.let {
            Url(it.id, it.originalUrl, it.shortCode)
        }
    }

}