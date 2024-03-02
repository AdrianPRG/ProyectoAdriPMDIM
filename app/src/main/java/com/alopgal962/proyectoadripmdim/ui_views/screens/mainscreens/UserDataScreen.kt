package com.alopgal962.proyectoadripmdim.ui_views.screens.mainscreens

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
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
import com.alopgal962.proyectoadripmdim.ui_views.screens.components.Bottombar
import com.alopgal962.proyectoadripmdim.ui_views.screens.components.Topbar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserDataScreen(
    navController: NavController,
    viewmodel: AppViewmodel,
    viewmodelAuth: LoginRegisterViewmodel
) {
    Scaffold(topBar = { Topbar() }, bottomBar = {
        Bottombar(
            onCasaClick = { navController.navigate(Routes.screenmain.route) },
            onSeriesClick = {
                navController.navigate(Routes.screenseries.route)
                viewmodel.obtenerSeries()
            }) {
        }
    }) {
        if (viewmodel.pulsaborrar == false) {
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 110.dp, bottom = 110.dp)
                    .background(color = Color(232, 239, 236)),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Column(
                    Modifier
                        .size(330.dp, 300.dp)
                        .clip(
                            RoundedCornerShape(30.dp)
                        )
                        .background(Color(35, 54, 71)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.userstadistics),
                        contentDescription = "icono de la app",
                        modifier = Modifier
                            .size(100.dp, 100.dp)
                            .padding(top = 10.dp),
                        colorFilter = ColorFilter.tint(
                            Color.White
                        )
                    )
                    Text(
                        text = "Datos de: ${viewmodel.firebaseauth.currentUser!!.email}",
                        Modifier.padding(top = 12.dp),
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = 21.sp,
                        fontFamily = FontFamily.SansSerif
                    )
                    Row(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .fillMaxWidth()
                            .height(70.dp), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.numeros),
                            contentDescription = "Imagen numero calificaciones",
                            modifier = Modifier.size(50.dp, 50.dp)
                        )
                        Text(
                            text = "Total de calificaciones: ${viewmodel.numseriesdeUser}",
                            Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 8.dp), color = Color.White
                        )
                    }
                    Row(
                        modifier = Modifier
                            .padding(start = 12.dp)
                            .fillMaxWidth()
                            .height(70.dp), verticalAlignment = Alignment.CenterVertically
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.media),
                            contentDescription = "Imagen media calificaciones",
                            modifier = Modifier.size(50.dp, 50.dp)
                        )
                        Text(
                            text = "Media de calificaciones: ${
                                viewmodel.mediadeUser.toString().substring(0, 3)
                            }",
                            Modifier
                                .align(Alignment.CenterVertically)
                                .padding(start = 2.dp), color = Color.White
                        )
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.Center, modifier = Modifier
                        .padding(top = 40.dp)
                        .size(300.dp, 50.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            Color(35, 54, 71)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logout),
                        colorFilter = ColorFilter.tint(Color.Yellow),
                        contentDescription = "Imagen log Out",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(30.dp, 40.dp)
                    )
                    Text(text = "Cerrar Sesion ",
                        fontSize = 17.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(start = 10.dp, top = 16.dp)
                            .clickable {
                                viewmodelAuth.borrarcampos()
                                navController.navigate(Routes.screenlogin.route)
                            })
                }
                Row(
                    horizontalArrangement = Arrangement.Center, modifier = Modifier
                        .padding(top = 40.dp)
                        .size(300.dp, 50.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .background(
                            Color(35, 54, 71)
                        )
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.papelera),
                        colorFilter = ColorFilter.tint(Color.Red),
                        contentDescription = "Imagen Papelera",
                        modifier = Modifier
                            .padding(8.dp)
                            .size(30.dp, 40.dp)
                    )
                    Text(text = "Borrar todas las series",
                        fontSize = 17.sp,
                        color = Color.White,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier
                            .padding(start = 10.dp, top = 16.dp)
                            .clickable { viewmodel.pulsaborrar = true })
                }
            }
        } else
            AlertDialog(
                title = { Text(text = "¿Desea borrar las series?") },
                onDismissRequest = { },
                dismissButton = {
                    Text(
                        text = "No, mantenerlas",
                        modifier = Modifier.clickable { viewmodel.pulsaborrar = false }.padding(end = 75.dp))
                },
                confirmButton = {
                    Text(text = "Si, borrarlas", modifier = Modifier.clickable { viewmodel.seriesDeleted() })
                })
    }
}