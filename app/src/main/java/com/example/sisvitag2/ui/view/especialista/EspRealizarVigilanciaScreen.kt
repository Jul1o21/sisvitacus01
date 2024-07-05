package com.example.sisvitag2.ui.view.especialista

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.data.model.android.Especialista
import com.example.data.model.response.TestEvaluable
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun EspRealizarVigilanciaScreen(
    navController: NavHostController,
    especialista: Especialista,
    viewModel: EspRealizarVigilanciaViewModel = viewModel()
) {
    val testsEvaluable by viewModel.testsEvaluables.observeAsState(emptyList())
    val tipoTests by viewModel.tipoTests.observeAsState(emptyList())
    val nivelesGravedad by viewModel.nivelesGravedad.observeAsState(emptyList())

    var fechaInicio by remember { mutableStateOf("") }
    var fechaFin by remember { mutableStateOf("") }
    var tipoTest by remember { mutableStateOf("") }
    var nivelGravedad by remember { mutableStateOf("") }

    val fechaInicioParsed = try {
        LocalDate.parse(fechaInicio, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    } catch (e: DateTimeParseException) {
        null
    }
    val fechaFinParsed = try {
        LocalDate.parse(fechaFin, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
    } catch (e: DateTimeParseException) {
        null
    }

    val filteredTests = testsEvaluable.filter { test ->
        val testDate = parseDate(test.fecha)

        (tipoTest.isEmpty() || test.tipo == tipoTest) &&
                (nivelGravedad.isEmpty() || test.nivel == nivelGravedad) &&
                (fechaInicioParsed == null || (testDate != null && !testDate.isBefore(fechaInicioParsed))) &&
                (fechaFinParsed == null || (testDate != null && !testDate.isAfter(fechaFinParsed)))
    }

    LaunchedEffect(Unit) {
        viewModel.obtenerTestsEvaluables()
    }

    Scaffold(
        topBar = { TopBarVigilancia(navController, especialista) },
        bottomBar = { BottomBarVigilancia() }
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
                text = "Evaluar Estudiantes",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 30.dp),
                textAlign = TextAlign.Center
            )

            // Filtros
            FiltrosComponent(
                fechaInicio = fechaInicio,
                onFechaInicioChanged = { fechaInicio = it },
                fechaFin = fechaFin,
                onFechaFinChanged = { fechaFin = it },
                tipoTest = tipoTest,
                onTipoTestChanged = { tipoTest = it },
                nivelGravedad = nivelGravedad,
                onNivelGravedadChanged = { nivelGravedad = it },
                tipoTests = tipoTests,
                nivelesGravedad = nivelesGravedad,
                onResetFilters = {
                    fechaInicio = ""
                    fechaFin = ""
                    tipoTest = ""
                    nivelGravedad = ""
                }
            )

            Text(
                text = "Tests a evaluar:",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 20.dp, bottom = 10.dp)
            )

            // Lista de Tests Evaluables
            if (filteredTests.isNotEmpty()) {
                filteredTests.forEach { test ->
                    TestEvaluableCard(test, navController)
                }
            } else {
                Text(
                    text = "No se encontraron tests evaluables.",
                    fontSize = 16.sp,
                    color = MaterialTheme.colorScheme.onBackground,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun TopBarVigilancia(
    navController: NavController,
    especialista: Especialista
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(6.dp)
    ) {
        IconButton(onClick = {
            navController.navigate(AppScreen.espMenuScreen.createRoute(especialista)) {
                popUpTo(AppScreen.espMenuScreen.route) { inclusive = true }
            }
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
fun BottomBarVigilancia() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(
                start = 20.dp,
                top = 6.dp,
                end = 20.dp,
                bottom = 6.dp
            ),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        BottomBarItem("Evaluar", Icons.Default.Star)
        BottomBarItem("Mapa", Icons.Default.CheckCircle)
        BottomBarItem("Citas", Icons.Default.Favorite)
        BottomBarItem("Perfil", Icons.Default.AccountCircle)
    }
}

@Composable
fun BottomBarItem(text: String, icon: ImageVector) {
    Column(
        modifier = Modifier
            .width(75.dp)
            .height(75.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            modifier = Modifier.size(25.dp),
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = text,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
fun parseDate(dateStr: String): LocalDate? {
    return try {
        val formatter = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss 'GMT'", Locale.ENGLISH)
        LocalDate.parse(dateStr, formatter)
    } catch (e: DateTimeParseException) {
        null
    }
}

@Composable
fun FiltrosComponent(
    fechaInicio: String,
    onFechaInicioChanged: (String) -> Unit,
    fechaFin: String,
    onFechaFinChanged: (String) -> Unit,
    tipoTest: String,
    onTipoTestChanged: (String) -> Unit,
    nivelGravedad: String,
    onNivelGravedadChanged: (String) -> Unit,
    tipoTests: List<String>,
    nivelesGravedad: List<String>,
    onResetFilters: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Filtros:",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            textAlign = TextAlign.Left
        )
        Text(
            text = "• Periodo",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp)
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = fechaInicio,
                onValueChange = onFechaInicioChanged,
                label = { Text("DD-MM-AAAA") },
                modifier = Modifier.weight(1f)
            )
            OutlinedTextField(
                value = fechaFin,
                onValueChange = onFechaFinChanged,
                label = { Text("DD-MM-AAAA") },
                modifier = Modifier.weight(1f)
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "• Tipo de Test",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp)
        )
        DropdownMenuComponent(
            value = tipoTest,
            onValueChange = onTipoTestChanged,
            options = tipoTests
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "• Nivel de gravedad",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp)
        )
        DropdownMenuComponent(
            value = nivelGravedad,
            onValueChange = onNivelGravedadChanged,
            options = nivelesGravedad
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onResetFilters,
            modifier = Modifier.align(Alignment.End)
        ) {
            Text(text = "Quitar Filtros")
        }
    }
}

@Composable
fun DropdownMenuComponent(
    value: String,
    onValueChange: (String) -> Unit,
    options: List<String>
) {
    var expanded by remember { mutableStateOf(false) }

    Box {
        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            label = { Text("Seleccione") },
            modifier = Modifier.fillMaxWidth(),
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.clickable { expanded = !expanded }
                )
            }
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            options.forEach { option ->
                DropdownMenuItem(onClick = {
                    onValueChange(option)
                    expanded = false
                }) {
                    Text(text = option)
                }
            }
        }
    }
}

// EspRealizarVigilanciaScreen.kt
@Composable
fun TestEvaluableCard(test: TestEvaluable, navController: NavController) {
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
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = { navController.navigate(AppScreen.espEvaluarResultadosTest.createRoute(test)) },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Evaluar")
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun RealizarVigilanciaScreenPreview() {
    val navController = rememberNavController()
    val especialista = Especialista(
        1,
        2001,
        "Perez",
        "Juan",
        "Robles",
        "especialista"
    )
    SisvitaG2Theme {
        EspRealizarVigilanciaScreen(
            navController = navController,
            especialista = especialista
        )
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun TestEvaluableCardPreview() {
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
    SisvitaG2Theme {
        TestEvaluableCard(test = test, navController = navController)
    }
}

