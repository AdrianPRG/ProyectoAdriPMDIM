package com.alopgal962.proyectoadripmdim.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyGridScope
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.alopgal962.proyectoadripmdim.R
import com.alopgal962.proyectoadripmdim.VM.VMFire

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SeriesScreens(navController: NavController,viewmodel:VMFire){
    Scaffold(topBar = { Topbar()}, bottomBar = { Bottombar(
        onCasaClick = { navController.navigate("Main") },
        onSeriesClick = { /*TODO*/ }) {

    }}) {
        if (viewmodel.pulsado.value==false){
            Column(modifier = Modifier
                .background(color = Color(232, 239, 236))
                .fillMaxSize()
                .padding(top = 110.dp, bottom = 110.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                Column(Modifier.fillMaxWidth().height(535.dp)) {
                    LazyVerticalGrid(columns = GridCells.Fixed(1), modifier = Modifier.padding(top = 20.dp), verticalArrangement = Arrangement.spacedBy(20.dp)){
                       items(viewmodel.listaseries) { serie ->
                           ShowSerie(Serie = serie)
                        }
                    }
                }
                IconButton(onClick = {viewmodel.pulsado.value=true }, colors = IconButtonDefaults.iconButtonColors(containerColor = Color(124, 111, 206)), modifier = Modifier.padding( top = 16.dp,start = 290.dp).clip(
                    RoundedCornerShape(10.dp))){
                    Text(text = "+", color = Color.White, fontSize = 24.sp, textAlign = TextAlign.Center)
                }
            }
        }
        else
            Column(modifier = Modifier
                .background(color = Color(232, 239, 236))
                .fillMaxSize()
                .padding(top = 110.dp, bottom = 110.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                AddSerie(viewmodel = viewmodel, { viewmodel.pulsado.value = false })
            }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun AddSerie(viewmodel:VMFire, quitar:() -> Unit){
    AlertDialog(
        title = { Text(text = "Añadir Nueva Serie")},onDismissRequest = { quitar() }, confirmButton = {  },
        text = { Column {
        TextField(value = viewmodel.nombre, onValueChange = {viewmodel.nombre = it}, label = { Text(text = "Nombre...")}, maxLines = 1, modifier = Modifier.padding(top = 5.dp) )
        TextField(value =viewmodel.puntuacion, onValueChange = {viewmodel.puntuacion = it}, label = { Text(text = "Puntuacion...")}, maxLines = 1,modifier = Modifier.padding(top = 10.dp) )
        TextField(value = viewmodel.resena, onValueChange = {viewmodel.resena = it}, label = { Text(text = "Reseña....")}, maxLines = 2,modifier = Modifier.padding(top = 10.dp) )
        TextField(value = viewmodel.url, onValueChange = {viewmodel.url = it}, label = { Text(text = "Reseña....")}, maxLines = 2,modifier = Modifier.padding(top = 10.dp) )
            Button(onClick = { viewmodel.meterdatos(viewmodel.creaserie(viewmodel.nombre,viewmodel.puntuacion,viewmodel.resena,viewmodel.url))
                viewmodel.datosACero()
                viewmodel.pulsado.value=false
                viewmodel.obtenerdatos()
                             }, colors = ButtonDefaults.buttonColors(containerColor = Color.Black)) {
                Text(text = "Añadir", color = Color.White,modifier =  Modifier.padding(5.dp))
            }
    }})
}

@Composable
fun ShowSerie(Serie:Serie){
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .size(330.dp, 172.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(color = Color(35, 54, 71)), verticalAlignment = Alignment.CenterVertically) {
            AsyncImage(
                model = Serie.urlimagen,
                contentDescription = null,
                modifier = Modifier
                    .width(150.dp)
                    .fillMaxHeight()
                    .scale(1f, 2f) // Optional: You can adjust the scale factor if needed
            )
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(top = 10.dp, start = 10.dp, end = 5.dp)) {
                Text(text = Serie.nombre, color = Color.White, fontSize = 15.sp, fontWeight = FontWeight.Bold)
                Text(text = "PUNTUACION : ${Serie.puntuacion} ⭐",color = Color.White, fontSize = 13.sp, fontWeight = FontWeight.Medium, modifier = Modifier.padding(top = 10.dp))
                Text(text = "Reseña: ${Serie.resena} ",color = Color.White, fontWeight = FontWeight.Light,modifier = Modifier.padding(top = 10.dp))
            }
        }
    }
}
