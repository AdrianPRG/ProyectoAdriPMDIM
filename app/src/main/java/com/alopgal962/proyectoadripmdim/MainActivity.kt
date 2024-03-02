package com.alopgal962.proyectoadripmdim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.alopgal962.proyectoadripmdim.VM.AppViewmodel
import com.alopgal962.proyectoadripmdim.VM.LoginRegisterViewmodel
import com.alopgal962.proyectoadripmdim.model.Routes
import com.alopgal962.proyectoadripmdim.ui_views.screens.login_register.LoginScreen
import com.alopgal962.proyectoadripmdim.ui_views.screens.mainscreens.MainScreen
import com.alopgal962.proyectoadripmdim.ui_views.screens.login_register.RegisterScreen
import com.alopgal962.proyectoadripmdim.ui_views.screens.mainscreens.SeriesScreens
import com.alopgal962.proyectoadripmdim.ui_views.screens.mainscreens.UserDataScreen
import com.alopgal962.proyectoadripmdim.ui_views.theme.ProyectoAdriPMDIMTheme

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
                    val viewmodelFire:AppViewmodel by viewModels()
                    val viewmodelLogin:LoginRegisterViewmodel by viewModels()
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.screenlogin.route ){
                        composable(Routes.screenregister.route){ RegisterScreen(navController,viewmodelLogin) }
                        composable(Routes.screenlogin.route){ LoginScreen(navController,viewmodelLogin) }
                        composable(Routes.screenmain.route){ MainScreen(navController,viewmodelFire ) }
                        composable(Routes.screenseries.route){ SeriesScreens(navController,viewmodelFire ) }
                        composable(Routes.screenuser.route){ UserDataScreen(navController,viewmodelFire, viewmodelLogin ) }

                    }
                }
            }
        }
    }
}
