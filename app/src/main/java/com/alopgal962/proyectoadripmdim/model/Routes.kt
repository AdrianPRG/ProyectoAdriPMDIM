package com.alopgal962.proyectoadripmdim.model

sealed class Routes(val route:String){
    object screenregister:Routes("Register")
    object screenlogin:Routes("Login")
    object screenmain:Routes("Main")
    object screenseries:Routes("Series")
    object screenuser:Routes("userdata")
}
