package com.example.sisvitag2.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.data.model.android.Cita
import com.example.data.model.android.Especialista
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvitag2.ui.viewmodel.EspCitaViewModel

@Composable
fun EspCitaScreen(
    navController: NavController,
    especialista: MutableState<Especialista?>,
    viewModel: EspCitaViewModel = viewModel()
) {
    Scaffold(
        topBar = { TopBarEspCita(navController, especialista) },
        bottomBar = { BottomBarEspCita() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(it) // Adds padding to accommodate the top and bottom bars
        ) {
            val citasList = listOf(
                Cita(id_usuario = 1, fecha_cita = "2024-06-21", observaciones = "Todo bien", tratamiento = "Continuar con la medicación", estado = "Pendiente"),
                Cita(id_usuario = 2, fecha_cita = "2024-06-22", observaciones = "Revisión de rutina", tratamiento = "Ninguno", estado = "Completado"),
            )
            ContentEspCita(citasList)
        }
    }
}

@Composable
fun TopBarEspCita(
    navController: NavController,
    especialista: MutableState<Especialista?>
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(15.dp)
    ) {
        IconButton(onClick = {
            // Acción para regresar al login
            navController.navigate(AppScreen.loginScreen.route) {
                popUpTo(AppScreen.loginScreen.route) { inclusive = true }
            }
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
fun BottomBarEspCita() {
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
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Observ.",
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
                text = "Tratam.",
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

@Composable
fun ContentEspCita(citas: List<Cita>) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(start = 30.dp, end = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Bienvenido Especialista ",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 30.dp),
            textAlign = TextAlign.Left
        )
        Text(
            text = "Aquí puedes ver y gestionar las citas, observaciones y tratamientos.",
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
        citas.forEach { cita ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp)
            ) {
                Column(
                    modifier = Modifier.padding(12.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Cita con Usuario ${cita.id_usuario}",
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.secondary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = cita.fecha_cita,
                            fontSize = 18.sp,
                            color = MaterialTheme.colorScheme.onBackground
                        )
                    }
                    Text(
                        text = "Observaciones: ${cita.observaciones ?: "Ninguna"}",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Tratamiento: ${cita.tratamiento ?: "Ninguno"}",
                        fontSize = 16.sp
                    )
                    Text(
                        text = "Estado: ${cita.estado}",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EspCitaScreenPreview() {
    val navController = rememberNavController()
    val especialistaState = remember { mutableStateOf<Especialista?>(null) }
    SisvitaG2Theme {
        EspCitaScreen(
            navController = navController,
            especialista = especialistaState
        )
    }
}
