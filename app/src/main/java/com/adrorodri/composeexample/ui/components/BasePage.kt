package com.adrorodri.composeexample.ui.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.adrorodri.composeexample.ui.theme.dim

@Composable
fun BasePage(
    drawer: @Composable ColumnScope.() -> Unit,
    content: @Composable (paddingValues: PaddingValues) -> Unit,
    navHost: Unit
) {
    val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))

    Scaffold(
        scaffoldState = scaffoldState,
        backgroundColor = MaterialTheme.colors.background,
        drawerContent = drawer,
        drawerScrimColor = dim,
        topBar = {
            Toolbar(
                title = { Text(text = "Welcome!") },
                onNavigationButtonClick = { scaffoldState.drawerState.open() }
            )
        },
    ) {
        navHost
    }
}