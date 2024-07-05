package com.example.sisvitag2.ui.view.especialista

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.data.model.android.Especialista
import com.example.data.model.android.Estudiante
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvitag2.ui.viewmodel.EspMapaViewModel
import com.example.sisvitag2.ui.viewmodel.EstudMenuViewModel


@Composable
fun EspMapaScreen(
    navController: NavHostController,
    especialista: Especialista,
    viewModel: EspMapaViewModel = viewModel()
) {
    Scaffold(
        topBar = { TopBarMapa(navController,especialista) },
        bottomBar = { BottomBarMapaScreen() }
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
                text = "Mapa de calor",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 30.dp),
                textAlign = TextAlign.Center
            )
            // Filtros
            FiltroComponentMap(viewModel )
        }
    }
}

@Composable
fun TopBarMapa(
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
fun BottomBarMapaScreen() {
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
        Column(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Cuestion.",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Result.",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Citas",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Perfil",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun FiltroComponentMap(
    viewModel: EspMapaViewModel
) {
    Column {
        Text(
            text = "Filtros:",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            textAlign = TextAlign.Left
        )

        Text(
            text = "Periodo",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp),
            textAlign = TextAlign.Left
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            inputTextPeriodoStart(
                value = viewModel.startPeriodo.value,
                onValueChange = { viewModel.setStartPeriodo(it)  },
                placeholder = "DD-MM-AAAA",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )
            Text(
                text = "-",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp) // Espaciado horizontal
            )
            inputTextPeriodoEnd(
                value = viewModel.startPeriodo.value,
                onValueChange = { viewModel.setStartPeriodo(it)  },
                placeholder = "DD-MM-AAAA",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )

        }

        Text(
            text = "Tipo de test",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp),
            textAlign = TextAlign.Left
        )

        ComboBoxTipoDeTest()

        Text(
            text = "Nivel de gravedad",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp),
            textAlign = TextAlign.Left
        )
        ComboBoxNivelGravedad()

        Text(
            text = "Mapa de calor:",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            textAlign = TextAlign.Left
        )
    }
}

@Composable
fun inputTextPeriodoStart(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .padding(end = 10.dp) // Reducir el espacio en el lado derecho
            .width(140.dp), // Ajustar el ancho según tus necesidades
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(text = placeholder)
        }
    )
}


@Composable
fun inputTextPeriodoEnd(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .padding(start = 10.dp)
            .width(140.dp), // Ajustar el ancho según tus necesidades
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(text = placeholder)
        }
    )
}


@Composable
fun ComboBoxTipoDeTest() {
    var expanded by remember { mutableStateOf(false) }
    val tipoDeTestOptions = listOf("Test A", "Test B", "Test C") // Opciones para el ComboBox
    var selectedTest by remember { mutableStateOf(tipoDeTestOptions[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp)) // Añadir borde
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(4.dp))
            .clickable { expanded = true }
            .padding(16.dp)
    ) {
        Text(text = selectedTest)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            tipoDeTestOptions.forEach { test ->
                DropdownMenuItem(
                    text = { Text(test) },
                    onClick = {
                        selectedTest = test
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun ComboBoxNivelGravedad() {
    var expanded by remember { mutableStateOf(false) }
    val tipoDeTestOptions = listOf("Bajo", "Medio", "Alto") // Opciones para el ComboBox
    var selectedTest by remember { mutableStateOf(tipoDeTestOptions[0]) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp)) // Añadir borde
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(4.dp))
            .clickable { expanded = true }
            .padding(16.dp)
    ) {
        Text(text = selectedTest)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            tipoDeTestOptions.forEach { test ->
                DropdownMenuItem(
                    text = { Text(test) },
                    onClick = {
                        selectedTest = test
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun CrearGoogleMap(){

}

@Preview(showBackground = true)
@Composable
fun EspMapaScreenPreview() {
    val navController = rememberNavController()
    val especialista = Especialista.defaultEspecialista()
    SisvitaG2Theme {
        EspMapaScreen(navController, especialista)
    }
}