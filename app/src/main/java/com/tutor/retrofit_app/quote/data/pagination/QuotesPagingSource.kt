package com.tutor.retrofit_app.quote.data.pagination

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.tutor.retrofit_app.quote.data.model.QuoteDto
import com.tutor.retrofit_app.quote.data.model.QuoteItem
import com.tutor.retrofit_app.quote.data.remote.api.QuoteApi
import okio.IOException
import retrofit2.HttpException

class QuotesPagingSource(
	private val api: QuoteApi
) : PagingSource<Int, QuoteItem>() {

	companion object {
		private const val STARTING_KEY = 1
	}

	override fun getRefreshKey(state: PagingState<Int, QuoteItem>): Int? {
		return state.anchorPosition
	}

	override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QuoteItem> {

		val startKey = params.key ?: STARTING_KEY
		return try {
			val quotes = api.getQuotes(startKey, params.loadSize)
			val nextKey = if (quotes.results.isEmpty()) null else quotes.page + 1

			LoadResult.Page(
				data = quotes.results,
				prevKey = if (startKey == STARTING_KEY) null else startKey - 1,
//				nextKey = if (quotes.results.isEmpty()) null else startKey + 1
				nextKey = nextKey
			)
		} catch (e: IOException) {
			return LoadResult.Error(e)
		} catch (e: HttpException) {
			return LoadResult.Error(e)
		}

	}
}