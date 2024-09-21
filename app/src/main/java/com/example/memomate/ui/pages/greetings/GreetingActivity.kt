package com.example.memomate.ui.pages.greetings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Greeting(navController: NavController) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        InitialPage(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun InitialPage(navController: NavController, modifier: Modifier = Modifier) {
    Column(modifier = modifier.padding(horizontal = 16.dp)) {
        MyBox(modifier = modifier.weight(1f))
        CenteredText()
        MyBox(modifier = modifier.weight(1f))
        SignInButton(navController)
        MyBox(modifier = modifier.height(16.dp))
        SignUpButton(navController)
        MyBox(modifier = modifier.weight(1f))
    }
}

@Composable
fun MyBox(modifier: Modifier) {
    Box(
        modifier
            .fillMaxWidth()
            .background(color = Color.Transparent))
}

@Composable
fun CenteredText() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.CenterEnd)
    ) {
        Text(
            text = "Memo \nMate",
            style = getTitleFontStyle()
        )
    }
}

private fun getTitleFontStyle(): TextStyle {
    return TextStyle(
        fontSize = 64.sp,
        textDirection = TextDirection.Rtl,
        fontWeight = FontWeight.Black
    )
}

@Composable
fun SignInButton(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.CenterEnd)
    ) {
        val onClick = {
            navController.navigate("signIn")
        }
        MyTextButton("Entrar", onClick)
    }
}

@Composable
fun SignUpButton(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.CenterEnd)
    ) {
        val onClick = {
            navController.navigate("signUp")
        }
        MyTextButton("Cadastre-se", onClick)
    }
}

@Composable
fun MyTextButton(text:String, onClick: () -> Unit) {
    TextButton(
        onClick = onClick,
        colors = ButtonDefaults.textButtonColors(
            contentColor = Color.Black,
            containerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent
        )
    ) {
        Text(text, style = getMyTextButtonTxtStyle())
    }
}

fun getMyTextButtonTxtStyle(): TextStyle {
    return TextStyle(
        fontSize = 18.sp,
        textDirection = TextDirection.Rtl,
        fontWeight = FontWeight.Normal
    )
}

@Preview
@Composable
fun GreetingPreview() {
    Greeting(navController = NavController(LocalContext.current))
}