package com.alopgal962.proyectoadripmdim.ui.screens

import android.util.Log

class Serie {
    var nombre:String=""
        get(){
            return field
        }
        set(value){
            if (value.length<=0){
                field="New Serie"
            }
            else
                field=value
        }
    var puntuacion:Int?=0
        get() {
            return field
        }
        set(value){
            if (value!!<=10 && value>=0){
                field=value
            }
            else
                field=5
        }
    var resena:String?=""
        get(){
            return field
        }
        set(value){
            if (value!!.length<=0){
                field="Decent"
            }
            else if (value.length>55){
                field=value.take(55)
            }
            else field=value
        }

    var urlimagen:String?=""
        get(){
            return field
        }
        set(value){
            if (value!!.length<=0){
                field="https://cdn-icons-png.flaticon.com/512/6553/6553523.png"
            }
            else field=value
        }

}