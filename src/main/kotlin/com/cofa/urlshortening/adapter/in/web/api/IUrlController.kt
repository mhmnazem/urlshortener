package com.cofa.urlshortening.adapter.`in`.web.api

import com.cofa.urlshortening.adapter.`in`.web.dto.OriginalUrlResponse
import com.cofa.urlshortening.adapter.`in`.web.dto.ShortenUrlRequest
import com.cofa.urlshortening.adapter.`in`.web.dto.ShortenUrlResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RequestMapping("/api/v1/urls")
interface IUrlController {


    @Operation(summary = "Shorten a given URL")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Shortened URL returned"),
            ApiResponse(responseCode = "400", description = "Invalid input", content = [Content()])
        ]
    )
    @PostMapping
    fun shorten(@RequestBody request: ShortenUrlRequest): ResponseEntity<ShortenUrlResponse>


    @Operation(summary = "Resolve a short code to original URL")
    @ApiResponses(
        value = [
            ApiResponse(responseCode = "200", description = "Original URL returned"),
            ApiResponse(responseCode = "404", description = "URL not found", content = [Content()])
        ]
    )
    @GetMapping("/{identifier}")
    fun resolve(@PathVariable identifier: String): ResponseEntity<OriginalUrlResponse>
}