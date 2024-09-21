package com.example.memomate.ui.pages.signUp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun SignUpActivity(navController: NavController) {
    SignUpForm()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        SignUpForm(
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun SignUpForm(modifier: Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    ) {
        Column(
            modifier
                .weight(18f)
                .fillMaxWidth()
                .wrapContentSize(align = Alignment.Center)
                .padding(horizontal = 16.dp)
        ) {
            MyNameTextField {
                SignUpForm.name = it
            }
            MyLastNameTextField {
                SignUpForm.lastName = it
            }
            MyBirthDateTextField {
                SignUpForm.birthDate = it
            }
            MyEmailTextField {
                SignUpForm.email = it
            }
            MyPasswordTextField {
                SignUpForm.password = it
            }
            MyConfirmPasswordTextField {
                SignUpForm.confirmPassword = it
            }
        }
        MyConfirmSignUpButton(modifier.weight(2f))
    }
}

@Composable
fun MyNameTextField(onValueChange: (String) -> Unit) {
    MyTextField(
        label = "Nome",
        placeholder = "Seu nome",
        onValueChange = onValueChange,
        icon = Icons.Filled.AccountCircle,
        keyboardType = KeyboardType.Text
    )
}

@Composable
fun MyLastNameTextField(onValueChange: (String) -> Unit) {
    MyTextField(
        label = "Sobrenome",
        placeholder = "Seu sobrenome",
        onValueChange = onValueChange,
        icon = Icons.Filled.AccountCircle,
        keyboardType = KeyboardType.Text
    )
}

@Composable
fun MyBirthDateTextField(onValueChange: (String) -> Unit) {
    MyTextField(
        label = "Data de Nascimento",
        placeholder = "DD/MM/AAAA",
        onValueChange = onValueChange,
        icon = Icons.Filled.DateRange,
        keyboardType = KeyboardType.Email
    )
}

@Composable
fun MyEmailTextField(onValueChange: (String) -> Unit) {
    MyTextField(
        label = "Email",
        placeholder = "seuemail@example.com",
        onValueChange = onValueChange,
        icon = Icons.Filled.Email,
        keyboardType = KeyboardType.Email
    )
}

@Composable
fun MyPasswordTextField(onValueChange: (String) -> Unit) {
    MyTextField(
        label = "Senha",
        placeholder = "********",
        onValueChange = onValueChange,
        icon = Icons.Filled.Lock,
        keyboardType = KeyboardType.Password,
        mask = '*'
    )
}

@Composable
fun MyConfirmPasswordTextField(onValueChange: (String) -> Unit) {
    var isIdle by remember { mutableStateOf(true) }

    MyTextField(
        label = "Confirmar Senha",
        placeholder = "********",
        onValueChange = {
            isIdle = false
            onValueChange(it)
        },
        icon = Icons.Filled.CheckCircle,
        keyboardType = KeyboardType.Password,
        mask = '*',
        iconColor = {
            if(isIdle) Color.Black
            else if (it == SignUpForm.password) Color.Green
            else Color.Red
        }
    )
}

@Composable
fun MyConfirmSignUpButton(modifier: Modifier) {
    Button(
        onClick = {
            when(SignUpForm.validateForm()){
                null -> {

                }
                else -> {

                }
            }
        },
        modifier = modifier
            .fillMaxWidth(),
        shape = RectangleShape
    ) {
        Text(
            text = "Cadastrar",
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
fun MyTextField(
    label: String,
    placeholder: String,
    onValueChange: (String) -> Unit,
    icon: ImageVector,
    keyboardType: KeyboardType,
    mask: Char? = null,
    iconColor: (String) -> Color = { Color.Black }
) {
    var text by remember { mutableStateOf("") }
    var isIdle by remember { mutableStateOf(true) }
    val isError = text.isBlank() && !isIdle

    TextField(
        modifier = Modifier
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

@Preview
@Composable
fun SignUpPreview() {
    SignUpActivity(navController = NavController(LocalContext.current))
}