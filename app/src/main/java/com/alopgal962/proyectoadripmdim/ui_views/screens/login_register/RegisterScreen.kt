package com.alopgal962.proyectoadripmdim.ui_views.screens.login_register

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
import com.alopgal962.proyectoadripmdim.VM.AppViewmodel
import com.alopgal962.proyectoadripmdim.VM.LoginRegisterViewmodel
import com.alopgal962.proyectoadripmdim.model.Routes

/**
 * @author AdrianPRG
 *
 * @param navController servirá para navegar hacia las distintas pantallas
 * @param viewmodel es el viewmodel que maneja el inicio de sesion
 */
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegisterScreen(navController: NavController, ViewmodelVM: LoginRegisterViewmodel) {
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
            //Textfield para el valor del correo electronico
            TextField(
                value = ViewmodelVM.correoelectronico,
                onValueChange = { ViewmodelVM.correoelectronico = it },
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
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(35, 54, 71),
                    textColor = Color.White
                ),
                maxLines = 1
            )
            //Textfield para el valor de la contraseña
            TextField(
                value = ViewmodelVM.contrasena,
                onValueChange = { ViewmodelVM.contrasena = it },
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
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(35, 54, 71),
                    textColor = Color.White
                ),
                maxLines = 1
            )
            //Textfield para el valor de la contraseña repetida,
            //Tiene que ser la misma contraseña que se puso arriba, si no, no dejará registrarse
            //Es un buen metodo para que el usuario recuerde su contraseña
            TextField(
                value = ViewmodelVM.repitecontrasena,
                onValueChange = { ViewmodelVM.repitecontrasena = it },
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
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(35, 54, 71),
                    textColor = Color.White
                ),
                maxLines = 1
            )
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                /**
                 * Al hacer click en el boton de registrarse, navegamos hacia login, se navega al login y no al main ya que en el login
                 * se encuentra la funcion que mete al usuario / lo actualiza en la base de datos. Ademas, se borran los campos para que no aparezcan
                 * en los textfields del login
                 */
                Button(
                    onClick = { ViewmodelVM.crearemailycontrasena({ navController.navigate(Routes.screenlogin.route) })
                              ViewmodelVM.borrarcampos()},
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
                   // Este Botón llama a la funcion del viewmodel que reinicia los campos
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
            //Si el usuario tiene una cuenta, al hacer click en el texto se reinician los campos y se navega a la pantalla de login
            Text(text = "⚫ Ya tengo una cuenta", fontWeight = FontWeight.SemiBold, color = Color(35, 54, 71), modifier = Modifier.padding(top = 14.dp, end = 80.dp).clickable { navController.navigate(Routes.screenlogin.route)
                ViewmodelVM.borrarcampos()})
        }
    }
}
