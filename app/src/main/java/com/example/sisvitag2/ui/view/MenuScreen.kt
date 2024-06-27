package com.example.sisvitag2.ui.view

import android.net.Uri
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
import com.example.sisvita_cus1.data.model.Estudiante
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvitag2.ui.viewmodel.LoginViewModel
import com.google.gson.Gson
import java.net.URLEncoder

@Composable
fun MenuScreen(navController: NavController, estudiante: Estudiante) {
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
            Button(onClick = {
                val estudianteJson = Uri.encode(Gson().toJson(estudiante))
                navController.navigate(AppScreen.estudMainScreen.createRoute(estudiante))
            }) {
                Text("Realizar Test")
            }
        }

        // Descomenta y ajusta los botones según tus necesidades
        // Button(onClick = { navController.navigate(AppScreen.VerResultados.route) }) {
        //     Text("Ver Resultados")
        // }
        // Button(onClick = { navController.navigate(AppScreen.NuevaCita.route) }) {
        //     Text("Nueva Cita")
        // }
        // Button(onClick = { navController.navigate(AppScreen.MisCitas.route) }) {
        //     Text("Mis Citas")
        // }
        // Button(onClick = { navController.navigate(AppScreen.Progreso.route) }) {
        //     Text("Progreso")
        // }
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
    val estudiante = Estudiante.defaultEstudiante() // Proporciona un estudiante predeterminado para la vista previa
    SisvitaG2Theme {
        MenuScreen(navController = navController, estudiante = estudiante)
    }
}
