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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sisvita_cus1.data.model.Estudiante
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.R
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvitag2.ui.viewmodel.EstudMainViewModel

@Composable
fun EstudMainScreen (
    navController: NavController,
    id_usu: Int
) {
    val estudiante = remember { mutableStateOf<Estudiante?>(null) }

    LaunchedEffect(id_usu) {
        estudiante.value = Estudiante(id_usu = id_usu)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar2(navController,estudiante)
        Content2(navController,estudiante)
        BottomBar2(navController,estudiante)
    }
}

@Composable
fun TopBar2(
    navController: NavController,
    estudiante: MutableState<Estudiante?>
) {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1F)
            .background(MaterialTheme.colorScheme.primary)
            .padding(15.dp)
    ) {
        IconButton(onClick = {
            // Accion para regresar al login
            navController.navigate(AppScreen.loginScreen.route) {
                popUpTo(AppScreen.loginScreen.route) { inclusive = true }
            }
        }) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowLeft,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .align(Alignment.CenterStart),
                tint = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Composable
fun Content2(
    navController: NavController,
    estudiante: MutableState<Estudiante?>
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.89F)
            .padding(start = 30.dp, end = 30.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val id_usu = estudiante.value?.id_usu ?: -1
        Text(
            text = "Hey $id_usu!",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 30.dp),
            textAlign = TextAlign.Left
        )
        Text(
            text = "Selecciona un cuestionario para iniciar.",
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
        ) {
            Column (
                modifier = Modifier.padding(12.dp)
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Test de Alfredo",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                    ClickableText(
                        text = AnnotatedString("Iniciar >"),
                        onClick = { /* TODO */},
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 18.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.test_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .width(90.dp)
                            .height(90.dp)
                    )
                    Column (
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Text(
                            text = "Info:",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Text(
                            text = "Test creado por "+ "Alfredo (1935)" +" para medir el grado de ansiedad. Consta de "+"20"+" preguntas de resp. única.",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
        ) {
            Column (
                modifier = Modifier.padding(12.dp)
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Test de Paco",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                    ClickableText(
                        text = AnnotatedString("Iniciar >"),
                        onClick = { /* TODO */},
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 18.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.test_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .width(90.dp)
                            .height(90.dp)
                    )
                    Column (
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Text(
                            text = "Info:",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Text(
                            text = "Test creado por "+ "Paco (1975)" +" para medir el grado de ansiedad. Consta de "+"15"+" preguntas de resp. única.",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
        Card (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp)
        ) {
            Column (
                modifier = Modifier.padding(12.dp)
            ) {
                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Test de Maria",
                        fontSize = 18.sp,
                        color = MaterialTheme.colorScheme.secondary,
                        fontWeight = FontWeight.Bold
                    )
                    ClickableText(
                        text = AnnotatedString("Iniciar >"),
                        onClick = { /* TODO */},
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 18.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }
                Row (
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.test_icon),
                        contentDescription = null,
                        modifier = Modifier
                            .width(90.dp)
                            .height(90.dp)
                    )
                    Column (
                        modifier = Modifier.padding(start = 10.dp)
                    ) {
                        Text(
                            text = "Info:",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 5.dp)
                        )
                        Text(
                            text = "Test creado por "+ "Maria (2005)" +" para medir el grado de ansiedad. Consta de "+"21"+" preguntas de resp. única.",
                            fontSize = 16.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun BottomBar2(
    navController: NavController,
    estudiante: MutableState<Estudiante?>
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
        Column (
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Custion.",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Column (
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Result.",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Column (
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Citas",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
        Column (
            modifier = Modifier
                .width(75.dp)
                .height(75.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = Icons.Default.AccountCircle,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Perfil",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun EstudMainScreenPreview(estudMainModel: EstudMainViewModel = viewModel()) {
    val navController = rememberNavController()
    SisvitaG2Theme {
        EstudMainScreen(
            navController=navController,
            id_usu = 0
        )
    }
}