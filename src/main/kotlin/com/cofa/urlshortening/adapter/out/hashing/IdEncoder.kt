package com.cofa.urlshortening.adapter.out.hashing

/**
 * Port for encoding numeric IDs into short string representations.
 * Used to generate compact, URL-safe codes (e.g., Base62).
 */
interface IdEncoder {

    /**
     * Encodes the given numeric ID into a short string.
     *
     * @param id The numeric ID to encode (must be non-negative).
     * @return A short, encoded string representing the ID.
     */
    fun encode(id: Long): String
}