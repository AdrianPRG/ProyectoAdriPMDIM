package com.alopgal962.proyectoadripmdim.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.alopgal962.proyectoadripmdim.VM.VMFire

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController, viewmodel: VMFire){
    Scaffold(topBar = { Topbar()}, bottomBar = { Bottombar(onCasaClick = { /*TODO*/ }, onSeriesClick = { navController.navigate("Series")
        viewmodel.obtenerdatos()}, onUserClick = { /*TODO*/})}){
        Column(Modifier.fillMaxSize().background(Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            }

        }
    }
}