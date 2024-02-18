package com.alopgal962.proyectoadripmdim.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.alopgal962.proyectoadripmdim.apartadoregistrarse.Apartadoregistrarse
import com.alopgal962.proyectoadripmdim.userregister.UserRegister

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenRegister(){
    Scaffold(topBar = { Apartadoregistrarse(modifier = Modifier
        .fillMaxWidth()
        .height(250.dp)
        .clip(RoundedCornerShape(bottomStart = 200.dp, bottomEnd = 200.dp)))}) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally) {
            UserRegister(modifier= Modifier.size(90.dp,90.dp).padding(top = 30.dp))
            TextField(value = "", onValueChange = {  }, label = {Text("Introduce tu correo electronico", color = Color.White,modifier = Modifier.align(CenterHorizontally))}, modifier = Modifier.clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomEnd = 10.dp, bottomStart = 10.dp)).padding(top = 280.dp), colors = TextFieldDefaults.textFieldColors(containerColor = Color(35, 54, 71)))
            TextField(value = "", onValueChange = {  }, label = {Text("Introduce tu contraseña", color = Color.White,modifier = Modifier.align(CenterHorizontally))}, modifier = Modifier.clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomEnd = 10.dp, bottomStart = 10.dp)).padding(top = 30.dp), colors = TextFieldDefaults.textFieldColors(containerColor = Color(35, 54, 71)))
            TextField(value = "", onValueChange = {  }, label = {Text("Introduce de nuevo tu contraseña", color = Color.White, modifier = Modifier.align(CenterHorizontally))}, modifier = Modifier.clip(RoundedCornerShape(topStart = 10.dp, topEnd = 10.dp, bottomEnd = 10.dp, bottomStart = 10.dp)).padding(top = 30.dp), colors = TextFieldDefaults.textFieldColors(containerColor = Color(35, 54, 71)))
        }
    }
}