package com.cofa.urlshortening

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class UrlshorteningApplication

fun main(args: Array<String>) {
	runApplication<UrlshorteningApplication>(*args)
}
