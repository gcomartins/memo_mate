package com.example.memomate

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material.TopAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.memomate.ui.theme.MemoMateTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MemoMateTheme {
                Scaffold(
                    topBar = { MyTopBar() },
                    modifier = Modifier.systemBarsPadding().fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Row {
        Text(
            text = "Helloooooooxxx $name!",
            modifier = modifier.weight(1f)
        )
        MySwitch(modifier)
        MyButton(modifier)
    }
}

@Composable
fun MyTopBar() {
    TopAppBar(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text("Top Bar")
    }
}

@Composable
fun MySwitch(modifier: Modifier) {
    var isChecked by remember { mutableStateOf(false) }
    Switch(
        checked = isChecked,
        onCheckedChange = { isChecked = it },
        modifier = modifier
    )
}

@Composable
fun MyButton(modifier: Modifier) {
    var count by remember { mutableIntStateOf(0) }
    Button(
        modifier = modifier,
        onClick = { count++ }) {
        Text(text = "Count: $count")
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MemoMateTheme {
        Greeting("Android")
    }
}