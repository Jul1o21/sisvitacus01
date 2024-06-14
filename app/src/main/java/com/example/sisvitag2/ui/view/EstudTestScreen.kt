package com.example.sisvitag2.ui.view

import EstudTestViewModel
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvita_cus1.data.model.*

@Composable
fun EstudTestScreen(navController: NavController, viewModel: EstudTestViewModel = viewModel()) {
    val tests by viewModel.tests.observeAsState(emptyList())
    var selectedAnswers by remember { mutableStateOf(mapOf<Int, String>()) }

    LaunchedEffect(Unit) {
        viewModel.fetchTests()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar3()
        Content3(tests, selectedAnswers) { questionId, selectedOption ->
            selectedAnswers = selectedAnswers.toMutableMap().apply { put(questionId, selectedOption) }
        }
        BottomBar3()
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            onClick = {
                val testResponse = TestResponse(
                    testId = 1, // Debe ser dinámico
                    userId = 1, // Debe ser dinámico
                    answers = selectedAnswers.map { (questionId, selectedOption) ->
                        Answer(questionId, selectedOption)
                    }
                )
                viewModel.submitTest(testResponse)
            },
        ) {
            Text(
                text = "Enviar respuestas",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(5.dp)
            )
        }
    }
}

@Composable
fun TopBar3() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1F)
            .background(MaterialTheme.colorScheme.primary)
            .padding(15.dp)
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .align(Alignment.CenterStart),
            tint = MaterialTheme.colorScheme.onPrimary
        )
    }
}

@Composable
fun Content3(
    tests: List<Test>,
    selectedAnswers: Map<Int, String>,
    onAnswerSelected: (Int, String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.89F)
            .padding(start = 30.dp, end = 30.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Test de Alfredo",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 15.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = "Marque una respuesta en cada una de las siguientes preguntas.",
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
        tests.forEach { test ->
            test.questions.forEach { question ->
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = question.text,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 10.dp, bottom = 8.dp)
                    )
                    question.options.forEach { option ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            Checkbox(
                                checked = selectedAnswers[question.id] == option,
                                onCheckedChange = {
                                    if (it) {
                                        onAnswerSelected(question.id, option)
                                    }
                                },
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(35.dp)
                            )
                            Text(
                                text = option,
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar3() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(
                start = 20.dp,
                top = 6.dp,
                end = 20.dp,
                bottom = 6.dp
            ),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Custion.",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Result.",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Citas",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Perfil",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EstudTestScreenPreview() {
    val navController = rememberNavController()
    SisvitaG2Theme {
        EstudTestScreen(navController = navController)
    }
}
