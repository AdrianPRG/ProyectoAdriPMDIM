package com.alopgal962.proyectoadripmdim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alopgal962.proyectoadripmdim.ui.screens.ScreenRegister
import com.alopgal962.proyectoadripmdim.ui.theme.ProyectoAdriPMDIMTheme
import com.google.firebase.analytics.FirebaseAnalytics

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProyectoAdriPMDIMTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*val analytics = FirebaseAnalytics.getInstance(this)
                    val bundle=Bundle()
                    bundle.putString("message","Firebase bien")
                    analytics.logEvent("InitScreen", bundle)
                     */
                    ScreenRegister()
                }
            }
        }
    }
}
