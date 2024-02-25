package com.alopgal962.proyectoadripmdim.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alopgal962.proyectoadripmdim.R

@Preview(showBackground = true)
@Composable
fun Topbar(){
    Row(
        Modifier
            .fillMaxWidth()
            .height(110.dp)
            .background(Color(33, 93, 46))
            .padding(start = 20.dp), verticalAlignment = Alignment.CenterVertically) {
        Image(painter = painterResource(id = R.drawable.iconoapp), contentDescription = "icono de la app")
        Text(text = "Ani&ShowReview", fontSize = 26.sp, modifier = Modifier.padding(start = 30.dp), color = Color.White, fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.Serif)
    }
}

@Composable
fun Bottombar(onCasaClick:() -> Unit?,onSeriesClick:() -> Unit?, onUserClick:() -> Unit?){
    Row(
        Modifier
            .fillMaxWidth()
            .background(Color(33, 93, 46))
            .height(110.dp), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.casa_1), contentDescription = "Casa", modifier = Modifier.clickable { onCasaClick() } )
        Image(painter = painterResource(id = R.drawable.serie_2), contentDescription = "Series", modifier = Modifier.padding(start = 70.dp).clickable { onSeriesClick() })
        Image(painter = painterResource(id = R.drawable.user_3), contentDescription = "User", modifier = Modifier.padding(start = 70.dp).clickable { onUserClick() } )
    }
}