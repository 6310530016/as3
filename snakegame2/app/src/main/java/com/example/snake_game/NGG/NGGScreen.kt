package com.example.ass3.NGG

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ass3.R
import kotlin.math.abs


@Composable
fun GameArea() {

    val InitialAnswer = (1..50).random()

    Column(verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Number Guessing Game",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Left,
        )
        Text(text = "Try to guess the number I'm thinking of from 1 - 50!",
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center
        )
        ActionArea(InitialAnswer)
    }
}

@Composable
fun ActionArea(answer: Int) {
    var answer: Int by remember { mutableStateOf(answer) }
    var number_input: String by remember { mutableStateOf("") }
    var number: Int by remember { mutableStateOf(0) }
    var is_correct: Boolean by remember { mutableStateOf(false) }

    var result: Double = 0.0
    var result_text: String by remember { mutableStateOf("") }
    var count: Int by remember { mutableStateOf(0) }
    var amount_text: String by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        NumberInputField(number_input, {number_input = it})
        Spacer(modifier = Modifier.height( 12.dp))
        if (number == 0 && !is_correct) {
            result_text = ""
        }
        else if ((number < 1 || number > 50) && !is_correct) {
            println("Hey there")
            result_text = stringResource(id = R.string.out_of_range)
        }
        else{
            result = CalculateDifference(number, answer)
            if (result == 0.0 && !is_correct) {
                result_text = ResultText(result = result)
                amount_text = GuessAmountText(amount = count)
                is_correct = true
            }
            else if (!is_correct){
                result_text = ResultText(result = result)
                amount_text = ""
                //count.state += 1
            }
        }
        Button(onClick = {number = number_input.toIntOrNull() ?: 0
                count++}) {
            Text(text = stringResource(id = R.string.button_label1), fontSize = 20.sp)
        }
        Spacer(modifier = Modifier.height( 12.dp))
        Text(text = result_text, fontSize = 20.sp, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height( 12.dp))
        Text(text = amount_text, fontSize = 20.sp, textAlign = TextAlign.Center)
        Button(onClick = { answer = (1..50).random()
                         count = 0
                         is_correct = false
                        amount_text = ""
                        number = 0
                         },
            contentPadding = PaddingValues(18.dp)
        ) {
            Text(stringResource(id = R.string.try_again_button), fontSize = 20.sp)
        }
    }

}

@Composable
fun NumberInputField(value: String,
        onValueChange: (String) -> Unit){
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(value = value,
            onValueChange = onValueChange,
            label = { Text(stringResource(R.string.text_input_label1))},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )
    }
}

@Composable
fun ResultText(result: Double): String {
    var text: String = ""
    if (result == 0.0) {
        return stringResource(id = R.string.correct)
    }
    if (result > 0) {
        text = stringResource(id = R.string.less)
    }
    else {
        text = stringResource(id = R.string.more)
    }

    val result = abs(result)
    if (result < 0.266666666) {
        text += " " + stringResource(id = R.string.close)
    }
    else if(result > 0.63333333) {
        text += " " + stringResource(id = R.string.far)
    }

    return text
}

@Composable
fun GuessAmountText(amount: Int): String{
    var text: String = stringResource(id = R.string.guess_amount) + " " + amount.toString() + " " +
            stringResource(id = R.string.time_unit)
    if (amount < 5) {
        text += " " + stringResource(id = R.string.low_amount)
    }
    if (amount > 20) {
        text += " " + stringResource(id = R.string.high_amount)
    }
    return text
}

private fun CalculateDifference(number: Int, answer: Int): Double{
    return (number - answer).toDouble() / 10
}

