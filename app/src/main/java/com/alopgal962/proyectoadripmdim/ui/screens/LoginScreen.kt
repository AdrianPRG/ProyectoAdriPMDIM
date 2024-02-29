package com.alopgal962.proyectoadripmdim.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alopgal962.proyectoadripmdim.R
import com.alopgal962.proyectoadripmdim.VM.VMFire

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController,viewmodel: VMFire) {
    Scaffold(topBar = {
        Row(
            Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(RoundedCornerShape(bottomStart = 200.dp, bottomEnd = 200.dp))
                .background(Color.White), Arrangement.Center, Alignment.CenterVertically
        ) {
            Text(
                text = "Iniciar Sesión",
                color = Color.Black,
                fontSize = 37.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(35, 54, 71))
                .padding(top = 265.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.loginfoto),
                contentDescription = "Foto Usuario Login",
                Modifier.size(105.dp, 100.dp)
            )
            TextField(
                value = viewmodel.correoelectronico,
                onValueChange = { viewmodel.correoelectronico = it },
                label = {
                    Text(
                        "Introduce tu correo electronico",
                        color = Color(35, 54, 71),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                },
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 15.dp, topEnd = 15.dp,
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        )
                    )
                    .padding(top = 30.dp)
                    .size(290.dp, 60.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                maxLines = 1

            )
            TextField(
                value =viewmodel.contrasena,
                onValueChange = { viewmodel.contrasena = it },
                label = {
                    Text(
                        "Introduce tu contraseña",
                        color = Color(35, 54, 71),
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                },
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 15.dp, topEnd = 15.dp,
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        )
                    )
                    .padding(top = 35.dp)
                    .size(290.dp, 60.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color.White),
                maxLines = 1
            )
            Button(
                onClick = { viewmodel.iniciarsesion { navController.navigate("Main") } },
                modifier = Modifier
                    .size(150.dp, 90.dp)
                    .padding(top = 35.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(110, 149, 114))
            ) {
                Text(
                    text = "Iniciar",
                    fontFamily = FontFamily.Serif,
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
            Text(text = " ⚫ No tengo cuenta ", color = Color.White, fontWeight = FontWeight.Bold, modifier = Modifier.padding(end = 160.dp, top = 55.dp).clickable { navController.navigate("Register") })
        }
    }
}