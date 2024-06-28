package com.example.sisvitag2.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.data.model.android.Especialista
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.R
import com.example.sisvitag2.ui.theme.SisvitaG2Theme

@Composable
fun EspMainScreen(
    navController: NavController,
    especialista: MutableState<Especialista?>,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBarEsp(navController)
        ContentEsp(navController)
        Spacer(modifier = Modifier.weight(1f))
        BottomBarEsp(navController)
    }
}

@Composable
fun TopBarEsp(navController: NavController) {
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
fun BottomBarEsp(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp)
            .background(MaterialTheme.colorScheme.secondary)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomBarItem("Cuestion.", Icons.Default.Star, navController, "cuestion")
        BottomBarItem("Result.", Icons.Default.CheckCircle, navController, "result")
        BottomBarItem("Citas", Icons.Default.Favorite, navController, "citas")
        BottomBarItem("Perfil", Icons.Default.AccountCircle, navController, "perfil")
    }
}

@Composable
fun BottomBarItem(texto: String, icono: ImageVector, navController: NavController, route: String) {
    Column(
        modifier = Modifier
            .width(75.dp)
            .height(75.dp)
            .clickable { navController.navigate(route) },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icono,
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
fun ContentEsp(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 16.dp, vertical = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Bienvenido Especialista",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            textAlign = TextAlign.Left
        )

        GridMenu(navController)
    }
}

@Composable
fun GridMenu(navController: NavController) {
    val menuItems = listOf(
        MenuItem1("Ver mis citas", painterResource(R.drawable.ver_citas), "ver_mis_citas"),
        MenuItem1("Programar citas", painterResource(R.drawable.agregar_cita), "programar_citas"),
        MenuItem1("Evaluar test", painterResource(R.drawable.test_icon), "evaluar_test"),
        MenuItem1("Realizar vigilancia", painterResource(R.drawable.seguimiento), "realizar_vigilancia")
    )

    Column {
        menuItems.chunked(2).forEach { rowItems ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                rowItems.forEach { item ->
                    MenuItemCard1(item, navController, Modifier.weight(1f))
                }
                if (rowItems.size < 2) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

data class MenuItem1(val title: String, val icon: Painter, val route: String)

@Composable
fun MenuItemCard1(item: MenuItem1, navController: NavController, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .aspectRatio(1f)
            .clickable { navController.navigate(item.route) },
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                painter = item.icon,
                contentDescription = null,
                modifier = Modifier
                    .size(48.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = item.title,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EspMainScreenPreview() {
    val navController = rememberNavController()
    val especialistaState = remember { mutableStateOf<Especialista?>(null) }
    SisvitaG2Theme {
        EspMainScreen(
            navController = navController,
            especialista = especialistaState
        )
    }
}
