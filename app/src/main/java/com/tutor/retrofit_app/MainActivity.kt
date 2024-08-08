package com.tutor.retrofit_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import com.tutor.retrofit_app.quote.screen.NavigationApp
import com.tutor.retrofit_app.quote.viewmodel.NewQuotesViewModel
import com.tutor.retrofit_app.quote.viewmodel.QuoteViewModel
import com.tutor.retrofit_app.ui.theme.Retrofit_appTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	private val viewModel: QuoteViewModel by viewModels()
	private val quotesViewModel: NewQuotesViewModel by viewModels()
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			Retrofit_appTheme {

				NavigationApp(viewModel, quotesViewModel)
			}
		}
	}

}

