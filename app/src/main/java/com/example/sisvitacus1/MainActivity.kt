package com.example.sisvitacus1
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.sisvitacus1.ui.theme.Sisvitacus1Theme
import com.example.sisvitacus1.ui.view.MainScreen
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Sisvitacus1Theme {
                val navController = rememberNavController()
                MainScreen(navController = navController)
            }
        }
    }
}

