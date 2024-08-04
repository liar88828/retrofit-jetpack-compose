package com.tutor.retrofit_app.quote.repository

import com.tutor.retrofit_app.quote.data.QuoteApi
import com.tutor.retrofit_app.quote.data.model.QuoteDto
import com.tutor.retrofit_app.quote.data.model.QuoteItem
import com.tutor.retrofit_app.quote.viewmodel.QuoteResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class QuoteRepository
@Inject constructor(
	private val quoteApi: QuoteApi
) {
	suspend fun getQuotes(): Flow<QuoteResult<QuoteDto>> = flow {
		val response = try {
			quoteApi.getQuotes()
		} catch (e: Exception) {
			e.printStackTrace()
			emit(QuoteResult.Error(e.message))
			return@flow
		}
		if (response.isSuccessful && response.body() != null) {
			emit(QuoteResult.Success(response.body()!!))
		}

	}

	suspend fun getRandom(): Flow<QuoteResult<QuoteItem>> = flow {
		val response = try {
			quoteApi.getRandom()
		} catch (e: Exception) {
			e.printStackTrace()
			emit(QuoteResult.Error(e.message))
			return@flow
		}
		if (response.isSuccessful && response.body() != null) {
			emit(QuoteResult.Success(response.body()!!))
		}

	}

}