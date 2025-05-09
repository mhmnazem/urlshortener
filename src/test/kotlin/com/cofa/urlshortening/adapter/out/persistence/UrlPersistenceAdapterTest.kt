package com.cofa.urlshortening.adapter.out.persistence

import com.cofa.urlshortening.domain.model.Url
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*

class UrlPersistenceAdapterTest {
    private val jpaRepository: UrlJpaRepository = mock()
    private val adapter = UrlPersistenceAdapter(jpaRepository)


    @Test
    fun `should save and return Url`() {
        val url = Url(id = null, originalUrl = "https://google.com", shortCode = "abc123")
        val entity = UrlEntity(id = 1L, originalUrl = url.originalUrl, shortCode = url.shortCode)

        `when`(jpaRepository.save(any())).thenReturn(entity)

        val saved = adapter.save(url)

        assertEquals(1L, saved.id)
        assertEquals(url.originalUrl, saved.originalUrl)
        assertEquals(url.shortCode, saved.shortCode)
    }


    @Test
    fun `should find Url by shortCode`() {
        val entity = UrlEntity(1L, "abc123", "https://test.com")
        `when`(jpaRepository.findByShortCode("abc123")).thenReturn(entity)

        val result = adapter.findByShortCode("abc123")

        assertNotNull(result)
        assertEquals("https://test.com", result?.originalUrl)
    }

    @Test
    fun `should return null if shortCode not found`() {
        `when`(jpaRepository.findByShortCode("notfound")).thenReturn(null)

        val result = adapter.findByShortCode("notfound")

        assertNull(result)
    }

    @Test
    fun `should find Url by originalUrl`() {
        val entity = UrlEntity(2L, "xyz456", "https://test.com")
        `when`(jpaRepository.findByOriginalUrl("https://test.com")).thenReturn(entity)

        val result = adapter.findByOriginalUrl("https://test.com")

        assertNotNull(result)
        assertEquals("xyz456", result?.shortCode)
    }
}