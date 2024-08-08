package com.tutor.retrofit_app.quote.screen.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.runtime.Composable
import com.tutor.retrofit_app.quote.screen.MyNavigationalItem

@Composable
  fun MyBottomNavigationBar() {
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
}