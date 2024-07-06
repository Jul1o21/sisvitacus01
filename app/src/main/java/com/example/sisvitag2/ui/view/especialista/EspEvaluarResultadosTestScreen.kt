package com.example.sisvitag2.ui.view.especialista

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.data.model.android.Especialista
import com.example.data.model.response.TestEvaluable
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvitag2.ui.viewmodel.EspEvaluarResultadosTestViewModel

@Composable
fun EspEvaluarResultadosTestScreen(navController: NavController, test: TestEvaluable, especialista: Especialista, viewModel: EspEvaluarResultadosTestViewModel = viewModel()) {
    var descripcion by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf("") }
    var tratamiento by remember { mutableStateOf("") }
    var recomendacion by remember { mutableStateOf("") }
    var notas by remember { mutableStateOf("") }
    val context = LocalContext.current

    val showDialog by viewModel.showDialog

    fun saveDiagnostico() {
        if (descripcion.isBlank() || resultado.isBlank() || tratamiento.isBlank() || recomendacion.isBlank() || notas.isBlank()) {
            Toast.makeText(context, "Todos los campos son obligatorios", Toast.LENGTH_SHORT).show()
            return
        }

        Log.d("ApiRequest", "Request: DiagnosticoRequest(id_usu=${especialista.id_usu}, id_test_res=${test.id_test_res}, descripcion=$descripcion, resultado=$resultado, recomendacion=$recomendacion, tratamiento=$tratamiento, notas=$notas)")
        viewModel.registrarDiagnostico(
            idUsu = especialista.id_usu,
            idTestRes = test.id_test_res,
            descripcion = descripcion,
            resultado = resultado,
            tratamiento = tratamiento,
            recomendacion = recomendacion,
            notas = notas
        )
    }

    Scaffold(
        topBar = { TopBarDiagnostico(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Text(
                text = "Realizar Diagnóstico",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Center
            )

            DiagnosticoCard(test, navController, especialista)

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Agregar evaluación",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(vertical = 10.dp)
            )

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = resultado,
                onValueChange = { resultado = it },
                label = { Text("Resultado") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = tratamiento,
                onValueChange = { tratamiento = it },
                label = { Text("Tratamiento") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = recomendacion,
                onValueChange = { recomendacion = it },
                label = { Text("Recomendación") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = notas,
                onValueChange = { notas = it },
                label = { Text("Notas de seguimiento") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = { saveDiagnostico() },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Guardar cambios")
            }

            if (showDialog) {
                AlertDialog(
                    onDismissRequest = { viewModel.updateShowDialog(false) },
                    confirmButton = {
                        Button(
                            onClick = {
                                viewModel.updateShowDialog(false)
                                navController.navigate(AppScreen.realizarVigilanciaScreen.createRoute(especialista))
                            }
                        ) {
                            Text("Continuar")
                        }
                    },
                    title = { Text(text = "Diagnóstico Registrado") },
                    text = { Text("Se registró el diagnóstico correctamente.") }
                )
            }
        }
    }
}

@Composable
fun TopBarDiagnostico(navController: NavController) {
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
fun BottomBarEvaResult(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        itemBarEvaResult("Cuestion.", Icons.Default.Star, navController, "cuestion")
        itemBarEvaResult("Result.", Icons.Default.CheckCircle, navController, "result")
        itemBarEvaResult("Citas", Icons.Default.Favorite, navController, "citas")
        itemBarEvaResult("Perfil", Icons.Default.AccountCircle, navController, "perfil")
    }
}

@Composable
fun itemBarEvaResult(texto: String, vector: ImageVector, navController: NavController, route: String) {
    Column(
        modifier = Modifier
            .width(75.dp)
            .height(75.dp)
            .clickable { navController.navigate(route) },
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

@Composable
fun DiagnosticoCard(test: TestEvaluable, navController: NavController, especialista: Especialista) {
    val color = when (test.nivel.toLowerCase()) {
        "alto" -> Color.Red
        "medio" -> Color.Yellow
        else -> Color.Green
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Test: ${test.descripcion}",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.weight(1f)
                )
                Box(
                    modifier = Modifier
                        .size(16.dp)
                        .background(color, shape = CircleShape)
                )
            }

            
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Estudiante: ${test.estudiante}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Tipo de Test: ${test.tipo}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Puntaje Total: ${test.puntaje_total}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Fecha: ${test.fecha}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground,
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = { navController.navigate(AppScreen.testResumenScreen.createRoute(test)) }
                ) {
                    Text(
                        text = "Ver más detalles",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 16.sp
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Diagnóstico automático:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Black,
                color = MaterialTheme.colorScheme.secondary
            )
            Text(
                text = "Descripción: ${test.resultado}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "Recomendación: ${test.recomend}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EspEvaluarResultadosTestScreenPreview() {
    val navController = rememberNavController()
    val test = TestEvaluable(
        descripcion = "Resultado Zung",
        estudiante = "Diego Flores Quinonez",
        fecha = "Mon, 01 Jul 2024 00:00:00 GMT",
        id_test_res = 3000002,
        nivel = "medio",
        puntaje_total = 42,
        recomend = "-",
        resultado = "Ansiedad minima a moderada",
        tipo = "Test de Ansiedad de Zung"
    )
    val especialista = Especialista(10, 10, "Salazar", "Lucas", "Gutierrez", "especialista")
    SisvitaG2Theme {
        EspEvaluarResultadosTestScreen(navController, test, especialista)
    }
}
