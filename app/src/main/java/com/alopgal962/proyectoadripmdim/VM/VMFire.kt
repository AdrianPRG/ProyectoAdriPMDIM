package com.alopgal962.proyectoadripmdim.VM

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alopgal962.proyectoadripmdim.ui.screens.Serie
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch
import java.lang.Exception

class VMFire : ViewModel() {

    var correoelectronico by mutableStateOf("")
    var contrasena by mutableStateOf("")
    var repitecontrasena by mutableStateOf("")

    var firebaseauth = Firebase.auth
    var firebasefirestore = Firebase.firestore

    var pulsado = mutableStateOf(false)

    var nombre by mutableStateOf("")
    var puntuacion by mutableStateOf("")
    var resena by mutableStateOf("")
    var url by mutableStateOf("")

    var listaseries by mutableStateOf(mutableListOf<Serie>())


    /**
     * FIREBASE
     * AUTENTICACION
     */
    fun crearemailycontrasena(navega: () -> Unit) {
        if (correoelectronico.isNotEmpty() && contrasena.isNotEmpty() && contrasena == repitecontrasena) {
            Log.d("CORREO", "CORREO -> ${correoelectronico} CONTRASEÑA --> ${contrasena}")
            firebaseauth.createUserWithEmailAndPassword(correoelectronico, contrasena)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        navega()
                    } else {
                        Log.d("USERERROR", "Error al registrar usuario: ${task.exception?.message}")
                    }
                }
        } else
            Log.d("CAMPOS-VACIOS", "Los campos estan vacios")
    }

    fun iniciarsesion(navega: () -> Unit) {
        if (correoelectronico != "" && contrasena != "") {
            firebaseauth.signInWithEmailAndPassword(correoelectronico, contrasena)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        navega()
                    } else {
                        Log.d("LOGINERROR", "Error al hacer login: ${task.exception?.message}")
                    }
                }
        }
    }

    fun borrarcampos() {
        correoelectronico = ""
        contrasena = ""
        repitecontrasena = ""
    }

    /**
     * FIREBASE
     * FIRESTORE
     */

    fun creaserie(nombre: String, puntuacion: String, resena: String, url: String): Serie {
        var serie = Serie()
        serie.nombre = nombre
        serie.puntuacion = puntuacion.toInt()
        serie.resena = resena
        serie.urlimagen = url
        return serie
    }

    fun datosACero() {
        nombre = ""
        puntuacion = ""
        resena = ""
        url = ""
    }

    fun meterdatos(serie: Serie) {
        firebasefirestore.collection("Series").document(serie.nombre).set(
            hashMapOf(
                "puntuacion" to serie.puntuacion,
                "resena" to serie.resena,
                "URL" to serie.urlimagen
            )
        )
    }

    fun obtenerdatos() {
        viewModelScope.launch {
            try {
                val serieactual = mutableListOf<Serie>()
                firebasefirestore.collection("Series").get().addOnSuccessListener { documents ->
                    for (document in documents) {
                        var serie = Serie()
                        serie.nombre = document.id
                        serie.puntuacion = document.getLong("puntuacion")?.toInt() ?: 5
                        serie.resena = document.getString("resena") ?: "Decent"
                        serie.urlimagen = document.getString("URL") ?: "Buenas"
                        serieactual.add(serie)

                    }
                    listaseries=serieactual
                }
                    .addOnFailureListener { exception ->
                        Log.d("ERRORQUE", "Error getting documents: ", exception)
                    }
            }catch (e:Exception){
                Log.e("ObtenerDatos", "Error al obtener datos: ${e.message}")
            }
        }
    }

    fun eliminardatos(nombre:String){
        viewModelScope.launch {
            try {
                firebasefirestore.collection("Series").document(nombre).delete()
            }
            catch (e:Exception){
                Log.d("Excepcion","No se pudo borrar")
            }
        }
    }


}
