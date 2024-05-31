package com.example.sisvitacus1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.sisvita_cus1.data.model.Estudiante
import com.example.sisvitacus1.ui.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerViewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)

        // Run the registration logic
        registerStudent()
    }

    private fun registerStudent() {
        // Create a sample Estudiante object
        val estudiante = Estudiante(
            nom_usu = "santiago",
            pat_usu = "cubas",
            mat_usu = "huaranga",
            nacion_usu = "alemana",
            tipo_doc_usu = "DNI",
            num_doc_usu = 70600300,
            sexo_usu = "Masculino",
            edad_usu = 30,
            cel_usu = 999666777,
            email_usu = "santiago@gmail.com",
            contra_usu = "password123",
            id_est = 1,
            est_civ_est = "Soltero",
            nom_univ_est = "UNMSM"
        )

        // Use a coroutine to perform the registration
        lifecycleScope.launch {
            try {
                val result = registerViewModel.registrarEstudiante(estudiante)
                println("Estudiante registrado con éxito: $result")
            } catch (e: Exception) {
                println("Error al registrar el estudiante: ${e.message}")
            }
        }
    }
}
