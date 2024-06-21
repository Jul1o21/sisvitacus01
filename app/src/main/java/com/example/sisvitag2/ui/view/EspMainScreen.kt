package com.example.sisvitag2.ui.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.sisvita_cus1.data.model.Especialista
import com.example.sisvitag2.ui.theme.SisvitaG2Theme
import com.example.sisvitag2.ui.viewmodel.EspMainViewModel

@Composable
fun EspMainScreen (
    navController: NavController,
    id_esp: Int
) {
    val especialista = remember { mutableStateOf<Especialista?>(null) }

    LaunchedEffect(id_esp) {
        especialista.value = Especialista(id_esp = id_esp)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        TopBarEsp()
        ContentEsp(especialista)
        BottomBarEsp(especialista)
    }
}

@Composable
fun TopBarEsp() {
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
fun ContentEsp(
    especialista: MutableState<Especialista?>
) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.89F)
            .padding(start = 30.dp, end = 30.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        val id_esp = especialista.value?.id_esp ?: -1
        Text(
            text = "Bienvenido Especialista $id_esp!",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 30.dp, bottom = 30.dp),
            textAlign = TextAlign.Left
        )
        Text(
            text = "Aquí puedes ver y gestionar las citas, observaciones y tratamientos.",
            fontSize = 18.sp,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
        )
        // Aquí puedes agregar más Cards o elementos para mostrar información relevante para el especialista
    }
}

@Composable
fun BottomBarEsp(
    especialista: MutableState<Especialista?>
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
                imageVector = Icons.Default.CheckCircle,
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .align(Alignment.CenterHorizontally),
                tint = MaterialTheme.colorScheme.onSecondary
            )
            Text(
                text = "Observ.",
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
                text = "Tratam.",
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
fun EspMainScreenPreview(espMainModel: EspMainViewModel = viewModel()) {
    val navController = rememberNavController()
    SisvitaG2Theme {
        EspMainScreen(
            navController=navController,
            id_esp = 0
        )
    }
}
