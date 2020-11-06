package com.example.turistiandoapp.ui.actividades

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.example.turistiandoapp.R
import kotlinx.android.synthetic.main.activity_detalle_actividad.*

class DetalleActividad : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_actividad)
        if(intent.extras != null){
            Glide.with(this).load(intent.getStringExtra("imagenActividad")).into(imagenActividad)
            tNombreActividad.text = intent.getStringExtra("tNombreActividad")
            tNombrePublicador.text = intent.getStringExtra("tNombrePublicador")
            tDescripActividad.text = intent.getStringExtra("tDescripActividad")
            tValorCosto.text = intent.getStringExtra("tValorCosto")
            tTelefono.text = intent.getStringExtra("tTelefono")
            tDireccion.text = intent.getStringExtra("tDireccion")
        }
    }
}