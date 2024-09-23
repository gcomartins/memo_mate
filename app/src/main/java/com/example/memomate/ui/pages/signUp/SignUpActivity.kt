package com.example.memomate.ui.pages.signUp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.memomate.service.users.UserRepository
import com.example.memomate.ui.pages.signUp.components.SignUpForm

@Composable
fun SignUpActivity(controller: SignUpController) {
    SignUpFormData()

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        SignUpForm(
            controller,
            modifier = Modifier.padding(innerPadding)
        )
    }
}



@Preview
@Composable
fun SignUpActivityPreview() {
    SignUpActivity(SignUpController(UserRepository()))
}