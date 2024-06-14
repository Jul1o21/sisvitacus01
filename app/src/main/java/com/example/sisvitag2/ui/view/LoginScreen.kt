package com.example.sisvitag2.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvitag2.ui.viewmodel.LoginViewModel
import androidx.compose.foundation.lazy.LazyColumn


@Composable
fun LoginScreen (
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel(), // Inicializa el LoginViewModel
) {

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar()
        Content()
    }
}

@Composable
fun TopBar() {
    Box (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.1F)
            .background(MaterialTheme.colorScheme.primary)
            .padding(15.dp)
    ) {
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

@Composable
fun Content() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Text(
                text = "Iniciar Sesión",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 30.dp),
                textAlign = TextAlign.Center
            )
        }
        item {
            Formulario()
        }
    }
}

@Composable
fun Formulario() {

    val loginViewModel: LoginViewModel = viewModel()

    val correo: String by loginViewModel.correoState
    val contrasenia: String by loginViewModel.contraseniaState


    val rememberMe: Boolean by loginViewModel.rememberMeState
    val isLoading: Boolean by loginViewModel.isLoading
    val isError: Boolean by loginViewModel.isError
    val loginSuccess: Boolean by loginViewModel.loginSuccess

    
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        //Correo
        Text(
            text = "Correo",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp),
            textAlign = TextAlign.Left
        )

        TextField(
            value = correo,
            onValueChange = { loginViewModel.setEmail(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email
            ),
            placeholder = { Text(text = "correo@dominio.com") }
        )

        //Contraseña
        Text(
            text = "Contraseña",
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp, bottom = 10.dp),
            textAlign = TextAlign.Left
        )
        TextField(
            value = contrasenia,
            onValueChange = { loginViewModel.setPassword(it) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp),
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password
            ),
            placeholder = { Text(text = "********") }
        )
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = false,
                onCheckedChange = { it }
            )
            Text(
                text = "Recordarme",
                fontSize = 18.sp
            )
        }
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp),
            onClick = { /*TODO*/ },
        ) {
            Text(
                text = "Iniciar Sesión",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(5.dp)
            )
        }
    }

}


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(loginViewModel: LoginViewModel = viewModel()) {
    val navController = rememberNavController()
    SisvitaG2Theme {
        LoginScreen(navController = navController)
    }
}