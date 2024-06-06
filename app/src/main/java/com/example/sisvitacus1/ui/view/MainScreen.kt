package com.example.sisvitacus1.ui.view
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sisvita_cus1.data.model.Estudiante
import com.example.sisvitacus1.ui.viewmodel.RegEstudianteViewModel
import androidx.navigation.compose.rememberNavController
import com.example.sisvitacus1.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val viewModel: RegEstudianteViewModel = viewModel()

    Scaffold(

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFFB3E5FC),
                            Color(0xFF03A9F4)
                        )
                    )
                )
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            Text(
                text = "SISVITA",
                fontSize = 40.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 32.dp)
            )

            // Image
            val image = painterResource(R.drawable.manejo_del_estres)
            Image(
                painter = image,
                contentDescription = null,
                modifier = Modifier
                    .height(224.dp)
                    .width(285.dp)
                    .padding(bottom = 32.dp)
            )

            // Register Button
            Button(
                onClick = { /* TODO: Navegar a la pantalla de registro */ },
                colors = ButtonDefaults.buttonColors(containerColor  = Color(0xFF7BB2E4)),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
            ) {
                Text("Registrarse")
            }

            // Already have an account Button
            Button(
                onClick = { /* TODO: Navegar a la pantalla de login */ },
                colors = ButtonDefaults.buttonColors(containerColor  = Color.White),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text("Ya tengo una cuenta", color = Color(0xFF7BB2E4))
            }


            Spacer(modifier = Modifier.height(16.dp))
            GridButtons(navController, viewModel)
        }
    }
}

@Composable
fun GridButtons(navController: NavController, viewModel: RegEstudianteViewModel) {
    val buttonColors = ButtonDefaults.buttonColors(Color(150, 20, 20))
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = { registerStudent(viewModel) }) {
                Text("Registrar Estudiante")
            }
        }
    }
}


fun registerStudent(viewModel: RegEstudianteViewModel) {
    val estudiante = Estudiante(
        nom_usu = "juancito",
        pat_usu = "alegria",
        mat_usu = "cermeño",
        nacion_usu = "peruana",
        tipo_doc_usu = "DNI",
        num_doc_usu = 20500400,
        sexo_usu = "Masculino",
        edad_usu = 20,
        cel_usu = 686888777,
        email_usu = "juan@gmail.com",
        contra_usu = "juancito123",
        id_est = 0,
        est_civ_est = "Soltero",
        nom_univ_est = "UNMSM"
    )

    viewModel.registrarEstudiante(estudiante)
}
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    val viewModel = RegEstudianteViewModel()

    MainScreen(navController = navController)
}