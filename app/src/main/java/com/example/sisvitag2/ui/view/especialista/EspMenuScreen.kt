package com.example.sisvitag2.ui.view.especialista

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
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvitag2.ui.viewmodel.EspMenuViewModel

@Composable
fun EspMenuScreen(
    navController: NavHostController,
    especialista: Especialista,
    viewModel: EspMenuViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.setEspecialista(especialista)
    }

    Scaffold(
        topBar = { TopBarEspMenu(navController) },
        bottomBar = { BottomBarEspMenu(navController) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val nombre = viewModel.especialista.value?.nombre_completo ?: "sin nombre"
            Text(
                text = "Hola $nombre",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 30.dp),
                textAlign = TextAlign.Center
            )

            // Menu buttons
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MenuButton(
                        text = "Evaluar Estudiantes",
                        icon = Icons.Default.Star,
                        onClick = { navController.navigate(AppScreen.realizarVigilanciaScreen.createRoute(especialista)) }

                    )
                    MenuButton(
                        text = "Visualizar Mapa Calor",
                        icon = Icons.Default.CheckCircle,
                        onClick = { navController.navigate(AppScreen.espVisualizarMapaScreen.createRoute(especialista)) }
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    MenuButton(
                        text = "Gestionar Citas",
                        icon = Icons.Default.Favorite,
                        onClick = { navController.navigate(AppScreen.espCitaScreen.createRoute(especialista)) }
                    )
                    MenuButton(
                        text = "Mi Perfil",
                        icon = Icons.Default.AccountCircle,
                        onClick = { }
                    )
                }
            }

            // Cerrar sesión button
            Button(
                onClick = { navController.navigate(AppScreen.loginScreen.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text(text = "Cerrar sesión", color = Color.White)
            }
        }
    }
}

@Composable
fun TopBarEspMenu(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.primary)
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            IconButton(onClick = {
                navController.navigate(AppScreen.loginScreen.route) {
                    popUpTo(AppScreen.loginScreen.route) { inclusive = true }
                }
            }) {
                Icon(
                    imageVector = Icons.Default.KeyboardArrowLeft,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun MenuButton(text: String, icon: ImageVector, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .size(150.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(imageVector = icon, contentDescription = null, tint = Color.Black, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = text, color = Color.Black, fontSize = 16.sp, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun BottomBarEspMenu(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        itemBarEspMenu("Cuestion.", Icons.Default.Star, navController, "cuestion")
        itemBarEspMenu("Result.", Icons.Default.CheckCircle, navController, "result")
        itemBarEspMenu("Citas", Icons.Default.Favorite, navController, "citas")
        itemBarEspMenu("Perfil", Icons.Default.AccountCircle, navController, "perfil")
    }
}

@Composable
fun itemBarEspMenu(texto: String, vector: ImageVector, navController: NavController, route: String) {
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
fun ContentEsp(
    navController: NavController,
    especialista: Especialista,
    viewModel: EspMenuViewModel
)
{
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val nombre = viewModel.especialista.value?.nombre_completo ?: "sin name"
        Text(
            text = "Hola! $nombre",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Left
        )

        GridMenu(navController, especialista)
    }
}

@Composable
fun GridMenu(
    navController: NavController,
    especialista: Especialista
) {
    val menuItems = listOf(
        MenuItem1("Ver mis citas", painterResource(R.drawable.ver_citas), AppScreen.espCitaScreen),
        MenuItem1("Programar citas", painterResource(R.drawable.agregar_cita), AppScreen.espCitaScreen),
        MenuItem1("Evaluar test", painterResource(R.drawable.test_icon), AppScreen.evaluarResultadosTestScreen),
        MenuItem1("Realizar vigilancia", painterResource(R.drawable.seguimiento), AppScreen.espCitaScreen),
        MenuItem1("Visualizar mapa de calor", painterResource(R.drawable.seguimiento), AppScreen.visualizarMapaScreen)

    )

    Column {
        menuItems.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowItems.forEach { item ->
                    MenuItemCard1(item, navController, especialista, Modifier.weight(1f))
                }
                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}


data class MenuItem1(val title: String, val icon: Painter, val screen: AppScreen)

@Composable
fun MenuItemCard1(
    item: MenuItem1,
    navController: NavController,
    especialista: Especialista,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable {
                val especialistaJson = Uri.encode(Gson().toJson(especialista))
                when (item.title) {
                    "Ver mis citas" ->
                        navController.navigate(
                            AppScreen.espCitaScreen.createRoute(especialista!!)
                        )

                    "Programar citas" ->
                        navController.navigate(
                            AppScreen.espCitaScreen.createRoute(especialista!!)
                        ) // Reemplaza con la ruta correcta si es diferente
                    "Evaluar test" ->
                        navController.navigate(
                            AppScreen.evaluarResultadosTestScreen.createRoute(especialista!!)
                        )

                    "Realizar vigilancia" ->
                        navController.navigate(
                            AppScreen.espCitaScreen.createRoute(especialista!!)
                        )
                    "Visualizar mapa de calor" ->
                        navController.navigate(
                            AppScreen.visualizarMapaScreen.createRoute(especialista!!)
                        )
                    else -> {} // Maneja otras rutas si es necesario
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
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = item.icon,
                contentDescription = null,
                modifier = Modifier.size(66.dp)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EspMenuScreenPreview() {
    val navController = rememberNavController()
    val especialista = Especialista(10, 10, "Salazar", "Maria Salazar", "Gutierrez", "especialista")
    SisvitaG2Theme {
        EspMenuScreen(navController, especialista)
    }
}
