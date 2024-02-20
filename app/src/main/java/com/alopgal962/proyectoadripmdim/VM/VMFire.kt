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
class VMFire :ViewModel(){

    var correoelectronico= mutableStateOf("")
    var contrasena = mutableStateOf("")
    var repitecontrasena = mutableStateOf("")

    var camposllenos = mutableStateOf(false)

    var firebaseauth=Firebase.auth

    fun crearemailycontrasena(){
        if (correoelectronico.value!="" && contrasena.value!="" && contrasena.value==repitecontrasena.value){
            firebaseauth.createUserWithEmailAndPassword(correoelectronico.value,contrasena.value)
            camposllenos.value=true
        }
        else
            Log.d("erroradri","no se ha creado")
    }

    fun navegaono(navega:Unit){
        if (camposllenos.value==true){
            navega
        }
        else
            Log.d("BOOLERROR","Introduc los datos")
    }

    fun borrarcampos(){
        correoelectronico.value=""
        contrasena.value=""
        repitecontrasena.value=""
    }






}