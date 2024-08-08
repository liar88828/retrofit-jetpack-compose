package com.tutor.retrofit_app.quote.screen

sealed class QuoteRoute(val route: String, ) {
	data object Home : QuoteRoute("Home")
	data object NewHome : QuoteRoute("NewHome")
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