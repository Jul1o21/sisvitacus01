package com.example.sisvitag2.ui.view.estudiante

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sisvitacus1.navigation.AppScreen
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvitag2.ui.view.otro.textoBoton
import com.example.sisvitag2.ui.viewmodel.EstudRegisterViewModel

@Composable
fun EstudRegisterScreen (
    navController: NavController,
    registerViewModel: EstudRegisterViewModel = viewModel()
) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBar1(navController)
        Content1(registerViewModel)
    }
}

@Composable
fun TopBar1(navController: NavController) {
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
fun Content1(
    registerViewModel: EstudRegisterViewModel
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(30.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Registrase",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 30.dp),
            textAlign = TextAlign.Center
        )
        Formulario1(registerViewModel)
    }
}

@Composable
fun Formulario1(
    registerViewModel: EstudRegisterViewModel
) {
    val nombres: String by registerViewModel.nombresState
    val paterno: String by registerViewModel.paternoState
    val materno: String by registerViewModel.maternoState
    val tipo: String by registerViewModel.tipoState
    val correo: String by registerViewModel.correoState
    val contra: String by registerViewModel.contraState

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        // Nombres y apellidos
        subtexto1( texto = "Nombres" )
        inputText1(
            value = nombres,
            onValueChange = { registerViewModel.setNombres(it) },
            placeholder = "Nombres",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        subtexto1( texto = "Apellido Paterno" )
        inputText1(
            value = paterno,
            onValueChange = { registerViewModel.setPaterno(it) },
            placeholder = "Apellido paterno",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )
        subtexto1( texto = "Apellido Materno" )
        inputText1(
            value = materno,
            onValueChange = { registerViewModel.setMaterno(it) },
            placeholder = "Apellido materno",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        // Tipo de usuario
        subtexto1( texto = "Tipo de usuario" )
        desplegable1(
            value = tipo,
            onValueChange = { registerViewModel.setTipo(it) },
            placeholder = "Tipo de usuario",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next
            )
        )

        // Correo y contraseña
        subtexto1( texto = "Correo" )
        inputText1(
            value = correo,
            onValueChange = { registerViewModel.setCorreo(it) },
            placeholder = "example@example.com",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            )
        )
        subtexto1( texto = "Contraseña" )
        inputText1(
            value = contra,
            onValueChange = { registerViewModel.setContra(it) },
            placeholder = "********",
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Password,
                imeAction = ImeAction.Done
            )
        )

        //Boton para enviar
        Column (
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            boton1(
                texto = "Registrarse",
                nav = { "" }
            )
        }
    }
}

@Composable
fun subtexto1 (
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
fun inputText1 (
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
fun boton1 (
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
fun desplegable1 (
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


@Preview(showBackground = true)
@Composable
fun EstudRegisterScreenPreview(estudRegisterModel: EstudRegisterViewModel = viewModel()) {
    val navController = rememberNavController()
    SisvitaG2Theme {
        EstudRegisterScreen(navController=navController)
    }
}