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

/**
 * @author AdrianPRG
 *
 * @property correoelectronico varible mutablestateof que guarda en string la direccion de correo introducida por el usuario
 * @property contrasena variable mutablestateof que guarda la contraseña del correo electronico
 * @property repitecontrasena variable mutablestateof que guarda tambien un valor que debe ser igual a la variable 'contraseña'
 * @property firebaseauth instancia de el modulo de autenticacion de firebase
 * @property firebasefirestore instancia del modulo de firestore de firebase
 */
class LoginRegisterViewmodel:ViewModel() {


    var correoelectronico by mutableStateOf("")
    var contrasena by mutableStateOf("")
    var repitecontrasena by mutableStateOf("")


    var firebaseauth = Firebase.auth
    var firebasefirestore = Firebase.firestore


    /**
     * Esta funcion comprueba si los valores de correo y contraseña no son nulos, ademas de si la contraseña
     * que se ha introducido es igual al de repitecontraseña, si es asi, se llama a la instancia de firebaseauth y se
     * utiliza la funcion createuserwithemailandpassword, la cual se le pasa por parametros el correo y la contraseña
     * Si se crea correctamente se ejecuta la función lambda que se pasa por parametros.
     * @param navega es una funcion lambda que se ejecuta si se crea correctamente el usuario
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

    /**
     * La funcion inciarsesion primero comprueba si el correo electronico y la contraseña estan vacios,
     * si no lo estan, se llama a la funcion de ingreso con correo y contraseña de firebase, pasandole por
     * parametros el correo y contraseña
     * Si el inicio de sesion es correcto, se ejecuta la funcion lambda que se pasa por parametros.
     * @param navega es una funcion lambda que se ejecutará si el usuario accede correctamente.
     */

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

    /*
    Esta funcion restablece los campos de registro/inicio de sesion
     */

    fun borrarcampos() {
        correoelectronico = ""
        contrasena = ""
        repitecontrasena = ""
    }


}