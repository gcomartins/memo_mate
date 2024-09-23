package com.example.memomate.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.memomate.service.users.UserRepository
import com.example.memomate.ui.pages.greetings.Greeting
import com.example.memomate.ui.pages.signIn.SignIn
import com.example.memomate.ui.pages.signUp.SignUpActivity
import com.example.memomate.ui.pages.signUp.SignUpController

@Composable
fun AppNavigation(modifier: Modifier = Modifier) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "greetings") {
        composable("greetings") { Greeting(navController) }
        composable("signUp") { SignUpActivity(SignUpController(UserRepository())) }
        composable("signIn") { SignIn(navController) }
    }
}