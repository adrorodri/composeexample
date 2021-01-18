package com.adrorodri.composeexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.viewinterop.viewModel
import androidx.navigation.compose.*
import com.adrorodri.composeexample.ui.AppScreen
import com.adrorodri.composeexample.ui.EditProfileScreen
import com.adrorodri.composeexample.ui.components.Toolbar
import com.adrorodri.composeexample.ui.theme.PokemonBaseTheme
import com.adrorodri.composeexample.ui.viewmodel.UserViewModel

class EditProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val scaffoldState = rememberScaffoldState(rememberDrawerState(DrawerValue.Closed))
            val navController = rememberNavController()
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.arguments?.getString(KEY_ROUTE)

            val userViewModel: UserViewModel = viewModel()
            val isUserLoggedIn = userViewModel.userLoggedIn.observeAsState()
            val userName = userViewModel.userName.observeAsState()

            val editProfileScreen = AppScreen(
                "editProfileScreen",
                "EditProfileScreen"
            ) {
                EditProfileScreen(onSave = {
                    userViewModel.userName.value = "Adrian"
                    finish()
                })
            }

            val screens = listOf(editProfileScreen)
            val screenMap = screens.associateBy { it.route }

            PokemonBaseTheme {
                Scaffold(
                    scaffoldState = scaffoldState,
                    backgroundColor = MaterialTheme.colors.background,
                    topBar = {
                        Toolbar(
                            title = { Text(text = "Edit Profile") }
                        )
                    },
                    bodyContent = {
                        NavHost(
                            navController = navController,
                            startDestination = editProfileScreen.route
                        ) {
                            screens.forEach { composable(it.route, content = it.content) }
                        }
                    }
                )
            }
        }
    }
}