package com.perullheim.homework.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ge.tkgroup.myapplication.ui.theme.Violet

@Composable
fun AuthButton(
    onClick: () -> Unit,
    title: String,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(containerColor = Violet)
    ) {
        Text(
            text = title,
            fontSize = 23.sp
        )
    }
}

@Preview
@Composable
private fun AuthButtonPreview() {
    AuthButton(
        onClick = { },
        title = "Login",
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )
}