package com.perullheim.homework.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.perullheim.homework.R
import com.perullheim.homework.presentation.components.DecoratedScreen

@Composable
fun SplashScreen(

) {

    DecoratedScreen {
        SplashContent()
    }
}

@Composable
private fun SplashContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painter = painterResource(id = R.drawable.art_welcome),
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SplashContentPreview() {
    DecoratedScreen {
        SplashContent()
    }
}