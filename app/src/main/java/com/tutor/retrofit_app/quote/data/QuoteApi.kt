package com.tutor.retrofit_app.quote.data

import com.tutor.retrofit_app.quote.data.model.QuoteDto
import com.tutor.retrofit_app.quote.data.model.QuoteItem
import com.tutor.retrofit_app.quote.utils.ConstQuote
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApi {
	@GET(ConstQuote.QUOTES)
	suspend fun getQuotes(): Response<QuoteDto>

	@GET(ConstQuote.RANDOM)
	suspend fun getRandom(): Response<QuoteItem>

}