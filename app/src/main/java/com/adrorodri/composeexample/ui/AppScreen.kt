package com.adrorodri.composeexample.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry

class AppScreen(
    val route: String,
    val title: String,
    val content: @Composable (backStackEntry: NavBackStackEntry) -> Unit
)