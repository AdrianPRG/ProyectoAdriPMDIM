package com.alopgal962.proyectoadripmdim.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.alopgal962.proyectoadripmdim.R
import com.alopgal962.proyectoadripmdim.VM.VMFire

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(navController: NavController, ViewmodelVM: VMFire){
    Scaffold(topBar = { Topbar()}, bottomBar = { Bottombar(onCasaClick = { /*TODO*/ }, onSeriesClick = { /*TODO*/ }, onUserClick = { /*TODO*/})}){
        Column(Modifier.fillMaxSize().background(Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
            Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                Box(modifier = Modifier.size(130.dp, 200.dp)
                    .background(Color(33, 93, 46))) {
                    Image(painter = painterResource(id = R.drawable.seriesordenadasimg), contentDescription = "", modifier = Modifier.size(130.dp,100.dp))
                    Text(text = "Series", fontSize = 10.sp, fontWeight = FontWeight.Bold, color = Color.White)
                }
            }

        }
    }
}