package com.adrorodri.composeexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.compose.*
import com.adrorodri.composeexample.ui.*
import com.adrorodri.composeexample.ui.components.Toolbar
import com.adrorodri.composeexample.ui.theme.PokemonBaseTheme
import com.adrorodri.composeexample.ui.theme.dim
import com.adrorodri.composeexample.ui.viewmodel.UserViewModel

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }

    @Composable
    fun MainScreen() {
        val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
        val navController = rememberNavController()
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

        val userViewModel: UserViewModel = viewModel()
        val user = userViewModel.user.observeAsState()

        val home = AppScreen(
            "HomeScreen",
            "Home"
        ) {
            HomeScreen()
        }

        val splashScreen = AppScreen(
            "splashScreen",
            "Splash Screen"
        ) {
            SplashScreen(onStart = {
                navController.popBackStack()
                navController.navigate(home.route)
            })
        }

        val editProfile = AppScreen(
            "editProfileScreen",
            "Edit Profile"
        ) {
            EditProfileScreen(onSave = {
                userViewModel.editUser(it) {
                    navController.popBackStack()
                }
            })
        }

        val screens = listOf(splashScreen, home, editProfile)
        val screenMap = screens.associateBy { it.route }

        PokemonBaseTheme {
            Scaffold(
                scaffoldState = scaffoldState,
                backgroundColor = MaterialTheme.colors.background,
                drawerShape = RoundedCornerShape(
                    ZeroCornerSize,
                    CornerSize(40.dp),
                    CornerSize(40.dp),
                    ZeroCornerSize
                ),
                drawerContent = {
                    Text(
                        text = "Pokemon API",
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(16.dp)
                    )
                    UserHeader(
                        user = user.value,
                        onLoginClick = {
                            scaffoldState.drawerState.close()
                            userViewModel.loginUser("Example Username")
                        },
                        onEditProfileClick = {
                            scaffoldState.drawerState.close()
                            navController.navigate(editProfile.route)
                        }
                    )
                    screens.forEach { screen ->
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
                },
                bodyContent = {
                    NavHost(
                        navController = navController,
                        startDestination = splashScreen.route
                    ) {
                        screens.forEach { composable(it.route, content = it.content) }
                    }
                }
            )
        }
    }
}

@Composable
private fun DrawerRow(title: String, selected: Boolean, onClick: () -> Unit) {
    val background =
        if (selected)
            MaterialTheme.colors.primary.copy(alpha = 0.12f)
        else
            Color.Transparent

    val textColor =
        if (selected)
            MaterialTheme.colors.primary
        else
            MaterialTheme.colors.onSurface

    ListItem(
        modifier = Modifier
            .clickable(onClick = onClick)
            .background(background)
    ) {
        Text(color = textColor, text = title)
    }
}