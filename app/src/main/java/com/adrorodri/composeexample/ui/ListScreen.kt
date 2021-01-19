package com.adrorodri.composeexample.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.adrorodri.composeexample.R
import com.adrorodri.composeexample.data.user.model.User
import com.adrorodri.composeexample.ui.theme.buttons

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun UserHeader(
    user: User? = null,
    onLoginClick: (() -> Unit)? = null,
    onEditProfileClick: (() -> Unit)? = null
) {
    val userLoggedIn = user != null
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
                .preferredSize(100.dp),
            shape = CircleShape,
            elevation = 10.dp
        ) {
            Image(
                imageResource(id = R.drawable.example_landscape),
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )
        }
        if (userLoggedIn) {
            Text(text = "${user?.firstName} ${user?.lastName}")
        }
        TextButton(
            shape = buttons.medium,
            colors = ButtonDefaults.buttonColors(contentColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .background(MaterialTheme.colors.primary),
            onClick = {
                if (!userLoggedIn)
                    onLoginClick?.invoke()
                else
                    onEditProfileClick?.invoke()
            }) {
            Text(text = if (userLoggedIn) "Edit Profile" else "Login")
        }
    }
}