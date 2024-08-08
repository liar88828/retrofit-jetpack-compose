package com.tutor.retrofit_app.quote.screen.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MyCenterAlignTopAppBar(
	scrollBehavior: TopAppBarScrollBehavior,
	scope: CoroutineScope,
	drawerState: DrawerState
) {
	CenterAlignedTopAppBar(
		scrollBehavior = scrollBehavior,
		navigationIcon = {
			IconButton(onClick = { scope.launch { drawerState.open() } }) {
				Icon(imageVector = Icons.Default.Menu, contentDescription = "Icon Menu")
			}
		},
		actions = {
			IconButton(onClick = {}) {
				Icon(imageVector = Icons.Default.Share, contentDescription = "Icon Share")
			}
		},
		title = {
			Text("Home Quote")
		}
	)
}