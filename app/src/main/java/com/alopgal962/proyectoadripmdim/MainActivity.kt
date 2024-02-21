package com.alopgal962.proyectoadripmdim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alopgal962.proyectoadripmdim.VM.VMFire
import com.alopgal962.proyectoadripmdim.ui.screens.LoginScreen
import com.alopgal962.proyectoadripmdim.ui.screens.MainScreen
import com.alopgal962.proyectoadripmdim.ui.screens.RegisterScreen
import com.alopgal962.proyectoadripmdim.ui.theme.ProyectoAdriPMDIMTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoAdriPMDIMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewmodelFire:VMFire by viewModels()
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "Register" ){
                        composable("Register"){ RegisterScreen(navController,viewmodelFire) }
                        composable("Login"){ LoginScreen(navController,viewmodelFire)}
                        composable("Main"){ MainScreen(navController,viewmodelFire )}
                    }
                }
            }
        }
    }
}
