package com.alopgal962.proyectoadripmdim.ui_views.screens.mainscreens

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
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.alopgal962.proyectoadripmdim.R
import com.alopgal962.proyectoadripmdim.VM.AppViewmodel
import com.alopgal962.proyectoadripmdim.model.Routes
import com.alopgal962.proyectoadripmdim.model.Serie
import com.alopgal962.proyectoadripmdim.ui_views.screens.components.Bottombar
import com.alopgal962.proyectoadripmdim.ui_views.screens.components.Topbar

/**
 * Esta funcion es la que contendra la pantalla donde se visualizarán las series.
 * Contiene un scaffold con su topbar y bottombar y , dependiendo de el estado del atributo pulsado, se mostrarñá el alertdialog o la pantalla principal
 * que consta de un lazyverticalgrid
 * @param navController es el que se encarga de navegar entre pantallas
 * @param viewmodel es el que contiene las funciones de viewmodel, en esta screen por ejemplo, la de obtener series, tambien el atributo pulsado, etc.
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SeriesScreens(navController: NavController, viewmodel: AppViewmodel) {
    Scaffold(topBar = { Topbar() }, bottomBar = {
        Bottombar(
            onCasaClick = { navController.navigate(Routes.screenmain.route) },
            onSeriesClick = { viewmodel.obtenerSeries() },
            onUserClick = {
                navController.navigate(Routes.screenuser.route)
                viewmodel.obtenerdatosUsuario()
            }
        )
    }) {
        if (viewmodel.pulsado == false) {
            Column(
                modifier = Modifier
                    .background(color = Color(232, 239, 236))
                    .fillMaxSize()
                    .padding(top = 110.dp, bottom = 110.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .height(535.dp)) {
                    /*
                    A este lazyvertical grid se le pasa por parametro a los items la lista de series, que contiene
                    las series recuperdas por la funcion obtener series del viewmodel.
                    Por cada serie se llama a la funcion ShowSerie y se muestra por pantalla.
                     */
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(1),
                        modifier = Modifier.padding(top = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        items(viewmodel.listaseries.sortedBy { it.puntuacion }.asReversed()) { serie ->
                            ShowSerie(Serie = serie)
                        }
                    }
                }
                /*
                Icono que al ser pulsado, pone el atributo de pulsado a true, llamando asi la funcion
                AddSerie que contiene el AlertDialog para añadir nueva Serie.
                 */
                IconButton(
                    onClick = { viewmodel.pulsado = true },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color(
                            124,
                            111,
                            206
                        )
                    ),
                    modifier = Modifier
                        .padding(top = 16.dp, start = 290.dp)
                        .clip(
                            RoundedCornerShape(10.dp)
                        )
                ) {
                    Text(
                        text = "+",
                        color = Color.White,
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center
                    )
                }
            }
            /*
            Si es true, se muestra el AlertDialog AddSerie, donde se le pasa el viewmodel, y la lambda del OnDismissRequest, el cual al pulsar fuera
            pondra el estado de pulsado a falso
             */
        } else
            AddSerie(viewmodel = viewmodel, { viewmodel.pulsado = false })
    }
}

/**
 * La funcion Addserie contiene un AlertDialog que permite introducir los datos
 * para crear una nueva serie.
 * Esta funcion aparece cuando el atributo 'pulsado' de viewmodel es true
 * Contiene los textfiels y el boton para introducir una serie
 * @param viewmodel es el viewmodel al que se acceden a los datos y funciones.
 * @param quitar es una funcion lambda que se ejecutara cuando el usuario pulse en un sitio que no sea el AlertDialog, en el caso,
 * se pondra el atributo pulsado a false.
 */
@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnrememberedMutableState")
@Composable
fun AddSerie(viewmodel: AppViewmodel, quitar: () -> Unit) {
    AlertDialog(
        title = { Text(text = "Añadir Nueva Serie") },
        onDismissRequest = { quitar() },
        /*Al pulsar sobre añadir, se crea la serie con los atributos del viewmodel, se le pasa por parametros a la funcion meterSerie.
                    * A continuacion, se restablecen los datos de las variables de la serie del viewmodel.
                    * Por ultimo, se cambia la propiedad de pulsado a false, se suma 1 a la cantidad de series y se recarga la lista de series*/
        confirmButton = { Button(onClick = {
            viewmodel.meterSerie(
                viewmodel.creaserie(
                    viewmodel.nombre,
                    viewmodel.puntuacion,
                    viewmodel.resena,
                    viewmodel.url
                )
            )
            viewmodel.datosACero()
            viewmodel.pulsado = false
            viewmodel.numseriesdeUser += 1
            viewmodel.obtenerSeries()
        }, colors = ButtonDefaults.buttonColors(containerColor = Color.Black), modifier = Modifier.padding(top = 10.dp)) {
            Text(text = "Añadir", color = Color.White, modifier = Modifier.padding(5.dp))
        } },
        text = {
            Column {
                TextField(
                    value = viewmodel.nombre,
                    onValueChange = { viewmodel.nombre = it },
                    label = { Text(text = "\uD83D\uDD20 Nombre de serie...") },
                    maxLines = 1,
                    modifier = Modifier.padding(top = 5.dp)
                )
                TextField(
                    value = viewmodel.puntuacion,
                    onValueChange = { viewmodel.puntuacion = it },
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    label = { Text(text = "⭐ Puntuacion...") },
                    maxLines = 1,
                    modifier = Modifier.padding(top = 10.dp)
                )
                TextField(
                    value = viewmodel.resena,
                    onValueChange = { viewmodel.resena = it },
                    label = { Text(text = "\uD83E\uDD14 Reseña de serie....") },
                    maxLines = 2,
                    modifier = Modifier.padding(top = 10.dp)
                )
                TextField(
                    value = viewmodel.url,
                    onValueChange = { viewmodel.url = it },
                    label = { Text(text = "\uD83D\uDD87️ URL de imagen (Puede ser nula)....") },
                    maxLines = 2,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        })
}

/**
 * Esta funcion composable es la encargada de mostrar los datos de la serie.
 * Consta de una columna, y una row, que en su parte izquierda tiene la imagen de la serie y a la derecha las propiedades de la serie (nombre,puntuacion,reseña)
 * @param Serie es la serie que se mostrará con sus propiedas en la funcion ShowSerie
 */
@Composable
fun ShowSerie(Serie: Serie) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color(232, 239, 236)), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .size(330.dp, 172.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(color = Color(35, 54, 71)),
            verticalAlignment = Alignment.CenterVertically
        ) {
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
                    .padding(top = 10.dp, start = 10.dp, end = 5.dp)
            ) {
                Text(
                    text = Serie.nombre,
                    color = Color.White,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "PUNTUACION : ${Serie.puntuacion} ⭐",
                    color = Color.White,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(top = 10.dp)
                )
                Text(
                    text = "Reseña: ${Serie.resena} ",
                    color = Color.White,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(top = 10.dp)
                )
            }
        }
    }
}
