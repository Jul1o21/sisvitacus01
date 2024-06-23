package com.example.sisvitag2.ui.view

import android.graphics.Paint.Style
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.R
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvitag2.ui.viewmodel.MainViewModel

@Composable
fun MainScreen(navController: NavController) {
    Column (
        modifier = Modifier.fillMaxSize()
    ) {
        Box (

        ){
            // Fondo
            Image(
                painter = painterResource(id = R.drawable.main_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            // Contenido
            Column (
                modifier = Modifier
                    .padding(30.dp)
            ) {
                // Dos secciones: titulo y botones
                titulo()
                botones(navController)
            }
        }
    }
}

@Composable
fun titulo() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.75F),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Titulo
        Text(
            text = "SISVITA",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(top = 30.dp, bottom = 20.dp)
        )

        // Imagen
        Image(
            painter = painterResource(id = R.drawable.main_image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth(.9F)
                .fillMaxHeight(.8F)
        )
    }
}

@Composable
fun botones(navController: NavController) {
    Column (
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = 20.dp)
    ) {
        boton(
            texto = "Ingresar como Invitado",
            nav = { navController.navigate(AppScreen.loginScreen.route) }
        )
        botonLinea(
            texto = "Iniciar Sesion",
            nav = { navController.navigate(AppScreen.loginScreen.route) }
        )
        boton(
            texto = "Registrarse",
            nav = { navController.navigate(AppScreen.estudRegisterScreen.route) }
        )
    }
}

@Composable
fun boton (
    texto: String,
    nav: () -> Unit
) {
    Button(
        modifier = Modifier
            .fillMaxWidth(.75F),
        onClick = { nav() }
    ) {
        textoBoton(texto)
    }
}

@Composable
fun botonLinea (
    texto: String,
    nav: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth(.75F),
        onClick = { nav() }
    ) {
        textoBoton(texto)
    }
}

@Composable
fun textoBoton (
    texto: String
) {
    Text(
        text = texto,
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(4.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    SisvitaG2Theme {
        MainScreen (navController = navController)
    }
}