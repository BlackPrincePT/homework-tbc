package com.perullheim.homework.presentation.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ge.tkgroup.myapplication.ui.theme.Violet
import ge.tkgroup.myapplication.ui.theme.Violet30
import ge.tkgroup.myapplication.ui.theme.Violet70

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    leadingIconImageVector: ImageVector,
    modifier: Modifier = Modifier,
    trailingIcon: @Composable (() -> Unit)? = null,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(
                text = hint,
                fontSize = 24.sp,
                color = Violet30,
                modifier = Modifier
                    .padding(start = 40.dp)
            )
        },
        leadingIcon = {
            Icon(
                imageVector = leadingIconImageVector,
                contentDescription = null,
                tint = Violet,
                modifier = Modifier
                    .padding(start = 24.dp)
                    .size(40.dp)
            )
        },
        trailingIcon = trailingIcon,
        visualTransformation = visualTransformation,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Violet30,
            unfocusedContainerColor = Violet30
        ),
        shape = RoundedCornerShape(percent = 50),
        singleLine = true,
        modifier = modifier
    )
}

@Composable
fun PasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    hint: String,
    modifier: Modifier = Modifier
) {
    var isVisible by rememberSaveable { mutableStateOf(value = false) }

    AuthTextField(
        value = value,
        onValueChange = onValueChange,
        hint = hint,
        leadingIconImageVector = Icons.Default.Lock,
        trailingIcon = {
            val icon = if (isVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff

            IconButton(
                onClick = { isVisible = isVisible.not() },
                modifier = Modifier
                    .padding(end = 24.dp)
                    .size(32.dp)
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Violet70,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        },
        visualTransformation = if (isVisible) VisualTransformation.None else PasswordVisualTransformation(),
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun AuthTextFieldPreview() {
    AuthTextField(
        value = "",
        onValueChange = { },
        hint = "Username",
        leadingIconImageVector = Icons.Default.Person,
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )
}

@Preview(showBackground = true)
@Composable
private fun PasswordTextFieldPreview() {
    PasswordTextField(
        value = "",
        onValueChange = { },
        hint = "Password",
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
    )
}