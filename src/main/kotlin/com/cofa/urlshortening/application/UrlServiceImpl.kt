package com.cofa.urlshortening.application

import com.cofa.urlshortening.adapter.`in`.web.exception.UrlNotFoundException
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
        urlRepository.findByOriginalUrl(originalUrl)?.let {
            return it.shortCode ?: throw IllegalStateException("Short code is null for existing URL")
        }

        val saved = urlRepository.save(Url(id = null, originalUrl = originalUrl, shortCode = ""))
        val code = idEncoder.encode(saved.id!!)
        val updated = saved.copy(shortCode = code)
        urlRepository.save(updated)
        return code
    }

    override fun getOriginalUrl(shortCode: String): String =
        urlRepository.findByShortCode(shortCode)?.originalUrl
            ?: throw UrlNotFoundException("Short URL $shortCode not found")
}