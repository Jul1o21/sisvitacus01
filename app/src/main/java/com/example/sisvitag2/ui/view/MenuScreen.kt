package com.example.sisvitag2.ui.view

import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.data.model.android.Estudiante
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.google.gson.Gson

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
            MenuGrid(navController, estudiante)
        }
        BottomBar4(navController)
    }
}

@Composable
fun MenuGrid(navController: NavController, estudiante: Estudiante) {
    val menuItems = listOf(
        MenuItem("Realizar Test", Icons.Default.Star, "estudMainScreen"),
        MenuItem("Ver Resultados", Icons.Default.CheckCircle, "resultadosScreen"),
        MenuItem("Nueva Cita", Icons.Default.Favorite, "nuevaCitaScreen"),
        MenuItem("Mis Citas", Icons.Default.DateRange, "misCitasScreen"),
        MenuItem("Progreso", Icons.Default.PlayArrow, "progresoScreen")
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        menuItems.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowItems.forEach { item ->
                    MenuItemCard(item, navController, estudiante, Modifier.weight(1f))
                }
                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

data class MenuItem(val title: String, val icon: ImageVector, val route: String)

@Composable
fun MenuItemCard(item: MenuItem, navController: NavController, estudiante: Estudiante, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable {
                val estudianteJson = Uri.encode(Gson().toJson(estudiante))
                navController.navigate(item.route)
            },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(top = 36.dp)
                .align(Alignment.CenterHorizontally)


        ) {
            Icon(
                item.icon,
                contentDescription = null,
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
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
    val estudiante = Estudiante(
        contrasenia = "password",
        correo = "estudiante@correo.com",
        departamento = "Departamento",
        distrito = "Distrito",
        edad = 20,
        id_estudiante = 1,
        id_usuario = 1,
        nombre_completo = "John Doe",
        numero_celular = 123456789,
        numero_documento = 12345678,
        pais = "Perú",
        sexo = "Masculino",
        tipo_documento = "DNI",
        tipo_usuario = "Estudiante",
        universidad = "Universidad XYZ"
    )
    SisvitaG2Theme {
        MenuScreen(navController = navController, estudiante = estudiante)
    }
}