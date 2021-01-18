package com.adrorodri.composeexample.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider

class AnyProvider : PreviewParameterProvider<Any> {
    override val values: Sequence<Any> = sequenceOf()
}