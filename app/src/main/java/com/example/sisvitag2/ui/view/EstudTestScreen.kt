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
import com.example.data.model.request.PregRespuesta
import com.example.data.model.request.TestRequest
import com.example.sisvita_cus1.data.model.Estudiante
import com.example.sisvitacus1.navigation.AppScreen
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
        Content3(viewModel, navController, idEstudiante)
        BottomBar3(navController)
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
fun TopBar3(navController: NavController) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.05F)
            .background(MaterialTheme.colorScheme.primary)
            .padding(6.dp)
    ) {
        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.CenterStart),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun Content3(
    viewModel: EstudTestViewModel,
    navController: NavController,
    idEstudiante: Int
) {
    val respuestas = viewModel.respuestas
    val selectedOptions = viewModel.selectedOptions
    val testResponse by viewModel.testResponse.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.92F)
            .padding(start = 30.dp, end = 30.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = testResponse?.data?.test?.tipo ?: "Tipo de Test Desconocido",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 20.dp),
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

        println("El test recibido del back es: $testResponse")

        /*val respuestas = remember { mutableStateListOf<PregRespuesta>() }
        val selectedOptions = remember { mutableStateMapOf<Int, Int>() }*/

        testResponse?.data?.preguntas?.forEach { pregunta ->
            val maximo = pregunta.puntaje_maximo
            val minimo = pregunta.puntaje_minimo
            val total = maximo - minimo + 1;

            var labels = if (total == 3) listOf("Poco", "Regular", "Mucho") else listOf("Nunca", "A veces", "Muchas veces", "Siempre")

            var values = if (minimo <= maximo) {
                (minimo..maximo).toList()
            } else {
                (minimo downTo maximo).toList()
            }

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 10.dp,
                        bottom = 10.dp
                    )
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
                    values.forEachIndexed { index, value ->
                        if (index < labels.size) {
                            OpcionRow3(
                                label = labels[index],
                                value = value,
                                idPregunta = pregunta.id_preg,
                                selectedOptions = selectedOptions,
                                respuestas = respuestas,
                                onvalueChange = {
                                    respuesta -> viewModel.updateRespuesta(respuesta.id_preg, respuesta.puntaje)
                                }
                            )
                        }
                    }
                }
            }
        }

        boton3(
            texto = "Enviar",
            nav = { viewModel.regTest(idEstudiante) }
        )

        respuestas.forEach { respuesta ->
            println("Pregunta ID: ${respuesta.id_preg}, Valor: ${respuesta.puntaje}")
        }
    }
}

@Composable
fun OpcionRow3(
    label: String,
    value: Int,
    idPregunta: Int,
    selectedOptions: MutableMap<Int, Int>,
    respuestas: SnapshotStateList<PregRespuesta>,
    onvalueChange: (PregRespuesta) -> Unit
) {

    val isChecked = selectedOptions[idPregunta] == value

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        println("Se crea un checkbox '$label' con valor '$value'")
        Checkbox(
            checked = isChecked,
            onCheckedChange = { checked ->
                if (checked) {
                    selectedOptions[idPregunta] = value
                    val respuesta = PregRespuesta(id_preg = idPregunta, puntaje = value)
                    onvalueChange(respuesta)
                } else {
                    selectedOptions.remove(idPregunta)
                    respuestas.removeAll { it.id_preg == idPregunta }
                }
            },
            modifier = Modifier
                .width(40.dp)
                .height(35.dp)
        )
        Text(
            text = "$label",
            fontSize = 16.sp
        )
    }
}

@Composable
fun BottomBar3(
    navController: NavController
) {
    Row (
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
        itemBar3(
            texto = "Cuestion.",
            vector = Icons.Default.Star
        )
        itemBar3(
            texto = "Result.",
            vector = Icons.Default.CheckCircle
        )
        itemBar3(
            texto = "Citas",
            vector = Icons.Default.Favorite
        )
        itemBar3(
            texto = "Perfil",
            vector = Icons.Default.AccountCircle
        )
    }
}

@Composable
fun itemBar3(
    texto: String,
    vector: ImageVector
) {
    Column (
        modifier = Modifier
            .width(75.dp)
            .height(75.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = vector,
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.CenterHorizontally),
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = texto,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun boton3 (
    texto: String,
    nav: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth(.75F),
        onClick = { nav() }
    ) {
        textoBoton(texto)
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