
// TestResumenScreen.kt
package com.example.sisvitag2.ui.view.otro

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sisvitag2.ui.theme.SisvitaG2Theme

@Composable
fun TestResumenScreen(
    navController: NavController,
    testTitulo: String,
    respuestas: List<TestRespuesta>
) {
    Scaffold(
        topBar = { TopBarResumen(navController) }
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
                text = testTitulo,
                color = MaterialTheme.colorScheme.primary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Resumen de respuestas",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 10.dp),
                textAlign = TextAlign.Left
            )

            respuestas.forEach { respuesta ->
                RespuestaCard(respuesta)
            }
        }
    }
}

@Composable
fun TopBarResumen(
    navController: NavController,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(6.dp)
    ) {
        IconButton(onClick = {
            navController.popBackStack()
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
fun RespuestaCard(respuesta: TestRespuesta) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = respuesta.pregunta,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = "→ ${respuesta.respuesta}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun TestResumenScreenPreview() {
    val navController = rememberNavController()
    val respuestas = listOf(
        TestRespuesta("¿Se ha sentido ultimamente mas nervioso y ansioso?", "Nunca"),
        TestRespuesta("¿Se ha sentido temeroso/asustado sin razon?", "A veces"),
        TestRespuesta("¿Ha sentido que se está derrumbando?", "A veces"),
        TestRespuesta("¿Se ha sentido ultimamente mas nervioso y ansioso?", "Nunca"),
        TestRespuesta("¿Se ha sentido temeroso/asustado sin razon?", "A veces"),
        TestRespuesta("¿Ha sentido que se está derrumbando?", "A veces")
    )
    SisvitaG2Theme {
        TestResumenScreen(
            navController = navController,
            testTitulo = "Test de Ansiedad de Zung",
            respuestas = respuestas
        )
    }
}

data class TestRespuesta(
    val pregunta: String,
    val respuesta: String
)