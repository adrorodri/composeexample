package com.adrorodri.composeexample.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.viewModel
import com.adrorodri.composeexample.data.user.model.User
import com.adrorodri.composeexample.ui.theme.buttons
import com.adrorodri.composeexample.ui.viewmodel.UserViewModel

@Composable
fun EditProfileScreen(onSave: ((newUser: User?) -> Unit)? = null) {
    val userViewModel: UserViewModel = viewModel()
    val currentUser = userViewModel.user.observeAsState()
    TextButton(
        shape = buttons.medium,
        colors = ButtonDefaults.buttonColors(contentColor = Color.White),
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(MaterialTheme.colors.primary),
        onClick = {
            onSave?.invoke(currentUser.value?.apply {
                firstName = "NameEdit"
                lastName = "EditProfile"
            })
        }
    ) {
        Text(text = "Save changes")
    }
}