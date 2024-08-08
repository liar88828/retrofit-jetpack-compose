package com.tutor.retrofit_app.quote.data.remote.api

import com.tutor.retrofit_app.quote.data.model.QuoteDto
import com.tutor.retrofit_app.quote.data.model.QuoteItem
import com.tutor.retrofit_app.quote.utils.ConstQuote
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface QuoteApi {
	@GET(ConstQuote.QUOTES)
	suspend fun getQuotes(): Response<QuoteDto>

	@GET(ConstQuote.RANDOM)
	suspend fun getRandom(): Response<QuoteItem>

	@GET(ConstQuote.QUOTES_BY_TAG)
	suspend fun getQuotes(
		@Query("page") page: Int,
		@Query("limit") limit: Int,
		@Query("tag") tag: String = "technology"
	):QuoteDto

}
