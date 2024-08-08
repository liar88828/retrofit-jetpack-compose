package com.tutor.retrofit_app.quote.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.tutor.retrofit_app.quote.viewmodel.NewQuotesViewModel
import com.tutor.retrofit_app.quote.viewmodel.QuoteViewModel

@Composable
fun NavigationApp(
	viewModel: QuoteViewModel,
	quotesViewModel: NewQuotesViewModel
) {
	val randomQuoteState by viewModel.quote.collectAsState()
	val navController = rememberNavController()
	val quotesPagingItems = quotesViewModel.quotes.collectAsLazyPagingItems()
	NavHost(
		navController = navController,
		startDestination = QuoteRoute.NewHome.route
	) {
		composable(QuoteRoute.Home.route) {
			HomeQuoteScreen(
				navController,
				randomQuoteState
			)
		}
		composable(
			QuoteRoute.NewHome.route,
		) {
			NewQuoteScreen(
				navController,
				quotesPagingItems,
				)
		}
	}
}