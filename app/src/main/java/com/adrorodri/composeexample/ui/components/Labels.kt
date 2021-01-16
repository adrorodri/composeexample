package com.adrorodri.composeexample.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.adrorodri.composeexample.ui.theme.textHint


@Composable
fun AboutText(text: String) {
    Text(
        text = text,
        color = textHint,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth()
    )
}