package com.tutor.retrofit_app.quote.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Card
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationBar
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tutor.retrofit_app.QuoteRoute
import com.tutor.retrofit_app.R
import com.tutor.retrofit_app.quote.data.model.QuoteDto
import com.tutor.retrofit_app.quote.data.model.QuoteItem
import com.tutor.retrofit_app.quote.viewmodel.QuoteResult
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeQuoteScreen(
	randomQuoteState: NavHostController, state: QuoteResult<QuoteDto>, modifier: Modifier = Modifier
) {
	val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
	val scope = rememberCoroutineScope()
	val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(rememberTopAppBarState())

	ModalNavigationDrawer(
		drawerState = drawerState,
		drawerContent = {
			MySidebar()
		},
	) {

		Scaffold(


			modifier = modifier
				.fillMaxSize()
				.nestedScroll(scrollBehavior.nestedScrollConnection),
			floatingActionButton = {
				FloatingActionButton(onClick = {}) {
					Icon(
						imageVector = Icons.Default.Search, contentDescription = "Search"
					)
				}
			},

			bottomBar = {
				NavigationBar {
					MyNavigationalItem(true, { }, Icons.Default.Home, "Home")
					MyNavigationalItem(false, { }, Icons.Default.Person, "Person")
					FloatingActionButton(onClick = { }) {
						Icon(
							imageVector = Icons.Default.Add, contentDescription = "Add"
						)
					}
					MyNavigationalItem(true, { }, Icons.Default.Book, "Book")
					MyNavigationalItem(true, { }, Icons.Default.Settings, "Settings")
				}
			},
			topBar = {
				CenterAlignedTopAppBar(
					scrollBehavior = scrollBehavior,
					navigationIcon = {
						IconButton(onClick = {
							scope.launch {
								drawerState.open()
							}
						}) {

							Icon(
								imageVector = Icons.Default.Menu, contentDescription = "Icon Menu"
							)
						}
					},
					actions = {
						IconButton(onClick = {}) {
							Icon(
								imageVector = Icons.Default.Share, contentDescription = "Icon Share"
							)
						}
					},
					title = {
						Text("Home Quote")
					}

				)
			},
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
private fun MySidebar(modifier: Modifier = Modifier) {
	ModalDrawerSheet(
		drawerContainerColor = MaterialTheme.colorScheme.background,
		drawerContentColor = MaterialTheme.colorScheme.onBackground,
		modifier = modifier
	) {
		Column(
			modifier = modifier
				.fillMaxWidth()
				.background(
					MaterialTheme.colorScheme.primary.copy(0.5f),
				)
				.padding(16.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
		) {
			Image(
				painter = painterResource(id = R.drawable.ic_launcher_foreground),
				contentDescription = "Avatar",
				modifier = Modifier
					.padding(16.dp)
					.size(100.dp)
					.background(
						MaterialTheme.colorScheme.onPrimary.copy(0.2f),
						shape = CircleShape
					)
					.border(
						2.dp,
						color = MaterialTheme.colorScheme.onPrimary,
						shape = CircleShape
					),

				)
			Text(
				"Avatar Name", fontSize = 28.sp,
				fontWeight = FontWeight.Medium
			)
		}
		Column(
			modifier = modifier.padding(horizontal = 16.dp),
			horizontalAlignment = Alignment.CenterHorizontally,
			verticalArrangement = Arrangement.spacedBy(8.dp)
		) {
			HorizontalDivider(modifier = modifier.padding(vertical = 20.dp))

			MySidebarItem(QuoteRoute.Home.route, true, { /*TODO*/ }, Icons.Default.Home)
			MySidebarItem(
				QuoteRoute.Detail.route, true, { /*TODO*/ }, Icons.Default.Details, 100
			)
			MySidebarItem(
				QuoteRoute.Favorite.route, true, { /*TODO*/ }, Icons.Default.Favorite
			)
			MySidebarItem(
				QuoteRoute.Help.route,
				true,
				{ /*TODO*/ },
				Icons.AutoMirrored.Filled.Help,
				100000000
			)
			MySidebarItem(
				QuoteRoute.Contact.route, true, { /*TODO*/ }, Icons.Default.Contacts
			)
			MySidebarItem(
				QuoteRoute.About.route, true, { /*TODO*/ }, Icons.Default.Info, 43
			)
			MySidebarItem(
				QuoteRoute.Login.route, true, { /*TODO*/ }, Icons.AutoMirrored.Filled.Login
			)
			MySidebarItem(
				QuoteRoute.Register.route, true, { /*TODO*/ }, Icons.Default.AppRegistration
			)
		}
	}
}

@SuppressLint("DefaultLocale")
@Composable
private fun MySidebarItem(
	title: String, select: Boolean, onClick: () -> Unit, Icom: ImageVector, count: Int = 0
) {
	NavigationDrawerItem(badge = {
		BadgedBox(badge = {
			if (count > 0) {
				Badge(
					containerColor = MaterialTheme.colorScheme.primary,
					contentColor = MaterialTheme.colorScheme.onPrimary
				) {
					Text(count.toString())
				}
			}
		}) {
			Icon(
				imageVector = Icons.AutoMirrored.Filled.ArrowForward,
				contentDescription = "ArrowForward"
			)
		}
	},
		label = { Text(title.capitalized()) },
		selected = select,
		onClick = { onClick() },
		icon = {
			Icon(
				imageVector = Icom, contentDescription = title
			)
		})
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
	MySidebar()
}

fun String.capitalized(): String {
	return this.replaceFirstChar {
		if (it.isLowerCase()) it.titlecase(java.util.Locale.getDefault())
		else it.toString()
	}
}