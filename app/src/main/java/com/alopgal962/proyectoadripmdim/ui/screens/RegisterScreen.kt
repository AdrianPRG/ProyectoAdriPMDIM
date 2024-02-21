package com.alopgal962.proyectoadripmdim.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
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
fun RegisterScreen(navController: NavController, ViewmodelVM: VMFire) {
    var email by ViewmodelVM.correoelectronico
    var contrasena by ViewmodelVM.contrasena
    var repitecontrasena by ViewmodelVM.repitecontrasena

    Scaffold(topBar = {
        Row(
            Modifier
                .fillMaxWidth()
                .height(240.dp)
                .clip(RoundedCornerShape(bottomStart = 200.dp, bottomEnd = 200.dp))
                .background(Color(35, 54, 71)), Arrangement.Center, Alignment.CenterVertically
        ) {
            Text(
                text = "Registrarse",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Serif
            )
        }
    }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 265.dp), horizontalAlignment = CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.registerfoto),
                contentDescription = "Foto Usuario",
                Modifier.size(105.dp, 100.dp)
            )
            TextField(value = email,
                onValueChange = { email = it },
                label = {
                    Text(
                        "Introduce tu correo electronico",
                        color = Color.White,
                        modifier = Modifier.align(CenterHorizontally)
                    )
                },
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp,
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        )
                    )
                    .padding(top = 20.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(35, 54, 71), textColor = Color.White),
                maxLines = 1
            )
            TextField(value = contrasena,
                onValueChange = { contrasena = it },
                label = {
                    Text(
                        "Introduce tu contraseña",
                        color = Color.White,
                        modifier = Modifier.align(CenterHorizontally)
                    )
                },
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp,
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        )
                    )
                    .padding(top = 20.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(35, 54, 71), textColor = Color.White),
                maxLines = 1
            )
            TextField(value = repitecontrasena,
                onValueChange = { repitecontrasena = it },
                label = {
                    Text(
                        "Repite tu contraseña",
                        color = Color.White,
                        modifier = Modifier.align(CenterHorizontally)
                    )
                },
                modifier = Modifier
                    .clip(
                        RoundedCornerShape(
                            topStart = 15.dp,
                            topEnd = 15.dp,
                            bottomEnd = 15.dp,
                            bottomStart = 15.dp
                        )
                    )
                    .padding(top = 20.dp),
                colors = TextFieldDefaults.textFieldColors(containerColor = Color(35, 54, 71), textColor = Color.White),
                maxLines = 1
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Button(
                    onClick = { ViewmodelVM.crearemailycontrasena({navController.navigate("Login")})},
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .size(150.dp, 70.dp),
                    colors = ButtonDefaults.buttonColors(Color(110, 149, 114))
                ) {
                    Text(
                        text = "Registrarse",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                }
                Button(
                    onClick = { ViewmodelVM.borrarcampos() },
                    modifier = Modifier
                        .clip(RoundedCornerShape(20.dp))
                        .size(150.dp, 70.dp)
                        .padding(start = 20.dp),
                    colors = ButtonDefaults.buttonColors(Color(210, 120, 120))
                ) {
                    Text(
                        text = "Cancelar",
                        fontFamily = FontFamily.Serif,
                        fontWeight = FontWeight.Bold,
                        fontSize = 17.sp
                    )
                }
            }
        }
    }
}
