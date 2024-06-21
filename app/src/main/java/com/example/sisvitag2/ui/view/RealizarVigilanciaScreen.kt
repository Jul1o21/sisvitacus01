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
import com.example.data.model.Participante
import com.example.sisvitag2.ui.theme.SisvitaG2Theme

@Composable
fun RealizarVigilanciaScreen() {
    var fechaFiltro by remember { mutableStateOf("") }
    var tipoTestFiltro by remember { mutableStateOf("") }
    val participantes = remember { mutableStateOf(listOf(
        Participante("Juan Perez", 80, "A"),
        Participante("Maria Lopez", 75, "B")
    )) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "Realizar Vigilancia",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 30.dp),
            textAlign = TextAlign.Center
        )
        // Filtros
        FiltroComponent(
            fechaFiltro = fechaFiltro,
            onFechaChanged = { fechaFiltro = it },
            tipoTestFiltro = tipoTestFiltro,
            onTipoTestChanged = { tipoTestFiltro = it }
        )
        // Lista de Participantes
        ParticipantesList(participantes = participantes.value)
    }
}

@Composable
fun FiltroComponent(
    fechaFiltro: String,
    onFechaChanged: (String) -> Unit,
    tipoTestFiltro: String,
    onTipoTestChanged: (String) -> Unit
) {
    Column {
        Text(
            text = "Filtrar por Fecha",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp),
            textAlign = TextAlign.Left
        )
        BasicTextField(
            value = fechaFiltro,
            onValueChange = onFechaChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            singleLine = true
        )
        Text(
            text = "Filtrar por Tipo de Test",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp),
            textAlign = TextAlign.Left
        )
        BasicTextField(
            value = tipoTestFiltro,
            onValueChange = onTipoTestChanged,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            singleLine = true
        )
    }
}

@Composable
fun ParticipantesList(participantes: List<Participante>) {
    Column {
        participantes.forEach { participante ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Nombre: ${participante.nombre}",
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "Puntaje: ${participante.puntaje}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                    Text(
                        text = "Calificación: ${participante.calificacion}",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RealizarVigilanciaScreenPreview() {
    SisvitaG2Theme {
        RealizarVigilanciaScreen()
    }
}