package de.yehaw.cnsacatalogservice.application.domain.model

data class Book(
    val isbn: String,
    val title: String,
    val author: String,
    val price: Double,
    val publisher: String,
)
