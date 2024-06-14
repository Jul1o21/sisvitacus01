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
            Image(
                painter = painterResource(id = R.drawable.main_background),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )
            Column (
                modifier = Modifier
                    .padding(30.dp)
            ) {
                titulo()
                botones()
            }
        }
    }
}

@Composable
fun titulo() {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(.7F),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "SISVITA",
            fontSize = 40.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            modifier = Modifier
                .padding(top = 30.dp, bottom = 20.dp)
        )
        Image(
            painter = painterResource(id = R.drawable.main_image),
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(.8F)
        )
    }
}

@Composable
fun botones() {
    Column (
        verticalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Ingresar como Invitado",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(5.dp)
            )
        }
        OutlinedButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Iniciar Sesión",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .padding(5.dp)
            )
        }
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = { /*TODO*/ }
        ) {
            Text(
                text = "Registrarse",
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
fun MainScreenPreview() {
    val navController = rememberNavController()
    SisvitaG2Theme {
        MainScreen(navController = navController)
    }
}