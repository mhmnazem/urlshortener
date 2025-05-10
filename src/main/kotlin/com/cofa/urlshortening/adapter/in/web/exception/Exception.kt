package com.cofa.urlshortening.adapter.`in`.web.exception

class InvalidUrlException(message: String) : IllegalArgumentException(message)

class UrlNotFoundException(message: String) : NoSuchElementException(message)
