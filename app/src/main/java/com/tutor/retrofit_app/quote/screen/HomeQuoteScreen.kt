package com.tutor.retrofit_app.quote.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tutor.retrofit_app.quote.data.model.QuoteDto
import com.tutor.retrofit_app.quote.data.model.QuoteItem
import com.tutor.retrofit_app.quote.screen.component.MyBottomNavigationBar
import com.tutor.retrofit_app.quote.screen.component.MyCenterAlignTopAppBar
import com.tutor.retrofit_app.quote.screen.component.MyFloatingActionButton
import com.tutor.retrofit_app.quote.screen.component.MySidebar
import com.tutor.retrofit_app.quote.viewmodel.QuoteResult

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeQuoteScreen(
	navController: NavHostController,
	state: QuoteResult<QuoteDto>, modifier: Modifier = Modifier
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
				Column(
					modifier.padding(20.dp)
				) {
//					Text(
//						"Home Quote", fontSize = 28.sp, fontWeight = FontWeight.Medium
//					)

					when (state) {
						is QuoteResult.Error -> Text("Error")

						is QuoteResult.Loading -> Text("Loading")

						is QuoteResult.Success -> {
							LazyColumn(
								verticalArrangement = Arrangement.spacedBy(10.dp)
							) {
								items(state.data?.results ?: emptyList()) { item ->
									MyQuoteCard(item)
								}
							}
						}
					}
				}
			}

		}
	}
}

@Composable
private fun MyQuoteCard(
	item: QuoteItem
) {
	Card(
		modifier = Modifier.fillMaxSize(),

		) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(16.dp),
			verticalArrangement = Arrangement.spacedBy(10.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(
				item.content, fontSize = 20.sp, fontStyle = FontStyle.Italic
			)
			Text(item.author, fontSize = 16.sp, fontWeight = FontWeight.Medium)
//			Text(
//				"Author Slug : ${item.authorSlug}", fontSize = 20.sp
//			)
			Row {
				Text("Tags : ")
				item.tags.map { Text("$it,") }
			}
			Icon(
				imageVector = Icons.Default.FormatQuote, contentDescription = "Icon Quote"
			)
		}
	}
}


@Composable
fun RowScope.MyNavigationalItem(
	select: Boolean, onClick: () -> Unit, item: ImageVector, title: String
) {
	NavigationBarItem(selected = select, onClick = onClick, icon = {
		Icon(
			imageVector = item, contentDescription = title
		)
	}, label = {
		Text(title)
	})
}

@Preview
@Composable
private fun HomeQuoteScreenPrev() {
	HomeQuoteScreen(
		NavHostController(LocalContext.current), QuoteResult.Success(
			QuoteDto(
				0, 0, 0,
				results = listOf(

					QuoteItem(
						author = "Dictumfinibus",
						authorSlug = "Dictumfinibus",
						content = "Dictumfinibus",
						dateAdded = "Dictumfinibus",
						dateModified = "Dictumfinibus",
						length = 0,
						_id = "Dictumfinibus",
						tags = listOf(
							"Dictumfinibus"
						)

					),
				),
				totalCount = 0,
				totalPages = 0,

				)
		)
	)
}

@Preview(showBackground = true)
@Composable
private fun TestOnly() {
	MySidebar(
		NavHostController(LocalContext.current)
	)
}

fun String.capitalized(): String {
	return this.replaceFirstChar {
		if (it.isLowerCase()) it.titlecase(java.util.Locale.getDefault())
		else it.toString()
	}
}