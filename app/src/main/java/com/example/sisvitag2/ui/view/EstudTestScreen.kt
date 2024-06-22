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
import com.example.data.model.request.TestRequest
import com.example.sisvitag2.ui.theme.SisvitaG2Theme

@Composable
fun EstudTestScreen(
    navController: NavController,
    idEstudiante: Int,
    idTest: Int,
    viewModel: EstudTestViewModel = viewModel()
) {

    println("el id del estudiante es $idEstudiante y el id del test es $idTest")
    // Llamar al método para cargar las preguntas
    LaunchedEffect(Unit) {
        viewModel.obtenerTest(TestRequest(id_test = idTest))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar3(navController)
        Content3(viewModel)
        BottomBar3()
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 20.dp),
            onClick = {

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
fun TopBar3(
    navController: NavController
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1F)
            .background(MaterialTheme.colorScheme.primary)
            .padding(15.dp)
    ) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
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
}

@Composable
fun Content3(
    viewModel: EstudTestViewModel
) {
    val testResponse by viewModel.testResponse.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.89F)
            .padding(start = 30.dp, end = 30.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = testResponse?.data?.test?.tipo ?: "Tipo de Test Desconocido",
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

        Text(
            text = "Considere desde poco frecuente hasta muy frecuente, desde el 1 hasta el maximo valor",
            fontSize = 14.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        println("El test recibido del back es: $testResponse")

        testResponse?.data?.preguntas?.forEach { pregunta ->
            val puntajeMaximo = maxOf(pregunta.puntaje_maximo, pregunta.puntaje_minimo)

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    println("pregunta del bucle de listas $pregunta")
                    Text(
                        text = pregunta.descripcion,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 10.dp, bottom = 8.dp)
                    )
                    (1..puntajeMaximo).forEach { opcion ->
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
                            println("se crea un checkbock $opcion")
                            Checkbox(
                                checked = false, // Esto es solo para el preview
                                onCheckedChange = { /* No hace nada por ahora */ },
                                modifier = Modifier
                                    .width(40.dp)
                                    .height(35.dp)
                            )
                            Text(
                                text = "Opción $opcion",
                                fontSize = 16.sp
                            )
                        }
                    }
                }
            }
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            onClick = { /* No hace nada por ahora */ }
        ) {
            Text(
                text = "Enviar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(5.dp)
            )
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
    val idEstudiante = 123 // ID de estudiante de prueba
    val idTest = 456 // ID de test de prueba
    SisvitaG2Theme {
        EstudTestScreen(navController, idEstudiante, idTest)
    }
}