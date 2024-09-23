package com.example.memomate.ui.pages.signUp.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.memomate.data.User
import com.example.memomate.ui.pages.signUp.SignUpController
import com.example.memomate.ui.pages.signUp.SignUpFormData
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun SignUpForm(controller: SignUpController, modifier: Modifier) {
    val coroutineScope = rememberCoroutineScope()

    var signUpResult by remember { mutableStateOf<Result<User>?>(null) }

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
                SignUpFormData.firstName = it
            }
            MyLastNameTextField {
                SignUpFormData.lastName = it
            }
            MyBirthDateTextField()
            MyEmailTextField {
                SignUpFormData.email = it
            }
            MyPasswordTextField {
                SignUpFormData.password = it
            }
            MyConfirmPasswordTextField {
                SignUpFormData.confirmPassword = it
            }
        }
        MyConfirmSignUpButton(modifier.weight(2f)){
            when(SignUpFormData.validateForm()){
                null -> {
                    coroutineScope.launch {
                        val user = SignUpFormData.getUserFromForm()
                        signUpResult = controller.signUpUser(user)
                    }
                }
                else -> {

                }
            }
        }
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyBirthDateTextField() {
    var showDatePicker by remember { mutableStateOf(false) }
    var birthDateValue by remember { mutableStateOf("") }

    val dateState = rememberDatePickerState()

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                Button(onClick = {
                    showDatePicker = false
                    val formattedDate = if (dateState.selectedDateMillis != null) {
                        SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date(dateState.selectedDateMillis!!))
                    } else {
                        ""
                    }
                    SignUpFormData.birthDate = formattedDate
                    birthDateValue = formattedDate
                }) {
                    Text("Confirmar")
                }
            },
            dismissButton = {
                Button(onClick = { showDatePicker = false }) {
                    Text("Cancelar")
                }
            }
        ) {
            DatePicker(state = dateState)
        }
    }

    MyTextField(
        value = birthDateValue,
        label = "Data de Nascimento",
        placeholder = "DD/MM/AAAA",
        onValueChange = {},
        icon = Icons.Filled.DateRange,
        keyboardType = KeyboardType.Email, // Considere usar KeyboardType.Number aqui
        enabled = false,
        modifier = Modifier.clickable { showDatePicker = true }
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
            else if (it == SignUpFormData.password) Color.Green
            else Color.Red
        }
    )
}