package com.cofa.urlshortening.adapter.out.hashing

import org.springframework.stereotype.Component

@Component
class Base62IdEncoder : IdEncoder {

    override fun encode(id: Long): String {
        require(id >= 0) { "ID must be non-negative" }

        if (id == 0L) return BASE62_ALPHABET[0].toString()

        var num = id
        val result = StringBuilder()

        while (num > 0) {
            val remainder = (num % BASE).toInt()
            result.append(BASE62_ALPHABET[remainder])
            num /= BASE
        }
        return result.reverse().toString()
    }

    companion object {
        const val BASE62_ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789"
        private const val BASE = 62
    }
}