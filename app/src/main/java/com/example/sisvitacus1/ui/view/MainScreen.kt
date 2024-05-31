package com.example.sisvitacus1.ui.view
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sisvita_cus1.data.model.Estudiante
import com.example.sisvitacus1.ui.viewmodel.RegisterViewModel
import androidx.navigation.compose.rememberNavController
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController) {
    val viewModel: RegisterViewModel = viewModel()

    Scaffold(

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "SISVITA",
                fontSize = 40.sp,
                color = Color(8,83,148),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))
            GridButtons(navController, viewModel)
        }
    }
}

@Composable
fun GridButtons(navController: NavController, viewModel: RegisterViewModel) {
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


fun registerStudent(viewModel: RegisterViewModel) {
    val estudiante = Estudiante(
        nom_usu = "santiaguito",
        pat_usu = "cubas",
        mat_usu = "huaranga",
        nacion_usu = "peruana",
        tipo_doc_usu = "DNI",
        num_doc_usu = 70500400,
        sexo_usu = "Masculino",
        edad_usu = 30,
        cel_usu = 888888777,
        email_usu = "santi@gmail.com",
        contra_usu = "julito123",
        id_est = 0,
        est_civ_est = "Soltero",
        nom_univ_est = "UNMSM"
    )

    // Use a coroutine to perform the registration
    viewModel.registrarEstudiante(estudiante)
}
@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    val navController = rememberNavController()
    val viewModel = RegisterViewModel()

    MainScreen(navController = navController)
}