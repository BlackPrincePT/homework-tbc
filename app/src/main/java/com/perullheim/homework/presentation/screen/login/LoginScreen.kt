package com.perullheim.homework.presentation.screen.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
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
import com.perullheim.homework.presentation.components.AuthTextField
import com.perullheim.homework.presentation.components.AuthTitle
import com.perullheim.homework.presentation.components.DecoratedScreen
import com.perullheim.homework.presentation.components.PasswordTextField

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: LoginViewModel = hiltViewModel()
) {


    DecoratedScreen {
        LoginContent(state = viewModel.uiState, onEvent = viewModel::onEvent)
    }
}

@Composable
private fun LoginContent(
    state: LoginUiState,
    onEvent: (LoginUiEvent) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        AuthTitle(text = stringResource(id = R.string.login))

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.art_login),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(32.dp))

        AuthTextField(
            value = state.username,
            onValueChange = { onEvent(LoginUiEvent.OnUsernameChange(value = it)) },
            hint = stringResource(R.string.username),
            leadingIconImageVector = Icons.Default.Person,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 30.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        PasswordTextField(
            value = state.password,
            onValueChange = { onEvent(LoginUiEvent.OnPasswordChange(value = it)) },
            hint = stringResource(R.string.password),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 30.dp)
        )

        Spacer(modifier = Modifier.height(80.dp))

        AuthButton(
            onClick = { onEvent(LoginUiEvent.OnLoginClick) },
            title = stringResource(id = R.string.login),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 30.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContentPreview() {
    DecoratedScreen {
        LoginContent(state = LoginUiState(), onEvent = { })
    }
}