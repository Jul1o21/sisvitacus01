package com.example.sisvitag2.ui.view.especialista

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
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
import com.example.sisvitag2.ui.viewmodel.EspMapaViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.Circle
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.MapProperties
import com.google.maps.android.compose.MapType
import com.google.maps.android.compose.MapUiSettings
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.Circle

@Composable
fun EspMapaScreen(
    navController: NavHostController,
    especialista: Especialista,
    viewModel: EspMapaViewModel = viewModel()
) {
    println("Se crea EspMapaScreen")
    Scaffold(
        topBar = { TopBarMapa(navController,especialista) },
        bottomBar = { BottomBarMapaScreen() }
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
                text = "Mapa de calor",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 30.dp),
                textAlign = TextAlign.Center
            )
            // Filtros
            FiltroComponentMap(viewModel )
        }
    }
}

@Composable
fun TopBarMapa(
    navController: NavController,
    especialista: Especialista
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.primary)
            .padding(6.dp)
    ) {
        IconButton(onClick = {
            navController.navigate(AppScreen.espMenuScreen.createRoute(especialista)) {
                popUpTo(AppScreen.espMenuScreen.route) { inclusive = true }

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
fun BottomBarMapaScreen() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(
                start = 20.dp,
                top = 6.dp,
                end = 20.dp,
                bottom = 6.dp
            ),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        Column(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Cuestion.",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Result.",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Citas",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )
        }
        Column(
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier.size(25.dp),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Perfil",
                fontSize = 15.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun FiltroComponentMap(
    viewModel: EspMapaViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = "Filtros:",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp),
            textAlign = TextAlign.Left
        )
        Text(
            text = "• Periodo",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 10.dp)
        )


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            inputTextPeriodoStart(
                value = viewModel.startPeriodo.value,
                onValueChange = { viewModel.setStartPeriodo(it)  },
                placeholder = "DD-MM-AAAA",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )
            Text(
                text = "-",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(horizontal = 8.dp) // Espaciado horizontal
                    .align(Alignment.CenterVertically) // Centrar verticalmente
            )
            inputTextPeriodoEnd(
                value = viewModel.startPeriodo.value,
                onValueChange = { viewModel.setStartPeriodo(it)  },
                placeholder = "DD-MM-AAAA",
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                )
            )

        }

        Text(
            text = "Tipo de test",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp),
            textAlign = TextAlign.Left
        )

        ComboBoxTipoDeTest(viewModel)

        Text(
            text = "Nivel de gravedad",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp),
            textAlign = TextAlign.Left
        )
        ComboBoxNivelGravedad()

        Text(
            text = "Mapa de calor:",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 12.dp),
            textAlign = TextAlign.Left
        )

        CrearGoogleMap3()
    }
}

@Composable
fun inputTextPeriodoStart(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions
) {

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .width(130.dp),
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(fontSize = 12.sp)
            )

        }
    )


}


@Composable
fun inputTextPeriodoEnd(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions
) {

    OutlinedTextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .width(130.dp),
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(fontSize = 12.sp)
            )

        }
    )
}


@Composable
fun ComboBoxTipoDeTest(
    viewModel: EspMapaViewModel
) {
    println("Se llama a ComboBoxTipoDeTest")

    var expanded by remember { mutableStateOf(false) }
    val tipoDeTestOptions by viewModel.tiposTestsLista.observeAsState(emptyList())
    var selectedTest by remember { mutableStateOf(tipoDeTestOptions.getOrElse(0) { "" }) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp)) // Añadir borde
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(4.dp))
            .clickable { expanded = true }
            .padding(16.dp)

    ) {
        Text(text = selectedTest)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            tipoDeTestOptions.forEach { test ->
                DropdownMenuItem(
                    text = { Text(test) },
                    onClick = {
                        selectedTest = test
                        expanded = false
                    }
                )
            }
        }
    }
}


