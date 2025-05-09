package com.cofa.urlshortening.adapter.out.hashing

interface IdEncoder {
    fun encode(id: Long): String
}