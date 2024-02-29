package com.alopgal962.proyectoadripmdim.VM

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

class LoginRegisterViewmodel:ViewModel() {

    /*
      Campos que maneja el viewmodel sobre el usuario
      Correo, contraseña, y repetir contraseña
    */

    var correoelectronico by mutableStateOf("")
    var contrasena by mutableStateOf("")
    var repitecontrasena by mutableStateOf("")

    /*
      Instancias de los modulos de firebase
     */

    var firebaseauth = Firebase.auth
    var firebasefirestore = Firebase.firestore

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

    fun meterdatosUsuario(){
        viewModelScope.launch {
            try {
                firebasefirestore.collection("Users").document(firebaseauth.currentUser!!.email.toString()).set{
                }
            }
            catch (e:Exception){
                Log.d("Error/InsertarDatos","Error al insertar/actualizar los datos de usuario")
            }
        }
    }

    fun borrarcampos() {
        correoelectronico = ""
        contrasena = ""
        repitecontrasena = ""
    }


}