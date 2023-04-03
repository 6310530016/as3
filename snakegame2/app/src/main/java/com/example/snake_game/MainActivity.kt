package com.example.snake_game

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.example.snake_game.ui.theme.SnakeGameTheme
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnakeGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MultiGameApp()
                }
            }
        }
    }
}

sealed class Screen(val title: String) {
    object Home : Screen("Multi-Game")
    object NumberGuessingGame : Screen("Number Guessing Game")
    object QuizGame : Screen("Quiz Game")
    object NewGame : Screen("New Game")
}

@Preview
@Composable
fun MultiGameApp() {
    var currentScreen by remember { mutableStateOf(Screen.Home) }

    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = currentScreen.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(16.dp)
            )

            when (currentScreen) {
                is Screen.Home -> {
                    HomeScreen(onGameSelected = { game ->
                        currentScreen = game
                    })
                }
                is Screen.NumberGuessingGame -> {
                    NumberGuessingGameScreen(onBackPressed = {
                        currentScreen = Screen.Home
                    })
                }
                is Screen.QuizGame -> {
                    QuizGameScreen(onBackPressed = {
                        currentScreen = Screen.Home
                    })
                }
                is Screen.NewGame -> {
                    NewGameScreen(onBackPressed = {
                        currentScreen = Screen.Home
                    })
                }
            }
        }
    }
}

@Composable
fun HomeScreen(onGameSelected: (Screen) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = { onGameSelected(Screen.NumberGuessingGame) },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = Screen.NumberGuessingGame.title)
        }
        Button(
            onClick = { onGameSelected(Screen.QuizGame) },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = Screen.QuizGame.title)
        }
        Button(
            onClick = { onGameSelected(Screen.NewGame) },
            modifier = Modifier.padding(16.dp)
        ) {
            Text(text = Screen.NewGame.title)
        }
    }
}

@Composable
fun NumberGuessingGameScreen(onBackPressed: () -> Unit) {
    // implement the Number Guessing Game UI here
    Button(onClick = onBackPressed) {
        Text(text = "Go back")
    }
}

@Composable
fun QuizGameScreen(onBackPressed: () -> Unit) {
    // implement the Quiz Game UI here
    Button(onClick = onBackPressed) {
        Text(text = "Go back")
    }
}

@Composable
fun NewGameScreen(onBackPressed: () -> Unit) {
    // implement the new game UI here
    Button(onClick = onBackPressed) {
        Text(text = "Go back")
    }
}