package com.alopgal962.proyectoadripmdim.VM

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.flow.MutableStateFlow

@Suppress("UNUSED_EXPRESSION")
class VMFire : ViewModel() {

    var correoelectronico = mutableStateOf("")
    var contrasena = mutableStateOf("")
    var repitecontrasena = mutableStateOf("")


    var firebaseauth = Firebase.auth

    fun crearemailycontrasena(navega: () -> Unit) {
        if (correoelectronico.value != "" && contrasena.value != "" && contrasena.value == repitecontrasena.value) {
            Log.d("CORREO", "CORREO -> ${correoelectronico.value} CONTRASEÑA --> ${contrasena.value}")
            firebaseauth.createUserWithEmailAndPassword(correoelectronico.value, contrasena.value)
                .addOnCompleteListener() {
                    if (it.isSuccessful) {
                        navega()
                        iniciarcampos()
                    } else {
                        Log.d("USERERROR", "no se ha registrado")
                    }
                }
        } else
            Log.d("CAMPOS-VACIOS", "Los campos estan vacios")
    }

    fun iniciarsesion(navega: () -> Unit){
        if (correoelectronico.value!="" && contrasena.value!=""){
            firebaseauth.signInWithEmailAndPassword(correoelectronico.value,contrasena.value).addOnCompleteListener() {
                if (it.isSuccessful){
                    navega()
                }
                else
                    Log.d("LOGINO","Login incorrecto")
            }
        }
    }

    fun iniciarcampos(){
        correoelectronico.value=""
        contrasena.value=""
    }

    fun borrarcampos() {
        correoelectronico.value = ""
        contrasena.value = ""
        repitecontrasena.value = ""
    }


}