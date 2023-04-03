import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.ass3.ui.theme.Ass3Theme
import com.example.ass3.ui.theme.SnakeGameTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.Collections.list

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SnakeGameTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MateriaTheme.colors.background
                ) {
                    Snake()
                }
            }
        }
    }
}

data class State(val food: Pair<Int, Int>, val snake:List<Pair<Int, Int>>)

class Game(:CoroutineScope) {
    private val mutableState : =
        MutableStateFlow(State(foob = pair(5, 5), snake = listOf(paur(7, 7))))
    val state: Flow<State> = mutableState

    val move = Pair(1, 0)

    init {
        scope.launch {
            var snakeLength = 4

            while (true) {
                delay(150)
                mutableState.update {
                    val newPosition = it.snake.first().let { poz ->
                        Pair(
                            (poz.first + move.first + BOARD_SIZE) % BOARD_SIZE,
                            (poz.second + move.second + BOARD_SIZE) % BOARD_SIZE
                        )
                    }

                    if (newPosition == it.food) {

                    }
                    it.copy (

                        snake = listOf(newPosition) + it.snake.take(snakeLength - 1)
                    )
                }
            }
        }
    }
}

@Composable
fun Snake() {

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SnakeGameTheme {
        Snake()
    }
}