package com.perullheim.homework.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun AuthTitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        fontSize = 48.sp,
        fontWeight = FontWeight.Medium,
        modifier = modifier
    )
}

@Preview
@Composable
private fun AuthTitlePreview() {
    AuthTitle(text = "Welcome")
}