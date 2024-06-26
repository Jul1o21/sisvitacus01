package com.example.sisvitag2.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvitag2.ui.viewmodel.LoginViewModel

@Composable
fun MenuScreen(navController: NavController, loginViewModel: LoginViewModel = viewModel()) {
    val estudiante by loginViewModel.estudiante.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar4(navController)
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Menú Principal",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            estudiante?.let {
                Button(onClick = {
                    navController.navigate(AppScreen.estudMainScreen.createRoute(it))
                }) {
                    Text("Realizar Test")
                }
            }
            // Descomenta y ajusta los botones según tus necesidades
            // Button(onClick = { navController.navigate(AppScreen.verResultados.route) }) {
            //     Text("Ver Resultados")
            // }
            // Button(onClick = { navController.navigate(AppScreen.nuevaCita.route) }) {
            //     Text("Nueva Cita")
            // }
            // Button(onClick = { navController.navigate(AppScreen.misCitas.route) }) {
            //     Text("Mis Citas")
            // }
            // Button(onClick = { navController.navigate(AppScreen.progreso.route) }) {
            //     Text("Progreso")
            // }
        }
        BottomBar4(navController)
    }
}

@Composable
fun TopBar4(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(6.dp)
    ) {
        IconButton(onClick = {
            navController.navigate(AppScreen.mainScreen.route) {
                popUpTo(AppScreen.mainScreen.route) { inclusive = true }
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
fun BottomBar4(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(6.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        itemBar4(texto = "Cuestion.", vector = Icons.Default.Star)
        itemBar4(texto = "Result.", vector = Icons.Default.CheckCircle)
        itemBar4(texto = "Citas", vector = Icons.Default.Favorite)
        itemBar4(texto = "Perfil", vector = Icons.Default.AccountCircle)
    }
}

@Composable
fun itemBar4(texto: String, vector: ImageVector) {
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
fun MenuScreenPreview() {
    val navController = rememberNavController()
    SisvitaG2Theme {
        MenuScreen(navController = navController)
    }
}
