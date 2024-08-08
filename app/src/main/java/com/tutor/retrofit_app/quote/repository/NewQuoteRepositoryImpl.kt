package com.tutor.retrofit_app.quote.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tutor.retrofit_app.quote.data.model.QuoteItem
import com.tutor.retrofit_app.quote.data.pagination.QuotesPagingSource
import com.tutor.retrofit_app.quote.data.remote.api.QuoteApi
import kotlinx.coroutines.flow.Flow

class NewQuoteRepositoryImpl(
	private val api: QuoteApi
) : NewQuoteRepository {
	override fun getQuotes(): Flow<PagingData<QuoteItem>> {
		return Pager(
			config = PagingConfig(pageSize = 20, enablePlaceholders = false),
			pagingSourceFactory = { QuotesPagingSource(api) }
		).flow
	}
}