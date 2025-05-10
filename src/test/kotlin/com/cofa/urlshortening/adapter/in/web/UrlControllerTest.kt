package com.cofa.urlshortening.adapter.`in`.web

import com.cofa.urlshortening.adapter.`in`.web.dto.ShortenUrlRequest
import com.cofa.urlshortening.domain.port.incoming.UrlService
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(controllers = [UrlController::class])
class UrlControllerT {


    @Autowired
    lateinit var mockMvc: MockMvc

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockBean
    lateinit var urlService: UrlService

    @Test
    fun `should return shortened URL`() {
        val request = ShortenUrlRequest("https://example.com")
        `when`(urlService.shortenUrl(request.originalUrl)).thenReturn("abc123")

        mockMvc.post("/api/v1/urls") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status { isOk() }
            jsonPath("$.shortCode") { value("abc123") }
        }
    }

    @Test
    fun `should return 400 Bad Request when originalUrl is blank`() {
        val request = ShortenUrlRequest(originalUrl = "")

        mockMvc.post("/api/v1/urls") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status().isBadRequest
        }
    }

    @Test
    fun `should return 400 Bad Request when originalUrl is invalid`() {
        val request = ShortenUrlRequest(originalUrl = "ftp://invalid.com")

        mockMvc.post("/api/v1/urls") {
            contentType = MediaType.APPLICATION_JSON
            content = objectMapper.writeValueAsString(request)
        }.andExpect {
            status().isBadRequest
        }
    }
}