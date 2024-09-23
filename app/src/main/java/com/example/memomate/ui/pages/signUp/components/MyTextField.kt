package com.example.memomate.ui.pages.signUp.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun MyTextField(
    label: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    keyboardType: KeyboardType,
    mask: Char? = null,
    iconColor: (String) -> Color = { Color.Black },
    enabled: Boolean = true,
    modifier: Modifier = Modifier,
    value: String = ""
) {
    var text by remember { mutableStateOf(value) }
    var isIdle by remember { mutableStateOf(true) }
    val isError = text.isBlank() && !isIdle

    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        value = text,
        onValueChange = {
            isIdle = false
            text = it
            onValueChange(it)
        },
        label = {
            Text(
                text = label,
                color = if (isError) MaterialTheme.colorScheme.error else Color.Black
            )
        },
        placeholder = {
            Text(
                text = placeholder,
                color = Color.LightGray
            )
        },
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = label,
                tint = if (isError) MaterialTheme.colorScheme.error else iconColor(text)
            )
        },
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        enabled = enabled,
        singleLine = true,
        colors = TextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.LightGray,
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            focusedIndicatorColor = Color.Black,
            unfocusedIndicatorColor = Color.LightGray,
            errorIndicatorColor = MaterialTheme.colorScheme.error
        ),
        visualTransformation = when (mask) {
            null -> VisualTransformation.None
            else -> PasswordVisualTransformation(mask = mask)
        }
    )
}