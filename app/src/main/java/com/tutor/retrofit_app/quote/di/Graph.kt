package com.tutor.retrofit_app.quote.di

import android.content.Context
import com.tutor.retrofit_app.quote.data.remote.api.QuoteApi
import com.tutor.retrofit_app.quote.repository.NewQuoteRepository
import com.tutor.retrofit_app.quote.repository.NewQuoteRepositoryImpl
import com.tutor.retrofit_app.quote.utils.ConstQuote
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Graph {

	lateinit var quoteApi: QuoteApi
	val repository: NewQuoteRepository by lazy {
		NewQuoteRepositoryImpl(quoteApi)

	}

	fun provide(context: Context) {
		val contextType = "application/json".toMediaType()
		quoteApi = Retrofit.Builder()
			.baseUrl(ConstQuote.BASE_URL)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(QuoteApi::class.java)

	}
}