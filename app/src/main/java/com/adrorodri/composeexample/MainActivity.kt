package com.adrorodri.composeexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.setContent
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.findNavController
import com.adrorodri.composeexample.ui.components.AboutText
import com.adrorodri.composeexample.ui.components.Toolbar
import com.adrorodri.composeexample.ui.theme.PokemonBaseTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SplashScreen()
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun SplashScreen() {
        PokemonBaseTheme {
            val navController = rememberNavController()
            val scaffoldState = rememberScaffoldState()
            Scaffold(
                backgroundColor = MaterialTheme.colors.background,
                topBar = {
                    Toolbar(
                        title = { Text(text = "Welcome!") },
                        onNavigationButtonClick = { scaffoldState.drawerState.open() }
                    )
                },
                bodyContent = {
                    Column(modifier = Modifier.fillMaxHeight()) {
                        Image(
                            modifier = Modifier.weight(1f),
                            bitmap = imageResource(id = R.drawable.pokemon_logo),
                            contentScale = ContentScale.Inside
                        )
                        TextButton(
                            modifier = Modifier.wrapContentSize(),
                            onClick = {

                            }) {
                            Text(text = "TEST")
                        }
                    }
                },
                bottomBar = { AboutText(text = "Example Jetpack Compose App") }
            )
        }
    }
}