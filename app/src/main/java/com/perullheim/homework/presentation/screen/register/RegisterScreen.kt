package com.perullheim.homework.presentation.screen.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.CircularProgressIndicator
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
import com.perullheim.homework.presentation.util.CollectLatestEffect

@Composable
fun RegisterScreen(
    onRegistrationSuccess: (String, String) -> Unit,
    viewModel: RegisterViewModel = hiltViewModel()
) {
    CollectLatestEffect(viewModel.uiEffect) { effect ->
        when (effect) {
            is RegisterUiEffect.ShowError -> { /* TODO */ }
            is RegisterUiEffect.NavigateToLogin -> onRegistrationSuccess(effect.email, effect.password)
        }
    }

    if (viewModel.uiState.isLoading.not()) {
        DecoratedScreen {
            RegisterContent(state = viewModel.uiState, onEvent = viewModel::onEvent)
        }
    } else {
        Box {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    }
}

@Composable
private fun RegisterContent(
    state: RegisterUiState,
    onEvent: (RegisterUiEvent) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {
        AuthTitle(text = stringResource(id = R.string.register))

        Spacer(modifier = Modifier.height(24.dp))

        Image(
            painter = painterResource(id = R.drawable.art_register),
            contentDescription = null
        )

        Spacer(modifier = Modifier.height(32.dp))

        AuthTextField(
            value = state.email,
            onValueChange = { onEvent(RegisterUiEvent.OnEmailChange(value = it)) },
            hint = stringResource(R.string.email),
            leadingIconImageVector = Icons.Default.Email,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        AuthTextField(
            value = state.username,
            onValueChange = { onEvent(RegisterUiEvent.OnUsernameChange(value = it)) },
            hint = stringResource(R.string.username),
            leadingIconImageVector = Icons.Default.Person,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )

        Spacer(modifier = Modifier.height(48.dp))

        PasswordTextField(
            value = state.password,
            onValueChange = { onEvent(RegisterUiEvent.OnPasswordChange(value = it)) },
            hint = stringResource(R.string.password),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
        )

        Spacer(modifier = Modifier.height(80.dp))

        AuthButton(
            onClick = { onEvent(RegisterUiEvent.OnRegisterClick) },
            title = stringResource(id = R.string.register),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 30.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RegisterContentPreview() {
    DecoratedScreen {
        RegisterContent(state = RegisterUiState(), onEvent = { })
    }
}