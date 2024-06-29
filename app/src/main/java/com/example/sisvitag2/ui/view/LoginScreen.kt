package com.example.sisvitag2.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
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
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.IconButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.text.input.ImeAction
import com.example.sisvitacus1.navigation.AppScreen



@Composable
fun LoginScreen (
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel(), // Inicializa el LoginViewModel
) {

    val showDialog by loginViewModel.showDialog
    val dialogMessage by loginViewModel.dialogMessage

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar(navController)
        Content(navController,loginViewModel)
    }

    if (showDialog) {
        AlertDialog(
            onDismissRequest = { loginViewModel.dismissDialog() },
            title = {
                Text(text = "Notificación")
            },
            text = {
                Text(text = dialogMessage)
            },
            confirmButton = {
                Button(
                    onClick = { loginViewModel.dismissDialog() }
                ) {
                    Text("OK")
                }
            }
        )
    }
}

@Composable
fun TopBar(navController: NavController) {
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
fun Content(
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Iniciar Sesión",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 40.dp),
            textAlign = TextAlign.Center
        )
        Formulario(navController, loginViewModel)
    }
}

@Composable
fun Formulario(
    navController: NavController,
    loginViewModel: LoginViewModel
) {
    val correo: String by loginViewModel.correoState
    val contrasenia: String by loginViewModel.contraseniaState
    val isError: Boolean by loginViewModel.isError
    val loginSuccess: Boolean by loginViewModel.loginSuccess
    val showDialog: Boolean by loginViewModel.showDialog
    val dialogMessage:String by loginViewModel.dialogMessage

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {

        //Correo
        subtexto( texto = "Correo" )
        inputText(
            value = correo,
            onValueChange = { loginViewModel.setEmail(it) },
            placeholder = "example@example.com",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )

        //Contraseña
        subtexto( texto = "Contraseña" )
        inputText(
            value = contrasenia,
            onValueChange = { loginViewModel.setPassword(it) },
            placeholder = "********",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done
            )
        )

        // Enviar credenciales para verificar
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp)
        ) {
            botonLogin(
                texto = "Iniciar Sesión",
                nav = { loginViewModel.login() }
            )
        }
        
        // Error en el login
        if (isError) {
            Text(
                text = "Correo o contraseña incorrectos, por favor intente de nuevo...",
                color = MaterialTheme.colorScheme.error,
                fontSize = 16.sp,
                modifier = Modifier.padding(top = 20.dp)
            )
        }


        if (loginSuccess) {
            val estudiante = loginViewModel.estudiante.value
            val especialista = loginViewModel.especialista.value
            estudiante?.let {
                LaunchedEffect(estudiante) {
                    println("Estudiante en la ventana: $estudiante")

                    // navController.navigate(AppScreen.estudTestsListScreen.createRoute(estudiante))
                    navController.navigate(AppScreen.estudMenuScreen.createRoute(estudiante))
                }
            } ?: especialista?.let {
                loginViewModel.setDialogMessage("Bienvenido, ${it.nombre_completo}. Aún no se ha implementado el menú para especialistas.")
                loginViewModel.mostrarDialog()
                LaunchedEffect(especialista) {
                    println("Especialista en la ventana: $especialista")
                    navController.navigate(AppScreen.espMainScreen.createRoute(especialista))
                }
            }
        }

        if (showDialog) {
            AlertDialog(
                onDismissRequest = { loginViewModel.dismissDialog() },
                title = {
                    Text(text = "Notificación")
                },
                text = {
                    Text(text = dialogMessage)
                },
                confirmButton = {
                    Button(
                        onClick = { loginViewModel.dismissDialog() }
                    ) {
                        Text("OK")
                    }
                }
            )
        }
    }
}


@Composable
fun subtexto (
    texto: String
) {
    Text(
        text = texto,
        color = MaterialTheme.colorScheme.secondary,
        fontSize = 17.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 5.dp, bottom = 10.dp),
        textAlign = TextAlign.Left
    )
}

@Composable
fun inputText (
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions
) {
    TextField(
        value = value,
        onValueChange = { onValueChange(it) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 20.dp),
        keyboardOptions = keyboardOptions,
        placeholder = {
            Text(text = placeholder)
        }
    )
}

@Composable
fun botonLogin (
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


@Preview(showBackground = true)
@Composable
fun LoginScreenPreview(loginViewModel: LoginViewModel = viewModel()) {
    val navController = rememberNavController()
    SisvitaG2Theme {
        LoginScreen(navController = navController)
    }
}