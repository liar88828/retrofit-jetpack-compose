package com.tutor.retrofit_app.quote.data.model

data class QuoteItem(
    val _id: String,
    val author: String,
    val authorSlug: String,
    val content: String,
    val dateAdded: String,
    val dateModified: String,
    val length: Int,
    val tags: List<String>
)


val exampleQuote = QuoteItem(
    _id = "5eb17aadb69dc744b4e944fe",
    author = "Albert Einstein",
    authorSlug = "albert-einstein",
    content = "Life is like riding a bicycle. To keep your balance you must keep moving.",
    dateAdded = "2024-08-08",
    dateModified = "2024-08-08",
    length = 64,
    tags = listOf("life", "inspirational", "balance")
)