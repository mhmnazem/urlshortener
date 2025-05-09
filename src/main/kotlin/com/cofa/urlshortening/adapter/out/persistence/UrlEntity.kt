package com.cofa.urlshortening.adapter.out.persistence

import jakarta.persistence.*

@Entity
@Table(name = "url_mappings")
class UrlEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val shortCode: String? = null,

    @Column(name = "original_url", columnDefinition = "TEXT")
    val originalUrl: String

)