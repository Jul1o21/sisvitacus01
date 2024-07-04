package com.example.sisvitag2.ui.view.especialista

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.data.model.android.Especialista
import com.example.data.model.android.Participante
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.ui.theme.SisvitaG2Theme

@Composable
fun EspRealizarVigilanciaScreen(
    navController: NavHostController,
    especialista: Especialista,

) {
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
            var fechaInicio by remember { mutableStateOf("") }
            var fechaFin by remember { mutableStateOf("") }
            var tipoTest by remember { mutableStateOf("") }
            var nivelGravedad by remember { mutableStateOf("") }
            val participantes = remember { mutableStateOf(listOf(
                Participante("Juan Perez", 80, "A"),
                Participante("Maria Lopez", 75, "B")
            )) }

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
                onNivelGravedadChanged = { nivelGravedad = it }
            )

            // Lista de Participantes
            ParticipantesList(participantes = participantes.value)
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

@Composable
fun FiltrosComponent(
    fechaInicio: String,
    onFechaInicioChanged: (String) -> Unit,
    fechaFin: String,
    onFechaFinChanged: (String) -> Unit,
    tipoTest: String,
    onTipoTestChanged: (String) -> Unit,
    nivelGravedad: String,
    onNivelGravedadChanged: (String) -> Unit
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
            options = listOf("Test A", "Test B", "Test C")
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
            options = listOf("Bajo", "Medio", "Alto")
        )
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

@Composable
fun ParticipantesList(participantes: List<Participante>) {
    Column {
        participantes.forEach { participante ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Row(
                    modifier = Modifier
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val color = when (participante.calificacion) {
                        "A" -> Color.Green
                        "B" -> Color.Yellow
                        else -> Color.Red
                    }
                    Box(
                        modifier = Modifier
                            .size(24.dp)
                            .background(color = color, shape = CircleShape)
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Column {
                        Text(
                            text = "Test T-${participante.hashCode()}",
                            color = MaterialTheme.colorScheme.primary,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Estudiante: ${participante.nombre}",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "Puntuación: ${participante.puntaje}",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Text(
                            text = "Calificación: ${participante.calificacion}",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.secondary
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Ver detalles >",
                            fontSize = 16.sp,
                            color = MaterialTheme.colorScheme.primary,
                            modifier = Modifier.align(Alignment.End)
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RealizarVigilanciaScreenPreview() {
    val navController = rememberNavController()
    val especialista = Especialista.defaultEspecialista()
    SisvitaG2Theme {
        EspRealizarVigilanciaScreen(navController, especialista)
    }
}