@Composable
fun ComboBoxNivelGravedad(
    viewModel: EspMapaViewModel = viewModel()
) {
    var expanded by remember { mutableStateOf(false) }
    val tipoGravedadLista by viewModel.gravedadLista.observeAsState(emptyList())
    var selected by remember { mutableStateOf(tipoGravedadLista.getOrElse(0) { "" }) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(1.dp)
            .border(1.dp, Color.Gray, shape = RoundedCornerShape(4.dp)) // Añadir borde
            .background(MaterialTheme.colorScheme.surface, shape = RoundedCornerShape(4.dp))
            .clickable { expanded = true }
            .padding(16.dp)
    ) {
        Text(text = selected)
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
        ) {
            tipoGravedadLista.forEach { test ->
                DropdownMenuItem(
                    text = { Text(test) },
                    onClick = {
                        selected = test
                        expanded = false
                    }
                )
            }
        }
    }
}

@Composable
fun CrearGoogleMap() {
    val cameraPositionState = rememberCameraPositionState {
        position = com.google.android.gms.maps.model.CameraPosition.fromLatLngZoom(LatLng(-12.0464, -77.0428), 10f)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp)
            .padding(16.dp)
            .border(1.dp, Color.Gray)
    ) {
        GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState,
            uiSettings = MapUiSettings(
                zoomControlsEnabled = false,
                compassEnabled = true,
                myLocationButtonEnabled = true
            ),
            onMapLoaded = {
                cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(LatLng(-12.0464, -77.0428), 10f))
            }
        )
    }
}
@Composable
fun CrearGoogleMap2() {

    var circle by remember { mutableStateOf<Circle?>(null) }

    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    var uiSettings by remember { mutableStateOf(MapUiSettings()) }

    val lima = LatLng(-12.0464, -77.0428)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(lima, 10f)
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .padding(16.dp)
        .border(1.dp, Color.Gray)
    ) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            properties = properties,
            uiSettings = uiSettings,
            cameraPositionState = cameraPositionState,  // Asegúrate de usar cameraPositionState aquí
            onMapLoaded = {
                cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(lima, 10f))
            }
        )
        {
            /*
            Circle(
                center = lima,
                radius = 1000.0, // Radio de la circunferencia en metros
                strokeColor = Color.Red, // Color del borde de la circunferencia (rojo)
                strokeWidth = 4f, // Ancho del borde de la circunferencia
                fillColor = Color.Red.copy(alpha = 0.3f) // Color de relleno de la circunferencia (rojo con transparencia)
            )*/

        }

    }
}

@Composable
fun CrearGoogleMap3(viewModel: EspMapaViewModel = viewModel()) {

    val ubigeos by viewModel.ubigeosEst.observeAsState(emptyList())

    var properties by remember {
        mutableStateOf(MapProperties(mapType = MapType.NORMAL))
    }

    var uiSettings by remember { mutableStateOf(MapUiSettings()) }

    val lima = LatLng(-12.0464, -77.0428)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(lima, 10f)
    }

    Box(modifier = Modifier
        .fillMaxWidth()
        .height(300.dp)
        .padding(16.dp)
        .border(1.dp, Color.Gray)
    ) {
        GoogleMap(
            modifier = Modifier.matchParentSize(),
            properties = properties,
            uiSettings = uiSettings,
            cameraPositionState = cameraPositionState,
            onMapLoaded = {
                cameraPositionState.move(CameraUpdateFactory.newLatLngZoom(lima, 10f))
            }
        ) {
            // Itera sobre los ubigeos y dibuja los círculos según el nivel
            ubigeos.forEach { ubigeo ->
                if (ubigeo.nivel.isNotEmpty()) {
                    val color = when (ubigeo.nivel.lowercase()) {
                        "alto" -> Color.Red
                        "medio" -> Color.Yellow
                        "bajo" -> Color.Green
                        else -> Color.Gray // Default color in case of unknown level
                    }

                    val position = LatLng(ubigeo.y.toDouble(), ubigeo.x.toDouble())

                    Circle(
                        center = position,
                        radius = 1000.0, // Radio de la circunferencia en metros
                        strokeColor = color, // Color del borde de la circunferencia
                        strokeWidth = 4f, // Ancho del borde de la circunferencia
                        fillColor = color.copy(alpha = 0.3f) // Color de relleno de la circunferencia con transparencia
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EspMapaScreenPreview() {
    val navController = rememberNavController()
    val especialista = Especialista.defaultEspecialista()
    SisvitaG2Theme {
        EspMapaScreen(navController, especialista)
    }
}