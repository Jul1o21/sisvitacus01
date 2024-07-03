package com.example.sisvitag2.ui.view.estudiante

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
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.data.model.request.PregRespuestaRequest
import com.example.data.model.request.TestRequest
import com.example.data.model.android.Estudiante
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.ui.theme.SisvitaG2Theme

@Composable
fun EstudRealizarTestScreen(
    navController: NavController,
    idEstudiante: Int,
    idTest: Int,
    estudiante: Estudiante,
    viewModel: EstudTestViewModel = viewModel()
) {
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.obtenerTest(TestRequest(id_test = idTest))
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar3(navController)
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.92F)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                Content3(viewModel)
            }
            Column (
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button(
                    onClick = {
                        viewModel.regTest(idEstudiante)
                        showDialog = true
                    },
                    modifier = Modifier
                        .fillMaxWidth(.75f)
                        .padding(10.dp)
                ) {
                    Text(
                        text = "Enviar respuestas",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(4.dp)
                    )
                }
            }
        }
        BottomBar2(navController)

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    Button(onClick = {
                        navController.navigate(AppScreen.estudMenuScreen.createRoute(estudiante))
                    }) {
                        Text("Ver resultados")
                    }
                },
                title = { Text("Respuestas Registradas") },
                text = { Text("Sus respuestas han sido registradas con éxito.") }
            )
        }
    }
}



@Composable
fun TopBar3(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(6.dp)
    ) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun Content3(viewModel: EstudTestViewModel) {
    val respuestas = viewModel.respuestas
    val selectedOptions = viewModel.selectedOptions
    val testResponse by viewModel.testSingleResponse.observeAsState()

    println("Los test recibidos son: $testResponse")
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = testResponse?.data?.test?.tipo ?: "Tipo de Test Desconocido",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            textAlign = TextAlign.Center
        )

        Text(
            text = "Marque una respuesta en cada una de las siguientes preguntas.",
            fontSize = 17.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )

        Text(
            text = "Marque sus respuestas a conciencia para obtener una mejor precisión.",
            fontSize = 15.sp,
            color = Color.Gray,
            modifier = Modifier.padding(bottom = 20.dp)
        )

        testResponse?.data?.preguntas?.forEach { pregunta ->
            val maximo = pregunta.puntaje_maximo
            val minimo = pregunta.puntaje_minimo
            val total = maximo - minimo + 1

            val labels = if (total == 3) listOf("Poco", "Regular", "Mucho") else listOf("Nunca", "A veces", "Muchas veces", "Siempre")

            val values = if (minimo <= maximo) {
                (minimo..maximo).toList()
            } else {
                (minimo downTo maximo).toList()
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                ) {
                    Text(
                        text = pregunta.descripcion,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                    values.forEachIndexed { index, value ->
                        if (index < labels.size) {
                            OpcionRow3(
                                label = labels[index],
                                value = value,
                                idPregunta = pregunta.id_preg,
                                selectedOptions = selectedOptions,
                                respuestas = respuestas,
                                onvalueChange = { respuesta ->
                                    viewModel.updateRespuesta(respuesta.id_preg, respuesta.puntaje)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun OpcionRow3(
    label: String,
    value: Int,
    idPregunta: Int,
    selectedOptions: MutableMap<Int, Int>,
    respuestas: SnapshotStateList<PregRespuestaRequest>,
    onvalueChange: (PregRespuestaRequest) -> Unit
) {
    val isChecked = selectedOptions[idPregunta] == value

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = isChecked,
            onCheckedChange = { checked ->
                if (checked) {
                    selectedOptions[idPregunta] = value
                    val respuesta = PregRespuestaRequest(id_preg = idPregunta, puntaje = value)
                    onvalueChange(respuesta)
                } else {
                    selectedOptions.remove(idPregunta)
                    respuestas.removeAll { it.id_preg == idPregunta }
                }
            },
            modifier = Modifier.size(35.dp)
        )
        Text(
            text = label,
            fontSize = 16.sp
        )
    }
}

@Composable
fun BottomBar3(navController: NavController) {
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
        itemBar3("Cuestion.", Icons.Default.Star)
        itemBar3("Result.", Icons.Default.CheckCircle)
        itemBar3("Citas", Icons.Default.Favorite)
        itemBar3("Perfil", Icons.Default.AccountCircle)
    }
}

@Composable
fun itemBar3(texto: String, vector: ImageVector) {
    Column(
        modifier = Modifier
            .width(75.dp)
            .height(75.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = vector,
            contentDescription = null,
            modifier = Modifier.size(25.dp),
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = texto,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@Preview(showBackground = true)
@Composable
fun EstudTestScreenPreview() {
    val navController = rememberNavController()
    val idEstudiante = 123 // ID de estudiante de prueba
    val idTest = 456 // ID de test de prueba
    val estudiante = Estudiante.defaultEstudiante()
    SisvitaG2Theme {
        EstudRealizarTestScreen(
            navController,
            idEstudiante,
            idTest,
            estudiante
        )
    }
}


