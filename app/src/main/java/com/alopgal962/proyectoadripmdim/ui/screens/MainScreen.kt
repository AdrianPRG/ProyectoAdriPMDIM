package com.alopgal962.proyectoadripmdim.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.alopgal962.proyectoadripmdim.VM.VMFire

@Composable
fun MainScreen(navController: NavController, ViewmodelVM: VMFire){
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "hOLA")
    }
}