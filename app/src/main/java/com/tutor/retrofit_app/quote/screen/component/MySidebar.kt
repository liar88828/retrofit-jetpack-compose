package com.tutor.retrofit_app.quote.screen.component

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Help
import androidx.compose.material.icons.automirrored.filled.Login
import androidx.compose.material.icons.filled.AppRegistration
import androidx.compose.material.icons.filled.Contacts
import androidx.compose.material.icons.filled.Details
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.HomeMax
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.tutor.retrofit_app.R
import com.tutor.retrofit_app.quote.screen.QuoteRoute
import com.tutor.retrofit_app.quote.screen.capitalized

@Composable
fun MySidebar(
	navController: NavHostController,
	modifier: Modifier = Modifier
) {
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
			MySidebarItem(
				QuoteRoute.Home.route,
				true,
				{ navController.navigate(QuoteRoute.Home.route) },
				Icons.Default.Home
			)
			MySidebarItem(
				QuoteRoute.NewHome.route,
				true,
				{ navController.navigate(QuoteRoute.NewHome.route) },
				Icons.Default.HomeMax
			)
			MySidebarItem(QuoteRoute.Detail.route, true, { /*TODO*/ }, Icons.Default.Details, 100)
			MySidebarItem(QuoteRoute.Favorite.route, true, { /*TODO*/ }, Icons.Default.Favorite)
			MySidebarItem(
				QuoteRoute.Help.route, true, { /*TODO*/ }, Icons.AutoMirrored.Filled.Help, 10000
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
fun MySidebarItem(
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
