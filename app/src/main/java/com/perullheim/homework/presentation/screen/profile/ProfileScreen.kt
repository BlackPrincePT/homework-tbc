package com.perullheim.homework.presentation.screen.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.perullheim.homework.R
import com.perullheim.homework.presentation.util.CollectLatestEffect

@Composable
fun ProfileScreen(
    onLogOutSuccess: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    CollectLatestEffect(viewModel.uiEffect) { effect ->
        when (effect) {
            ProfileUiEffect.LoggedOutSuccessfully -> onLogOutSuccess.invoke()
        }
    }

    ProfileContent(state = viewModel.uiState, onEvent = viewModel::onEvent)
}

@Composable
private fun ProfileContent(
    state: ProfileUiState,
    onEvent: (ProfileUiEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        state.currentSession?.let { session ->
            Text(
                text = session.email,
                fontSize = 24.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 64.dp)
            )
        }

        Button(
            onClick = { onEvent(ProfileUiEvent.LogOut) },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(64.dp)
        ) {
            Text(text = stringResource(R.string.log_out))
        }
    }
}