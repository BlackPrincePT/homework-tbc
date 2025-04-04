package com.perullheim.homework.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ge.tkgroup.myapplication.ui.theme.Cream
import ge.tkgroup.myapplication.ui.theme.Green20
import ge.tkgroup.myapplication.ui.theme.Mint

@Composable
fun GradientCircle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(182.dp)
            .rotate(degrees = 180f)
            .background(
                brush = Brush.linearGradient(colors = listOf(Mint, Cream)),
                shape = CircleShape
            )
    )
}

@Composable
fun SolidCircle(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(182.dp)
            .background(color = Green20, shape = CircleShape)
    )
}

@Preview
@Composable
private fun GradientBoxPreview() {
    GradientCircle()
}

@Preview
@Composable
private fun SolidBoxPreview() {
    SolidCircle()
}