package com.example.sisvitag2.ui.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.data.model.response.Test
import com.example.sisvita_cus1.data.model.Estudiante
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.R
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvitag2.ui.viewmodel.EstudMainViewModel
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun EstudMainScreen(
    navController: NavController,
    estudiante: MutableState<Estudiante?>,
    viewModel: EstudMainViewModel = viewModel()
) {
    val tests by viewModel.tests
    println("Usuario recibido en EstudMainScreen: ${estudiante.value}")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar2(navController)
        Content2(navController, estudiante, tests)
        BottomBar2(navController)
    }
}

@Composable
fun TopBar2(navController: NavController) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.05F)
            .background(MaterialTheme.colorScheme.primary)
            .padding(6.dp)
    ) {
        IconButton(onClick = {
            // Accion para regresar a la ventana inicial
            navController.navigate(AppScreen.mainScreen.route) {
                popUpTo(AppScreen.mainScreen.route) { inclusive = true }
            }
        }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier
                    .size(25.dp)
                    .align(Alignment.CenterStart),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun Content2(
    navController: NavController,
    estudiante: MutableState<Estudiante?>,
    tests: List<Test>
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.92F)
            .padding(start = 30.dp, end = 30.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        println("Estudiante recibido en Content2: $estudiante")
        val nombre = estudiante.value?.nombre_completo ?: "sin name"
        Text(
            text = "Bienvenido "+nombre+"!",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 30.dp),
            textAlign = TextAlign.Left
        )
        Text(
            text = "Selecciona un cuestionario para iniciar.",
            fontSize = 17.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
        tests.forEach { test ->
            TestCard(test, navController, estudiante.value?.id_estudiante)
        }
    }
}
@Composable
fun TestCard(
    test: Test,
    navController: NavController,
    estudianteId: Int?
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, bottom = 5.dp)
    ) {
        Column(
            modifier = Modifier.padding(12.dp)
        ) {
            Text(
                text = test.tipo,
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.test_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .width(90.dp)
                        .height(90.dp)
                )
                Column(
                    modifier = Modifier.padding(start = 10.dp)
                ) {
                    Text(
                        text = "Info:",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        text = test.descripcion,
                        fontSize = 16.sp
                    )
                }
            }
            Column (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp, top = 5.dp),
                horizontalAlignment = Alignment.End
            ) {
                ClickableText(
                    text = AnnotatedString("Iniciar >"),
                    onClick = {
                        estudianteId?.let { idEstudiante ->
                            navController.navigate(AppScreen.estudTestScreen.createRoute(idEstudiante, test.id_test))
                        }},
                    style = TextStyle(
                        color = MaterialTheme.colorScheme.onBackground,
                        fontSize = 15.sp,
                        textDecoration = TextDecoration.Underline
                    ),
                    modifier = Modifier.padding(bottom = 10.dp)
                )
            }
        }
    }
}
@Composable
fun BottomBar2(
    navController: NavController
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.secondary)
            .padding(
                start = 20.dp,
                top = 6.dp,
                end = 20.dp,
                bottom = 6.dp
            ),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        itemBar2(
            texto = "Cuestion.",
            vector = Icons.Default.Star
        )
        itemBar2(
            texto = "Result.",
            vector = Icons.Default.CheckCircle
        )
        itemBar2(
            texto = "Citas",
            vector = Icons.Default.Favorite
        )
        itemBar2(
            texto = "Perfil",
            vector = Icons.Default.AccountCircle
        )
    }
}

@Composable
fun itemBar2(
    texto: String,
    vector: ImageVector
) {
    Column (
        modifier = Modifier
            .width(75.dp)
            .height(75.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = vector,
            contentDescription = null,
            modifier = Modifier
                .size(25.dp)
                .align(Alignment.CenterHorizontally),
            tint = MaterialTheme.colorScheme.onSecondary
        )
        Text(
            text = texto,
            fontSize = 15.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )
    }
}


@Preview(showBackground = true)
@Composable
fun EstudMainScreenPreview() {
    val navController = rememberNavController()
    val estudianteState = remember { mutableStateOf<Estudiante?>(null) }
    SisvitaG2Theme {
        EstudMainScreen(
            navController = navController,
            estudiante = estudianteState
        )
    }
}
