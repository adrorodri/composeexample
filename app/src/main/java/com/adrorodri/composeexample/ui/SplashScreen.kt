package com.adrorodri.composeexample.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adrorodri.composeexample.R
import com.adrorodri.composeexample.ui.components.AboutText
import com.adrorodri.composeexample.ui.theme.buttons

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashScreen(onStart: (() -> Unit)? = null) {
    var contentScale by remember { mutableStateOf(ContentScale.Inside) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxHeight()
    ) {
        Image(
            modifier = Modifier
                .weight(1f)
                .clickable(onClick = {
                    contentScale =
                        if (contentScale == ContentScale.Inside)
                            ContentScale.Crop
                        else
                            ContentScale.Inside
                }),
            bitmap = imageResource(id = R.drawable.pokemon_logo),
            contentScale = contentScale
        )
        TextButton(
            shape = buttons.medium,
            colors = ButtonDefaults.buttonColors(contentColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(MaterialTheme.colors.primary),
            onClick = {
                onStart?.invoke()
            }) {
            Text(text = "Start")
        }
        AboutText(text = "Example Jetpack Compose App")
    }
}