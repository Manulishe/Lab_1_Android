package com.example.sakova1

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// главный (и единственный) экран приложения
class MainActivity : ComponentActivity() {

    // функция вызывается при создании
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // задаём наполнение экрана приложения
        setContent {
            // Значения этих переменных будут соответствовать полям ввода
            // Значение поля "От"
            val lowerLimit = remember { mutableStateOf("") }
            // Значение поля "До"
            val upperLimit = remember { mutableStateOf("") }
            // Значение поля "Количество"
            val amount = remember { mutableStateOf("") }
            // Значения этих переменных динамически меняются когда меняется содержимое полей

            // С помощью этих переменных задаётся содержимое полей вывода
            // Значение поля "Сгенерированные числа"
            val numbers = remember { mutableStateOf("") }
            // Значение поля "Результат"
            val result = remember { mutableStateOf("") }
            // Значение поля "Отсортированный результат"
            val sortResult = remember { mutableStateOf("") }
            //когда мы меняем значения этих переменных - меняются и числа на экране

            // создаём объект нашего класса Service для дальнейшего использования
            val service = Service()

            Column(
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Лабораторная работа №1\nВариант №8",
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center,
                    modifier = Modifier
                        .fillMaxWidth(1f)
                        .padding(30.dp)
                )

                MyInputField(input = lowerLimit, "От", 6)
                MyInputField(input = upperLimit, "До", 6)
                MyInputField(input = amount, "Количество", 2)

                OutlinedButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp),
                    border = BorderStroke(1.dp, Color.DarkGray),
                    onClick = {
                        try {
                            val array = service.generateNumbers(
                                lowerLimit.value,
                                upperLimit.value,
                                amount.value
                            )
                            numbers.value = array.joinToString()

                            val arrayResult = service.processNumbers(array)
                            result.value = arrayResult.joinToString()
                            sortResult.value = arrayResult.sorted().joinToString()

                        } catch (e: NumberFormatException) {
                            Toast.makeText(
                                applicationContext,
                                "Во всех полях ввода должны быть целые числа",
                                Toast.LENGTH_LONG
                            ).show()
                        } catch (e: Exception) {
                            Toast.makeText(
                                applicationContext,
                                "Неправильно заданы входные параметры",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                ) {
                    Text("Сгенерировать числа", fontSize = 10.sp, textAlign = TextAlign.Center)
                }

                MyOutputField(numbers, "Сгенерированные числа")
                MyOutputField(result, "Результат")
                MyOutputField(sortResult, "Отсортированный результат")
            }
        }
    }

    @Composable
    fun MyInputField(input: MutableState<String>, title: String, length: Int) {
        OutlinedTextField(
            value = input.value,
            label = { Text(title) },
            textStyle = TextStyle(fontSize = 18.sp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            onValueChange = { str ->
                if (str.length <= length) input.value = str
            }
        )
    }

    @Composable
    fun MyOutputField(input: MutableState<String>, title: String) {
        OutlinedTextField(
            value = input.value,
            label = { Text(title) },
            readOnly = true,
            textStyle = TextStyle(fontSize = 18.sp),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 10.dp, end = 10.dp),
            onValueChange = {}
        )
    }
}
