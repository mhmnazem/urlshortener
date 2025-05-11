package com.cofa.urlshortening.adapter.out.hashing

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class Base62IdEncoderTest {
    private val encoder: IdEncoder = Base62IdEncoder()

    @Test
    fun `should encode zero correctly`() {
        assertEquals("a", encoder.encode(0))
    }

    @Test
    fun `should encode small number correctly`() {
        assertEquals("ba", encoder.encode(62))
    }

    @Test
    fun `should encode large number correctly`() {
        assertEquals("gFT3kBoXAZ1", encoder.encode(5465465464655465455))
    }

    @Test
    fun `should throw exception for negative id`() {
        assertThrows<IllegalArgumentException> {
            encoder.encode(-1)
        }
    }
}