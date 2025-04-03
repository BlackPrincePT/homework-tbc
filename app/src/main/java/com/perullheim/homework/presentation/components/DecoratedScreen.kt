package com.perullheim.homework.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex

@Composable
fun DecoratedScreen(
    content: @Composable () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        GradientCircle(
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(x = (-48).dp, y = (-48).dp)
                .zIndex(-1f)
        )

        content()

        SolidCircle(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .offset(x = (-64).dp, y = (64).dp)
                .zIndex(-1f)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DecoratedScreenPreview() {
    DecoratedScreen {  }
}