package com.tutor.retrofit_app.quote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.tutor.retrofit_app.quote.data.model.QuoteItem
import com.tutor.retrofit_app.quote.di.Graph
import com.tutor.retrofit_app.quote.repository.NewQuoteRepository
import kotlinx.coroutines.flow.Flow

class NewQuotesViewModel(
	private val repository: NewQuoteRepository = Graph.repository
) : ViewModel() {
	val quotes: Flow<PagingData<QuoteItem>> = repository
		.getQuotes()
		.cachedIn(viewModelScope)

}