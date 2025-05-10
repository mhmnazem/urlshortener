package com.cofa.urlshortening.adapter.out.persistence

import com.cofa.urlshortening.domain.model.Url

fun Url.toEntity(): UrlEntity = UrlEntity(
    id = this.id ?: 0,
    originalUrl = this.originalUrl,
    shortCode = this.shortCode
)

fun UrlEntity.toDomain(): Url = Url(
    id = this.id,
    originalUrl = this.originalUrl,
    shortCode = this.shortCode
)