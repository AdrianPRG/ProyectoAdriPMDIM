package com.alopgal962.proyectoadripmdim.VM

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alopgal962.proyectoadripmdim.model.Serie
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlin.Exception

/**
 * @property firebaseauth Instancias del modulo de Autenticacion de firebase
 * @property firebasefirestore Instancia del modulo de Firestore de firebase
 * @property pulsado variable que cuando se pulse para añadir una serie, se vuelve true y aparece el dialog para
 * añadirla, cuando se pulse en otro sitio es false de nuevo
 * @property nombre texto que contendra el nombre de la serie que queramos añadir
 * @property puntuacion texto que contendra la puntuacion de la serie a introducir
 * @property resena texto que contiene la reseña de la serie
 * @property url string que contiene la url de la imagen de la serie
 * @property numseriesdeUser int en el que se guardará el numero de series que se han calificado
 * @property mediadeUser int que contendra la media de calificaciones del usuario
 */
class AppViewmodel : ViewModel() {


    var firebaseauth = Firebase.auth
    var firebasefirestore = Firebase.firestore

    var pulsado by mutableStateOf(false)


    var nombre by mutableStateOf("")
    var puntuacion by mutableStateOf("")
    var resena by mutableStateOf("")
    var url by mutableStateOf("")

    var listaseries by mutableStateOf(mutableListOf<Serie>())

    var numseriesdeUser by mutableStateOf(0)
    var mediadeUser by mutableStateOf(0f)

    var pulsaborrar by mutableStateOf(false)


    /**
     * La funcion crearserie recibe los parametros para crear una serie, comprueba que el nombre y la puntuacion no sean nulos
     * le asigna los parmetros y la devuelve
     * @param nombre string que sera asignada al nombre de la serie
     * @param puntuacion string que sera asignada a la puntuacion de la serie
     * @param resena string que sera asignada a la reseña de la serie
     * @param puntuacion string que sera asignada a la puntuacion de la serie
     */

    fun creaserie(nombre: String, puntuacion: String, resena: String, url: String): Serie {
        var serie = Serie()
        try {
            if (nombre!="" && puntuacion!=""){
                serie.nombre = nombre
                serie.puntuacion = puntuacion.toInt()
                serie.resena = resena
                serie.urlimagen = url
            }
        }catch (e:Exception){
            Log.d("ERORCREATION","Error creando serie")
        }
        return serie
    }

    /*
    Esta funcion restablece los campos para crear una serie a cero
     */

    fun datosACero() {
        nombre = ""
        puntuacion = ""
        resena = ""
        url = ""
    }

    /**
     * Esta funcion se ejecuta dentro de una corrutina,y dento de un bloque try-catch
     * Recibe una serie, y llama a la instancia de firestore, llama a la coleccion Series, y le crea un nuevo documento
     * donde asigna el campo email al email de firebase Autentication, el nombre al nombre de la serie pasada por parametos
     * puntuacion a la puntuacion de la serie, y asi con los demas campos.
     * Los documentos se idenficarán mediante email_nombreSerie, esto nos permite tener mas organizado los documentos de la coleccion,
     * ademas de que nos servira para buscarlos cuando queramos eliminarlos.
     * @param serie es la serie que se le pasa y que se introducirá a la base de datos
     */

    fun meterSerie(serie: Serie) {
        viewModelScope.launch {
            try {
                if (serie.nombre!="" && serie.resena!=""){
                    firebasefirestore.collection("Series").document("${firebaseauth.currentUser!!.email}_${serie.nombre}").set(
                        hashMapOf(
                            "Email" to firebaseauth.currentUser!!.email.toString(),
                            "Nombre" to serie.nombre,
                            "puntuacion" to serie.puntuacion,
                            "resena" to serie.resena,
                            "URL" to serie.urlimagen
                        )
                    )
                }
            }catch (e:Exception){
                Log.d("errorinsert","Error al insertar")
            }
        }
    }

