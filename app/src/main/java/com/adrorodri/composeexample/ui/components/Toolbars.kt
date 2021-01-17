package com.adrorodri.composeexample.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable

@Composable
fun Toolbar(title: @Composable () -> Unit, onNavigationButtonClick: () -> Unit) {
    TopAppBar(
        title = title,
        backgroundColor = MaterialTheme.colors.primary,
        contentColor = MaterialTheme.colors.onPrimary,
        navigationIcon = {
            IconButton(
                onClick = onNavigationButtonClick
            ) {
                Icon(Icons.Filled.Menu)
            }
        })
}