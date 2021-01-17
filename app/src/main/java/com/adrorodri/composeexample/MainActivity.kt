package com.adrorodri.composeexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.compose.*
import com.adrorodri.composeexample.ui.ListScreen
import com.adrorodri.composeexample.ui.SplashScreen
import com.adrorodri.composeexample.ui.components.Toolbar
import com.adrorodri.composeexample.ui.theme.PokemonBaseTheme
import com.adrorodri.composeexample.ui.theme.dim

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun MainScreen() {
        PokemonBaseTheme {
            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

            Scaffold(
                scaffoldState = scaffoldState,
                backgroundColor = MaterialTheme.colors.background,
                drawerContent = {
                    Text(
                        text = "Pokemon API",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(16.dp)
                    )
                    Icon(Icons.Filled.Menu)
                    Text(text = "My user")
                    ALL_SCREENS.forEach { screen ->
                        DrawerRow(
                            title = screen.title,
                            selected = currentRoute == screen.route,
                            onClick = {
                                // This is the equivalent to popUpTo the start destination
                                navController.popBackStack(
                                    navController.graph.startDestination,
                                    false
                                )

                                // This if check gives us a "singleTop" behavior where we do not create a
                                // second instance of the composable if we are already on that destination
                                if (currentRoute != screen.route) {
                                    navController.navigate(screen.route)
                                }
                                scaffoldState.drawerState.close()
                            })
                    }
                },
                drawerScrimColor = dim,
                topBar = {
                    Toolbar(
                        title = { Text(text = "Welcome!") },
                        onNavigationButtonClick = { scaffoldState.drawerState.open() }
                    )
                }
            ) {
                NavHost(
                    navController = navController,
                    startDestination = AppScreen.SplashScreen.route
                ) {
                    ALL_SCREENS.forEach { composable(it.route, content = it.content) }
                }
            }
        }
    }
}

@Composable
private fun DrawerRow(title: String, selected: Boolean, onClick: () -> Unit) {
    val background =
        if (selected) MaterialTheme.colors.primary.copy(alpha = 0.12f) else Color.Transparent
    val textColor = if (selected) MaterialTheme.colors.primary else MaterialTheme.colors.onSurface
    ListItem(
        modifier = Modifier
            .clickable(onClick = onClick)
            .background(background)
    ) {
        Text(color = textColor, text = title)
    }
}

private sealed class AppScreen(
    val route: String,
    val title: String,
    val content: @Composable (backStackEntry: NavBackStackEntry) -> Unit
) {
    object SplashScreen : AppScreen(
        "timetopace",
        "SplashScreen",
        { backStackEntry -> SplashScreen(backStackEntry) })

    object ListScreen : AppScreen(
        "pacetotime",
        "ListScreen",
        { backStackEntry -> ListScreen(backStackEntry) })
}

private val ALL_SCREENS = listOf(AppScreen.SplashScreen, AppScreen.ListScreen)
private val SCREEN_MAP = ALL_SCREENS.associateBy { it.route }