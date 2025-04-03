package com.perullheim.homework.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.perullheim.homework.R
import com.perullheim.homework.presentation.components.DecoratedScreen
import com.perullheim.homework.presentation.util.CollectLatestEffect
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSessionRestored: () -> Unit,
    onAuthenticationRequired: () -> Unit,
    viewModel: SplashViewModel = hiltViewModel()
) {
    val startTime = rememberSaveable { System.currentTimeMillis() }

    CollectLatestEffect(viewModel.uiEffect) { effect ->

        val elapsedTime = System.currentTimeMillis() - startTime
        if (elapsedTime < 1000) {
            delay(timeMillis = 1000 - elapsedTime)
        }

        when (effect) {
            SplashUiEffect.NavigateToHome -> onSessionRestored()
            SplashUiEffect.NavigateToWelcome -> onAuthenticationRequired()
        }
    }

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