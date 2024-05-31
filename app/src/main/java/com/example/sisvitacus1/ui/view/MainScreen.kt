package com.example.sisvitacus1.ui.view
import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sisvita_cus1.data.model.Estudiante
import com.example.sisvitacus1.registerStudent
import com.example.sisvitacus1.ui.viewmodel.RegisterViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavController) {
    val viewModel: RegisterViewModel = viewModel()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "MENÚ",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color(8,83,148))
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¡Bienvenido!",
                fontSize = 40.sp,
                color = Color(8,83,148),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Text(
                text = "Seleccione una opción:",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(top = 16.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
            GridButtons(navController, viewModel)
        }
    }
}

@Composable
fun GridButtons(navController: NavController, viewModel: RegisterViewModel) {
    val buttonColors = ButtonDefaults.buttonColors(Color(8, 83, 148))
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

@Composable
fun registerStudent(viewModel: RegisterViewModel) {
    // Create a sample Estudiante object
    val estudiante = Estudiante(
        nom_usu = "santiago",
        pat_usu = "cubas",
        mat_usu = "huaranga",
        nacion_usu = "peruana",
        tipo_doc_usu = "DNI",
        num_doc_usu = 70600300,
        sexo_usu = "Masculino",
        edad_usu = 30,
        cel_usu = 999666777,
        email_usu = "santiago@gmail.com",
        contra_usu = "santiago123",
        id_est = 1,
        est_civ_est = "Soltero",
        nom_univ_est = "UNMSM"
    )

    // Use a coroutine to perform the registration
    viewModel.registrarEstudiante(estudiante)
}
