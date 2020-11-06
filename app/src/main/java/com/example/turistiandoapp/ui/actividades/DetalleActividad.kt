package com.example.turistiandoapp.ui.actividades

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.turistiandoapp.R
import kotlinx.android.synthetic.main.activity_detalle_actividad.*

class DetalleActividad : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_actividad)
        if(intent.extras != null){
            Glide.with(this).load(intent.getStringExtra("imagenActividad")).into(imagenActividad)
            tNombreActividad.text = intent.getStringExtra("tNombreActividad")
            tNombrePublicador.text = intent.getStringExtra("tNombrePublicador")
            tDescripActividad.text = intent.getStringExtra("tDescripActividad")
            tValorCosto.text = "$ " + intent.getIntExtra("tValorCosto", 0).toString() + "COP por persona"
            tTelefono.text = intent.getDoubleExtra("tTelefono",0.0).toString()
            tDireccion.text = intent.getStringExtra("tDireccion")
        }
    }
}