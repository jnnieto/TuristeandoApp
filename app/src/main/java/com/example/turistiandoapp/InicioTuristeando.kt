package com.example.turistiandoapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class InicioTuristeando : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_TuristiandoApp)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio_turisteando)
    }
}