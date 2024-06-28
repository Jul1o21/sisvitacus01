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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
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
import com.example.data.model.android.Estudiante
import com.example.data.model.request.PregRespuestaRequest
import com.example.data.model.response.TestResponse
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.R
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvitag2.ui.viewmodel.EstudMainViewModel

@Composable
fun EstudResultScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar5(navController)
        Content5(navController)
        BottomBar5(navController)
    }
}

@Composable
fun TopBar5(navController: NavController) {
    Box(
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
fun Content5(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.92F)
            .padding(30.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Mis resultados",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            textAlign = TextAlign.Left
        )
        Text(
            text = "Últimos test resueltos (3):",
            fontSize = 17.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp)
        )
        CardTest5(navController = navController)
        Text(
            text = "Mis historias:",
            fontSize = 17.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 10.dp, top = 25.dp)
        )
        CardHist5(navController = navController)
    }
}

@Composable
fun BottomBar5(navController: NavController) {
    Row(
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
        itemBar5("Cuestion.", Icons.Default.Star)
        itemBar5("Result.", Icons.Default.CheckCircle)
        itemBar5("Citas", Icons.Default.Favorite)
        itemBar5("Perfil", Icons.Default.AccountCircle)
    }
}

@Composable
fun itemBar5(texto: String, vector: ImageVector) {
    Column(
        modifier = Modifier
            .width(75.dp)
            .height(75.dp),
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
fun CardTest5(
    navController: NavController
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
                text = "Test "+"<ID>",
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
                        text = "Tipo de test: ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        text = "Puntuación: ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Text(
                        text = "Fecha: ",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(bottom = 5.dp)
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = 5.dp, top = 5.dp),
                        horizontalAlignment = Alignment.End
                    ) {
                        ClickableText(
                            text = AnnotatedString("Ver en historia >"),
                            onClick = {
                                /*add*/
                            },
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
    }
}

@Composable
fun CardHist5(
    navController: NavController
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
                text = "Historia "+"<ID>",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.secondary,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 10.dp)
            )
            Column(
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(
                    text = "Fecha de creación: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 3.dp)
                )
                Text(
                    text = "Estado: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 3.dp)
                )
                Text(
                    text = "Fecha de alta: ",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 3.dp)
                )
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 5.dp, top = 5.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    ClickableText(
                        text = AnnotatedString("Ver historia >"),
                        onClick = {
                            /*add*/
                        },
                        style = TextStyle(
                            color = MaterialTheme.colorScheme.onBackground,
                            fontSize = 15.sp,
                            textDecoration = TextDecoration.Underline
                        )
                    )
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun EstudResultScreenPreview() {
    val navController = rememberNavController()
    SisvitaG2Theme {
        EstudResultScreen(
            navController = navController
        )
    }
}