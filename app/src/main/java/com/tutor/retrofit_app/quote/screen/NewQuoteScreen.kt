package com.tutor.retrofit_app.quote.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.tutor.retrofit_app.quote.data.model.QuoteItem
import com.tutor.retrofit_app.quote.data.model.exampleQuote
import com.tutor.retrofit_app.quote.screen.component.MyBottomNavigationBar
import com.tutor.retrofit_app.quote.screen.component.MyCenterAlignTopAppBar
import com.tutor.retrofit_app.quote.screen.component.MyFloatingActionButton
import com.tutor.retrofit_app.quote.screen.component.MySidebar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewQuoteScreen(
	navController: NavHostController,
	quotes: LazyPagingItems<QuoteItem>,
	modifier: Modifier = Modifier,
) {
	val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
	val scope = rememberCoroutineScope()
	val scrollBehavior =
		TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

	ModalNavigationDrawer(
		drawerState = drawerState,
		drawerContent = { MySidebar(navController) },
	) {
		Scaffold(
			modifier = modifier
				.fillMaxSize()
				.nestedScroll(scrollBehavior.nestedScrollConnection),
			floatingActionButton = { MyFloatingActionButton() },
			bottomBar = { MyBottomNavigationBar() },
			topBar = { MyCenterAlignTopAppBar(scrollBehavior, scope, drawerState) },
		) { innerPadding ->
			Surface(
				modifier = modifier.padding(innerPadding)
			) {
				LazyColumn {
					items(
						count = quotes.itemCount,
						key = quotes.itemKey(),
						contentType = quotes.itemContentType()
					) { index ->
						val quote = quotes[index]
						if (quote != null) {
							QuoteCard(quote)
						}
					}
				}
			}
		}
	}
}

@Composable
fun QuoteCard(
	item: QuoteItem,
	modifier: Modifier = Modifier
) {
	Column(
		modifier = modifier
			.wrapContentWidth()
			.padding(12.dp)
			.background(MaterialTheme.colorScheme.primaryContainer)
			.padding(12.dp)
	) {
		Icon(
			imageVector = Icons.Default.FormatQuote,
			contentDescription = "Icon Quote",
			modifier = Modifier.padding(8.dp)

		)
		Text(
			text = item.content,
			style = MaterialTheme.typography.bodyLarge,
			color = MaterialTheme.colorScheme.onBackground,
			modifier = modifier.padding(start = 8.dp, end = 8.dp)
		)
		Spacer(modifier = Modifier.height(8.dp))
		Box(modifier = Modifier.fillMaxWidth()) {
			Text(
				text = item.author,
				style = MaterialTheme.typography.bodyLarge,
				color = MaterialTheme.colorScheme.onBackground,
				modifier = modifier
					.align(Alignment.CenterEnd)
					.padding(12.dp)
			)
			Spacer(modifier = modifier.height(8.dp))
		}
	}
}

@Preview(showBackground = true)
@Composable
private fun QuoteCardPrev() {
	QuoteCard(exampleQuote)
}