package com.alopgal962.proyectoadripmdim.ui_views.screens.mainscreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
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
import com.alopgal962.proyectoadripmdim.model.Routes
import com.alopgal962.proyectoadripmdim.ui_views.screens.components.Bottombar
import com.alopgal962.proyectoadripmdim.ui_views.screens.components.Topbar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserDataScreen(navController: NavController,viewmodel:AppViewmodel){
    Scaffold(topBar = { Topbar() }, bottomBar = { Bottombar(
        onCasaClick = { navController.navigate(Routes.screenmain.route) },
        onSeriesClick = { navController.navigate(Routes.screenseries.route)
        viewmodel.obtenerSeries()}) {
    }
    } ) {
        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 110.dp, bottom = 110.dp)
                .background(color = Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally) {
            Column(
                Modifier
                    .size(320.dp, 370.dp)
                    .padding(top = 40.dp)
                    .clip(
                        RoundedCornerShape(30.dp)
                    )
                    .background(Color(35, 54, 71)), horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.userstadistics), contentDescription = "icono de la app", modifier = Modifier
                    .size(100.dp, 100.dp)
                    .padding(top = 10.dp), colorFilter = ColorFilter.tint(
                    Color.White))
                Text(text = "Datos de: ${viewmodel.firebaseauth.currentUser!!.email}",Modifier.padding(top = 12.dp), color = Color.White, fontWeight = FontWeight.Bold, fontSize = 21.sp, fontFamily = FontFamily.SansSerif)
                Text(text = "Total de series calificadas  : ${viewmodel.numseriesdeUser}",Modifier.padding(top = 20.dp), color = Color.White)
                Text(text = "Media de calificaciones : ${viewmodel.mediadeUser.toString().substring(0,3)} ⭐",Modifier.padding(top = 20.dp), color = Color.White)
            }
        }
    }
}