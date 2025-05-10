package com.cofa.urlshortening.adapter.`in`.web

import com.cofa.urlshortening.adapter.`in`.web.dto.OriginalUrlResponse
import com.cofa.urlshortening.adapter.`in`.web.dto.ShortenUrlRequest
import com.cofa.urlshortening.adapter.`in`.web.dto.ShortenUrlResponse
import com.cofa.urlshortening.domain.port.incoming.UrlService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/urls")
class UrlController (private val urlService:UrlService){
    @PostMapping
     fun shorten(@RequestBody @Valid request: ShortenUrlRequest): ResponseEntity<ShortenUrlResponse> {
        val shortCode = urlService.shortenUrl(request.originalUrl)
        return ResponseEntity.ok(ShortenUrlResponse(shortCode))
    }

    @GetMapping("/{identifier}")
     fun resolve(@PathVariable identifier: String): ResponseEntity<OriginalUrlResponse> {
        val originalUrl = urlService.getOriginalUrl(identifier)
        return ResponseEntity.ok(OriginalUrlResponse(originalUrl))
    }
}