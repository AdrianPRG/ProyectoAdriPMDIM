package com.alopgal962.proyectoadripmdim.ui_views.screens.mainscreens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.alopgal962.proyectoadripmdim.VM.AppViewmodel
import com.alopgal962.proyectoadripmdim.model.Routes
import com.alopgal962.proyectoadripmdim.ui_views.screens.components.Bottombar
import com.alopgal962.proyectoadripmdim.ui_views.screens.components.Topbar

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController, viewmodel: AppViewmodel) {
    Scaffold(topBar = { Topbar() }, bottomBar = {
        Bottombar(onCasaClick = { /*TODO*/ }, onSeriesClick = {
            navController.navigate(Routes.screenseries.route)
            viewmodel.obtenerSeries()
        }, onUserClick = {
            navController.navigate(Routes.screenuser.route)
            viewmodel.obtenerdatosUsuario()
        })
    }) {
        Column(
            Modifier
                .fillMaxSize()
                .background(Color(232, 239, 236)),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

        }
    }
}