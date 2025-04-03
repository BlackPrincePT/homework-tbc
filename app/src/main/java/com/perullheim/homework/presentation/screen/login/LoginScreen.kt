package com.perullheim.homework.presentation.screen.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.perullheim.homework.presentation.components.DecoratedScreen
import ge.tkgroup.myapplication.ui.theme.Cream
import ge.tkgroup.myapplication.ui.theme.Mint
import kotlin.math.cos
import kotlin.math.hypot
import kotlin.math.sin

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel()
) {


    DecoratedScreen {
        LoginContent()
    }
}

@Composable
private fun LoginContent() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
    ) {


    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContentPreview() {
    DecoratedScreen {
        LoginContent()
    }
}