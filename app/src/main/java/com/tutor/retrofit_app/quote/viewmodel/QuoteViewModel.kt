package com.tutor.retrofit_app.quote.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tutor.retrofit_app.quote.data.model.QuoteDto
import com.tutor.retrofit_app.quote.repository.QuoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QuoteViewModel
@Inject constructor(
	private val repository: QuoteRepository
) : ViewModel() {

	private val _quote = MutableStateFlow<QuoteResult<QuoteDto>>(QuoteResult.Loading())
	val quote: StateFlow<QuoteResult<QuoteDto>> = _quote

	init {
		getQuote()
	}

	fun getQuote() {
		viewModelScope.launch {

			val result = repository.getQuotes()
			result.collect {
				when (it) {
					is QuoteResult.Error -> _quote.value = QuoteResult.Error(it.message)
					is QuoteResult.Loading -> _quote.value = QuoteResult.Loading()
					is QuoteResult.Success -> {
						_quote.value = QuoteResult.Success(it.data!!)
					}
				}
			}

		}

	}
}

sealed class QuoteResult<T>(
	val data: T? = null,
	val message: String? = null
) {
	class Success<T>(data: T) : QuoteResult<T>(data)
	class Error<T>(message: String?, data: T? = null) : QuoteResult<T>(data, message)
	class Loading<T> : QuoteResult<T>()

}
