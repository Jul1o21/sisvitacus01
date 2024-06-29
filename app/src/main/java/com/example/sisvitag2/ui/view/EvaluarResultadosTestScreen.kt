import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.data.model.android.Especialista
import com.example.data.model.android.Resultado
import com.example.data.model.response.TestResponseResult
import com.example.sisvitag2.ui.theme.SisvitaG2Theme

@Composable
fun EvaluarResultadosTestScreen(
    navController: NavHostController,
    viewModel: EvaluarResultadosTestViewModel = viewModel()
) {
    var observacion by remember { mutableStateOf("") }
    var tratamiento by remember { mutableStateOf("") }
    val testResults = viewModel.testsRespondidos

    LaunchedEffect(Unit) {
        viewModel.obtenerTodosTestsRespondidos()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState())
    ) {
        TopBarEvaResult(navController = navController)
        Text(
            text = "Evaluar Resultados Test",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(top = 10.dp, bottom = 30.dp),
            textAlign = TextAlign.Center
        )

        // Mostrar resultados de tests
        testResults.forEach { result ->
            TestResultComponent(result)
        }

        // Observaciones y Tratamiento
        ObservacionesComponent(
            observacion = observacion,
            onObservacionChanged = { observacion = it },
            tratamiento = tratamiento,
            onTratamientoChanged = { tratamiento = it }
        )

        // Botón para Confirmar y Notificar
        Button(
            onClick = {
                // Aquí se puede agregar la lógica para confirmar el resultado y notificar
                println("Resultado confirmado: $observacion, $tratamiento")
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(vertical = 8.dp)
        ) {
            Text(
                text = "Confirmar y Notificar",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(5.dp)
            )
        }

        // Espacio para empujar el BottomBarEvaResult hacia abajo
        Spacer(modifier = Modifier.weight(1f))

        BottomBarEvaResult(navController = navController)
    }
}


@Composable
fun TestResultComponent(result: TestResponseResult) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Test: ${result.id_test_res}",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.secondary,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Tipo de test: ${result.tipo_test}",
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Puntaje Total: ${result.puntaje_total}",
                fontSize = 14.sp,
            )
        }
    }
}


@Composable
fun ObservacionesComponent(
    observacion: String,
    onObservacionChanged: (String) -> Unit,
    tratamiento: String,
    onTratamientoChanged: (String) -> Unit
) {
    Column {
        Text(
            text = "Observaciones",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(top = 5.dp, bottom = 10.dp),
            textAlign = TextAlign.Left
        )
        BasicTextField(
            value = observacion,
            onValueChange = onObservacionChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            singleLine = true
        )
        Text(
            text = "Tratamiento",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .padding(top = 5.dp, bottom = 10.dp),
            textAlign = TextAlign.Left
        )
        BasicTextField(
            value = tratamiento,
            onValueChange = onTratamientoChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            singleLine = true
        )
    }
}


@Composable
fun TopBarEvaResult(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(horizontal = 16.dp, vertical = 6.dp)
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
fun BottomBarEvaResult(navController: NavController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(horizontal = 16.dp, vertical = 6.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        itemBarEvaResult("Cuestion.", Icons.Default.Star, navController, "cuestion")
        itemBarEvaResult("Result.", Icons.Default.CheckCircle, navController, "result")
        itemBarEvaResult("Citas", Icons.Default.Favorite, navController, "citas")
        itemBarEvaResult("Perfil", Icons.Default.AccountCircle, navController, "perfil")
    }
}


@Composable
fun itemBarEvaResult(texto: String, vector: ImageVector, navController: NavController, route: String) {
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

@Preview(showBackground = true)
@Composable
fun EvaluarResultadosTestScreenPreview() {
    val navController = rememberNavController()
    SisvitaG2Theme {
        EvaluarResultadosTestScreen(
            navController = navController
        )
    }
}
