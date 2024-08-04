package com.tutor.retrofit_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tutor.retrofit_app.quote.screen.HomeQuoteScreen
import com.tutor.retrofit_app.quote.viewmodel.QuoteViewModel
import com.tutor.retrofit_app.ui.theme.Retrofit_appTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

	private val viewModel: QuoteViewModel by viewModels()

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			Retrofit_appTheme {
				val randomQuoteState by viewModel.quote.collectAsState()
				val navController = rememberNavController()
				NavHost(
					navController = navController,
					startDestination = QuoteRoute.Home.route
				) {
					composable(QuoteRoute.Home.route) {
						HomeQuoteScreen(
							navController,
							randomQuoteState
						)
					}
				}

			}
		}
	}
}

sealed class QuoteRoute(val route: String, ) {
	data object Home : QuoteRoute("Home")
	data object Detail : QuoteRoute("Detail")
	data object Search : QuoteRoute("Search")
	data object Favorite : QuoteRoute("Favorite")
	data object Profile : QuoteRoute("Profile")
	data object Setting : QuoteRoute("Setting")
	data object Help : QuoteRoute("Help")
	data object About : QuoteRoute("About")
	data object Contact : QuoteRoute("Contact")
	data object Login : QuoteRoute("Login")
	data object Register : QuoteRoute("Register")

}