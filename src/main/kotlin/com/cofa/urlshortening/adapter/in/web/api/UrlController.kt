package com.cofa.urlshortening.adapter.`in`.web.api

import com.cofa.urlshortening.adapter.`in`.web.dto.OriginalUrlResponse
import com.cofa.urlshortening.adapter.`in`.web.dto.ShortenUrlRequest
import com.cofa.urlshortening.adapter.`in`.web.dto.ShortenUrlResponse
import com.cofa.urlshortening.domain.port.incoming.UrlService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class UrlController (private val urlService:UrlService) : IUrlController{
    @PostMapping
    override fun shorten(@RequestBody @Valid request: ShortenUrlRequest): ResponseEntity<ShortenUrlResponse> {
        val shortCode = urlService.shortenUrl(request.originalUrl)
        return ResponseEntity.ok(ShortenUrlResponse(shortCode))
    }

    @GetMapping("/{identifier}")
    override fun resolve(@PathVariable identifier: String): ResponseEntity<OriginalUrlResponse> {
        val originalUrl = urlService.getOriginalUrl(identifier)
        return ResponseEntity.ok(OriginalUrlResponse(originalUrl))
    }
}