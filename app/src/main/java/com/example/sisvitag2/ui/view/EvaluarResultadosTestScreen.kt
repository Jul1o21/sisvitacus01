import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Resultado
import com.example.sisvitag2.ui.theme.SisvitaG2Theme

@Composable
fun EvaluarResultadosTestScreen() {
    var observacion by remember { mutableStateOf("") }
    var tratamiento by remember { mutableStateOf("") }
    val resultado = remember { mutableStateOf(Resultado("Juan Perez", 80, "Alta")) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Evaluar Resultados Test",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 30.dp),
            textAlign = TextAlign.Center
        )
        // Detalles del Resultado
        ResultadoDetallesComponent(resultado = resultado.value)
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
    }
}

@Composable
fun ResultadoDetallesComponent(resultado: Resultado) {
    Column {
        Text(
            text = "Nombre: ${resultado.nombre}",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "Puntaje: ${resultado.puntaje}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
        Text(
            text = "Nivel de Ansiedad: ${resultado.nivelAnsiedad}",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        )
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

@Preview(showBackground = true)
@Composable
fun EvaluarResultadosTestScreenPreview() {
    SisvitaG2Theme {
        EvaluarResultadosTestScreen()
    }
}