package com.cofa.urlshortening.application

import com.cofa.urlshortening.adapter.out.hashing.IdEncoder
import com.cofa.urlshortening.domain.model.Url
import com.cofa.urlshortening.domain.port.outgoing.UrlRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.Mockito.mock
import org.mockito.kotlin.*


class UrlServiceImplTest {
    private val urlRepository: UrlRepository = mock()
    private val idEncoder: IdEncoder = mock()
    private val service = UrlServiceImpl(urlRepository, idEncoder)


    @Test
    fun `should return existing short code if URL already exists`() {
        val existing = Url(id = 1L, originalUrl = "https://test.com", shortCode = "abc123")
        whenever(urlRepository.findByOriginalUrl("https://test.com")).thenReturn(existing)

        val result = service.shortenUrl("https://test.com")

        assertEquals("abc123", result)
        verify(urlRepository, never()).save(any())
    }

    @Test
    fun `should generate and return new short code if URL is new`() {
        val inputUrl = "https://new.com"
        val saved = Url(id = 100L, originalUrl = inputUrl, shortCode = "")
        val final = saved.copy(shortCode = "xyz789")

        whenever(urlRepository.findByOriginalUrl(inputUrl)).thenReturn(null)
        whenever(urlRepository.save(any())).thenReturn(saved, final)
        whenever(idEncoder.encode(100L)).thenReturn("xyz789")

        val result = service.shortenUrl(inputUrl)

        assertEquals("xyz789", result)
        verify(urlRepository, times(2)).save(any())
    }

    @Test
    fun `should throw if existing shortCode is null`() {
        val broken = Url(id = 2L, originalUrl = "https://bad.com", shortCode = null)
        whenever(urlRepository.findByOriginalUrl("https://bad.com")).thenReturn(broken)

        assertThrows<IllegalStateException> {
            service.shortenUrl("https://bad.com")
        }
    }

    @Test
    fun `should return original URL for valid short code`() {
        val url = Url(1L, "https://abc.com", "abc123")
        whenever(urlRepository.findByShortCode("abc123")).thenReturn(url)

        val result = service.getOriginalUrl("abc123")

        assertEquals("https://abc.com", result)
    }

    @Test
    fun `should throw if short code is not found`() {
        whenever(urlRepository.findByShortCode("notfound")).thenReturn(null)

        assertThrows<NoSuchElementException> {
            service.getOriginalUrl("notfound")
        }
    }
}