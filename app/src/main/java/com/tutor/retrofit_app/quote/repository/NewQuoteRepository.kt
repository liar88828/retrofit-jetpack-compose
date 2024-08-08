package com.tutor.retrofit_app.quote.repository

import androidx.paging.PagingData
import com.tutor.retrofit_app.quote.data.model.QuoteItem
import kotlinx.coroutines.flow.Flow

interface NewQuoteRepository {
	fun getQuotes(): Flow<PagingData<QuoteItem>>
}