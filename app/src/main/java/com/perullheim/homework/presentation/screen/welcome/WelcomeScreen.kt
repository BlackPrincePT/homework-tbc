package com.perullheim.homework.presentation.screen.welcome

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.perullheim.homework.R
import com.perullheim.homework.presentation.components.AuthButton
import com.perullheim.homework.presentation.components.AuthTitle
import com.perullheim.homework.presentation.components.DecoratedScreen
import com.perullheim.homework.presentation.screen.register.RegisterUiEvent
import ge.tkgroup.myapplication.ui.theme.Violet30

@Composable
fun WelcomeScreen(
    viewModel: WelcomeViewModel = hiltViewModel()
) {

    DecoratedScreen {
        WelcomeContent(onEvent = viewModel::onEvent)
    }
}

@Composable
private fun WelcomeContent(
    onEvent: (WelcomeUiEvent) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        AuthTitle(text = stringResource(id = R.string.welcome))

        Spacer(modifier = Modifier.height(64.dp))

        Image(
            painter = painterResource(id = R.drawable.art_welcome),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(32.dp))

        AuthButton(
            onClick = { onEvent(WelcomeUiEvent.OnRegisterClick) },
            title = stringResource(id = R.string.register),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 30.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        AuthButton(
            onClick = { onEvent(WelcomeUiEvent.OnLoginClick) },
            title = stringResource(id = R.string.login),
            backgroundColor = Violet30,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 30.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun WelcomeContentPreview() {
    DecoratedScreen {
        WelcomeContent(onEvent = { })
    }
}