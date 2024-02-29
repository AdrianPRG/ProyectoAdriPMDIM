package com.alopgal962.proyectoadripmdim.ui_views.screens.mainscreens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alopgal962.proyectoadripmdim.R
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
                .padding()
                .fillMaxSize()
                .background(Color(232, 239, 236)),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(modifier = Modifier
                .padding(40.dp)
                .size(300.dp, 260.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(color = Color(33, 93, 46)), horizontalAlignment = Alignment.CenterHorizontally) {
                Column(modifier = Modifier.size(300.dp,200.dp)) {
                    Image(painter = painterResource(id = R.drawable.series), contentDescription = "introduccionIMG", modifier = Modifier
                        .fillMaxSize()
                        .padding(bottom = 30.dp))
                }
                Text(text = "Tus series, siempre contigo ;)", fontSize = 19.sp, fontStyle = FontStyle.Italic , fontFamily = FontFamily.SansSerif, color = Color.White, fontWeight = FontWeight.SemiBold, modifier  = Modifier.padding(5.dp))
            }
            Column(Modifier.padding(top = 30.dp)) {
                Image(painter = painterResource(id = R.drawable.enter), contentDescription = "Imagen enter", modifier = Modifier.clickable {
                    navController.navigate(Routes.screenseries.route)
                    viewmodel.obtenerSeries()
                } )
            }
        }
    }
}