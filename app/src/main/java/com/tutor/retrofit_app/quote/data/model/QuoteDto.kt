package com.tutor.retrofit_app.quote.data.model

data class QuoteDto(
    val count: Int,
    val lastItemIndex: Int,
    val page: Int,
    val results: List<QuoteItem>,
    val totalCount: Int,
    val totalPages: Int
)