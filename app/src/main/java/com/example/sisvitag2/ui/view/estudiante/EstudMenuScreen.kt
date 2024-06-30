package com.example.sisvitag2.ui.view.estudiante

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.data.model.android.Estudiante
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvitag2.ui.viewmodel.EstudMenuViewModel

@Composable
fun EstudMenuScreen(
    navController: NavController,
    estudiante: Estudiante,
    viewModel: EstudMenuViewModel = viewModel()
) {
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
            val nombre = viewModel.estudiante.value?.nombre_completo ?: "sin name"
            Text(
                text = "Bienvenido $nombre!",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            MenuGrid(navController, estudiante)
        }
    }
}

@Composable
fun MenuGrid(navController: NavController, estudiante: Estudiante) {
    val menuItems = listOf(
        MenuItem("Realizar Test", Icons.Default.Star, "test"),
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
fun MenuItemCard(
    item: MenuItem,
    navController: NavController,
    estudiante: Estudiante,
    modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable {
                if (item.route == "test") {
                    navController.navigate(AppScreen.estudTestsListScreen.createRoute(estudiante))
                } else {
                    //
                }
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

            navController.navigate(AppScreen.loginScreen.route) {
                popUpTo(AppScreen.loginScreen.route) { inclusive = true }
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

@Preview(showBackground = true)
@Composable
fun MenuScreenPreview() {
    val navController = rememberNavController()
    val estudiante = Estudiante.defaultEstudiante()
    SisvitaG2Theme {
        EstudMenuScreen(
            navController = navController,
            estudiante = estudiante)
    }
}
