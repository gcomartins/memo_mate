package com.example.memomate.ui.pages.signUp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import kotlin.text.isBlank

@Composable
fun SignUp(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        SignUpScreen(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun SignUpScreen(navController: NavController, modifier: Modifier) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        SignUpForm(modifier.padding(innerPadding))
    }
}

@Composable
fun SignUpForm(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        MyEmailTextField()
        MyPasswordTextField()
    }
}

@Composable
fun MyEmailTextField(){
    MyTextField(
        label = "Email",
        placeholder = "seuemail@example.com",
        onValueChange = {},
        icon = Icons.Filled.Email,
        keyboardType = KeyboardType.Email
    )
}

@Composable
fun MyPasswordTextField(){
    MyTextField(
        label = "Senha",
        placeholder = "********",
        onValueChange = {},
        icon = Icons.Filled.Lock,
        keyboardType = KeyboardType.Password,
        mask = '*'
    )
}

@Composable
fun MyTextField(
    label: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    keyboardType: KeyboardType,
    mask: Char? = null
) {
    var text by remember { mutableStateOf("") }
    var isIdle by remember { mutableStateOf(true) }
    val isError = text.isBlank() && !isIdle

    TextField(
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
                tint = if (isError) MaterialTheme.colorScheme.error else Color.Black
            )
        },
        isError = isError,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
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
        visualTransformation = when(mask) {
            null -> VisualTransformation.None
            else -> PasswordVisualTransformation(mask = mask)
        }
    )
}

@Preview
@Composable
fun SignUpPreview() {
    SignUp(navController = NavController(LocalContext.current))
}