    /*
       Esta funcion se ejecuta en una corrutina, para sea de manera asincrona.
       Dentro de un bloque try-catch, hace una consulta a la coleccion 'series' de firebase, y le pide que
        devuelva los documentos donde el campo email sea igual al del usuario actual de la aplicacion
       Para cada documento se crea una serie,se le asigna los campos a los atributos de la serie y se añade a una lista temporal.
       Una vez finalizado el bucle,los datos de la lista se guardan en la lista de la clase viewmodel.
     */
    fun obtenerSeries() {
        viewModelScope.launch {
            try {
                var listatemporal = mutableListOf<Serie>()
                firebasefirestore.collection("Series").whereEqualTo("Email",firebaseauth.currentUser!!.email.toString()).get().addOnSuccessListener { documents ->
                    for (document in documents) {
                        var serie = Serie()
                        serie.nombre = document.getString("Nombre") ?: "Serie"
                        serie.puntuacion = document.getLong("puntuacion")?.toInt() ?: 10
                        serie.resena = document.getString("resena") ?: "Decent"
                        serie.urlimagen = document.getString("URL") ?: "Buenas"
                        listatemporal.add(serie)
                    }
                    listaseries=listatemporal
                }
                    .addOnFailureListener { exception ->
                        Log.d("ERRORQUE", "Error getting documents: ", exception)
                    }
            }catch (e:Exception){
                Log.e("ObtenerDatos", "Error al obtener datos: ${e.message}")
            }
        }
    }

    /*
    La funcion eliminar serie recorre la lista de series, y como hemos comentado, los documentos se guardan con el formato email_nombreserie, entonces,
    para cada serie, le decimos a firebase que borre de la base de datos, el documento que contenga nuestroEmail_serieactual del bucle
    Eliminando asi todas las series
     */
    fun eliminarseries(){
        viewModelScope.launch {
            try {
                for (serie in listaseries){
                    firebasefirestore.collection("Series").document("${firebaseauth.currentUser!!.email}_${serie.nombre}").delete().addOnSuccessListener{
                        Log.d("DeleteCorrect","Eliminacion completada")
                    }
                }
            }
            catch (e:Exception){
                Log.d("DeleteError","ERROR al borrar los datos")
            }
        }
    }


    /*
    Esta funcion contiene dos variables locales, numseries y media, ahora veremos para que sirven
    En una corrutina, se pide a firebase los documentos de la coleccion series
    Por cada documento/serie, se suma uno a la variable numseries, y se va sumando la variable media cada puntuacion
    de cada serie
    Una vez finalizado el bucle, se asigna la variable 'numseries' a la variable 'numdeseriesuser', que guarda este estado
    A continuacion, la variable media se divide ente el numero de series (puntuacion/numero de series), e igualmente se guarda
    en la variable de estado mediadeUser

     */
    fun obtenerdatosUsuario(){
        var numseries=0
        var media=0f
        viewModelScope.launch {
            try {
                firebasefirestore.collection("Series").whereEqualTo("Email",firebaseauth.currentUser!!.email.toString()).get().addOnSuccessListener {
                    for (documents in it){
                        numseries+=1
                        media+= documents.getLong("puntuacion")!!.toFloat() ?: 0f
                    }
                    numseriesdeUser=numseries
                    mediadeUser=media/numseries
                }
            }
            catch (e:Exception){
                Log.d("ERRORUSEROBTAIN","Error al obtener el numero de libros")
            }
        }
    }

    /*
    Esta funcion se encarga de que, una vez se pulse sobre eliminar, se quite la pantalla de dialog
    Se llama a la funcion de eliminar Series, y se refrescan los datos de usuario (calificaciones, media, etc)
     */
    fun seriesDeleted(){
        pulsaborrar=false
        eliminarseries()
        obtenerdatosUsuario()
    }



}
